package org.jemz.jf.json.internal.parser;

import org.jemz.jf.json.internal.objects.JFObject;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.objects.JFParamArray;
import org.jemz.jf.json.internal.util.IJFConstants;
import org.jemz.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jmartinez on 2/10/15.
 */
public class JFXmlParser implements IJFConstants {

    private static final Logger logger = LoggerFactory.getLogger(JFXmlParser.class);
    private static XMLInputFactory xmlif = XMLInputFactory.newInstance();

    private XMLStreamReader jsreader = null;

    /**
     * Instantiates a new JF stream parser.
     */
    public JFXmlParser() {

    }

    /**
     * Read jf param.
     *
     * @param file the file
     * @return the JF param
     * @throws Exception the exception
     */
    public JFParam readJFParam(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        return readJFParam(fis);
    }

    /**
     * Read jf param.
     *
     * @param instream the instream
     * @return the JF param
     * @throws Exception the exception
     */
    public JFParam readJFParam(InputStream instream) throws Exception {
        jsreader = xmlif.createXMLStreamReader(instream, DEFAULT_ENCODING);
        JFParam param = internalReadJFParam();
        jsreader.close();
        return param;

    }

    /**
     * Read jf param.
     *
     * @return the JF param
     * @throws Exception the exception
     */
    public JFParam readJFParam(String src) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(src.getBytes());
        return readJFParam(bis);

    }

    private JFParam internalReadJFParam() throws Exception {
        JFParam p = new JFParam();
        while(jsreader.hasNext()) {
            jsreader.next();
            int eventType = jsreader.getEventType();
            switch(eventType) {
                case XMLStreamConstants.START_ELEMENT:

                    if(jsreader.getLocalName().equals(FIELD_PARAM)) {
                        String name = jsreader.getAttributeValue("", FIELD_NAME);
                        int type = Integer.parseInt(jsreader.getAttributeValue("", FIELD_TYPE));

                        if(name != null &&
                                name.length() > 0) {
                            p.setName(name);
                        }
                        p.setType(type);
                        switch(type) {
                            case TYPE_OBJECT:
                                p.setValue(getJFObject(new JFObject()));
                                break;
                            case TYPE_OBJECTARRAY:
                                p.setValue(getJFObjectArray(new JFParamArray()));
                                break;
                            case TYPE_LIST:
                            case TYPE_VECTOR:
                                p.setValue(getListObject());
                                break;
                            case TYPE_MAP:
                            case TYPE_TABLE:
                                p.setValue(getMapObject());
                                break;



                        }


                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(jsreader.getLocalName().equals(FIELD_PARAM)) {
                        return p;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    switch(p.getType()) {
                        case TYPE_NULL:
                            p.setValue(null);
                            break;
                        case TYPE_BOOLEAN:
                            p.setValue(Boolean.parseBoolean(jsreader.getText()));
                            break;
                        case TYPE_CHARACTER:
                            p.setValue(jsreader.getText().charAt(0));
                            break;
                        case TYPE_BYTE:
                            p.setValue(Byte.parseByte(jsreader.getText()));
                            break;
                        case TYPE_INTEGER:
                            p.setValue(Integer.parseInt(jsreader.getText()));
                            break;
                        case TYPE_LONG:
                            p.setValue(Long.parseLong(jsreader.getText()));
                            break;
                        case TYPE_FLOAT:
                            p.setValue(Float.parseFloat(jsreader.getText()));
                            break;
                        case TYPE_DOUBLE:
                            p.setValue(Double.parseDouble(jsreader.getText()));
                            break;
                        case TYPE_STRING:
                            p.setValue((recoverNonValidXMLCharacters(jsreader.getText())));
                            break;
                        case TYPE_DATE:
                            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
                            p.setValue(format.parse(jsreader.getText()));
                            break;
                        default:
                            p.setValue(jsreader.getText());

                    }
                    break;
            }

        }



        logger.debug("Name: " + p.getName() + " type: " + p.getType() + " value: " + p.getValue());


        return p;
    }

    /**
     * @param in The String
     * @return The in String, original String with non-valid Characters.
     */
    private String recoverNonValidXMLCharacters(String in) {
        StringBuffer out = new StringBuffer(); // Used to hold the output.
        Pattern pat = Pattern.compile("#\\{0x[A-Fa-f0-9]+\\}#");
        Matcher m = pat.matcher(in);

        boolean result = m.find();
        while(result) {
            int data = Integer.parseInt(m.group().substring(4,m.group().length()-2), 16);
            StringBuffer buf = new StringBuffer().append((char)data);
            m.appendReplacement(out, buf.toString());
            result = m.find();
        }
        m.appendTail(out);
        return out.toString();
    }



    private List<JFParam> getListObject() throws Exception {
        List<JFParam> list = new ArrayList<JFParam>();
        while(jsreader.hasNext()) {
            jsreader.next();
            int eventType = jsreader.getEventType();
            switch(eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if(jsreader.getLocalName().equals(FIELD_LIST)) {
                    } else if (jsreader.getLocalName().equals(FIELD_ELEMENT)) {
                        list.add(internalReadJFParam());
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(jsreader.getLocalName().equals(FIELD_LIST)) {
                        return list;
                    }
                    break;
                default:
                    break;
            }

        }
        return list;

    }

    private Map<JFParam, JFParam> getMapObject() throws Exception {
        Map<JFParam, JFParam> map = new LinkedHashMap<JFParam, JFParam>();
        JFParam key = null;
        JFParam val = null;
        while(jsreader.hasNext()) {
            jsreader.next();
            int eventType = jsreader.getEventType();
            switch(eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if(jsreader.getLocalName().equals(FIELD_MAP)) {
                    } else if (jsreader.getLocalName().equals(FIELD_MAP_KEY)) {
                        key = internalReadJFParam();
                    } else if (jsreader.getLocalName().equals(FIELD_MAP_VALUE)) {
                        val = internalReadJFParam();
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(jsreader.getLocalName().equals(FIELD_MAP_ENTRY)) {
                        map.put(key, val);
                    } else if(jsreader.getLocalName().equals(FIELD_MAP)) {
                        return map;
                    }
                    break;
                default:
                    break;
            }

        }
        return map;

    }


    private JFObject getJFObject(JFObject obj) throws Exception {

        if(obj.getAttr() == null) {
            obj.setAttr(new ArrayList<JFParam>());
        }
        while(jsreader.hasNext()) {
            jsreader.next();
            int eventType = jsreader.getEventType();
            switch(eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if(jsreader.getLocalName().equals(TAG_OBJECT)) {
                        obj.setClazz(jsreader.getAttributeValue("", FIELD_CLASS));

                    } else if (jsreader.getLocalName().equals(FIELD_ATTRIBUTE)) {
                         obj.getAttr().add(internalReadJFParam());
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(jsreader.getLocalName().equals(TAG_OBJECT)) {
                        return obj;
                    }
                    break;
                default:
                    break;
            }

        }

        return obj;

    }

    private JFParamArray getJFObjectArray(JFParamArray pArray) throws Exception {
        if(pArray.getParamArray() == null) {
            pArray.setParamArray(new ArrayList<JFParam>());
        }
        while(jsreader.hasNext()) {
            jsreader.next();
            int eventType = jsreader.getEventType();
            switch(eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    if(jsreader.getLocalName().equals(TAG_OBJECTARRAY)) {
                        pArray.setCl(jsreader.getAttributeValue("", FIELD_CLASS));

                    } else if (jsreader.getLocalName().equals(FIELD_ELEMENT)) {
                        pArray.getParamArray().add(internalReadJFParam());
                    }

                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if(jsreader.getLocalName().equals(TAG_OBJECTARRAY)) {
                        return pArray;
                    }
                    break;
                default:
                    break;
            }

        }

        return pArray;

    }
}
