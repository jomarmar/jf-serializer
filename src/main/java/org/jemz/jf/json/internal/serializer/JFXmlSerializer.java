package org.jemz.jf.json.internal.serializer;

import com.google.gson.stream.JsonWriter;
import org.jemz.jf.json.internal.objects.JFObject;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.objects.JFParamArray;
import org.jemz.jf.json.internal.util.IJFConstants;
import org.jemz.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jmartinez on 2/10/15.
 */
public class JFXmlSerializer implements IJFConstants {

    private static final Logger logger = LoggerFactory.getLogger(JFXmlSerializer.class);
    private static XMLOutputFactory xmlof = XMLOutputFactory.newInstance();

    private XMLStreamWriter jswriter = null;

    public JFXmlSerializer() {

    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    public String writeJFParam(JFParam param) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        writeJFParam(param, bos);
        jswriter.close();
        return bos.toString();
    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeJFParam(JFParam param, String file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        writeJFParam(param, fos);
    }

    /**
     * Write jf param.
     *
     * @param param the param
     * @param ostream the ostream
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeJFParam(JFParam param, OutputStream ostream) throws Exception {
        jswriter = xmlof.createXMLStreamWriter(new OutputStreamWriter(ostream, DEFAULT_ENCODING));
        internalWriteJFParam(param);
        jswriter.flush();
        jswriter.close();
    }






    @SuppressWarnings("unchecked")
    private void internalWriteJFParam(JFParam obj) throws IOException, XMLStreamException {
        jswriter.writeStartElement(TAG_PARAM);

        if(obj.getName() != null && obj.getName().length() > 0) {
            jswriter.writeAttribute(FIELD_NAME, obj.getName());
        }

        jswriter.writeAttribute(FIELD_TYPE, ""+obj.getType());

        int type = obj.getType();

        switch(type) {
            case JFDataTypes.TYPE_BASE64:
                jswriter.writeCharacters( (String) obj.getValue());
                break;
            case JFDataTypes.TYPE_OBJECT:
                try {
                    writeJFObject((JFObject)obj.getValue());
                } catch (Exception e) {
                    logger.error("TYPE_OBJECT: " + e.toString(), e);
                }
                break;
            case JFDataTypes.TYPE_OBJECTARRAY:
                try {
                    writeJFObjectArray((JFParamArray)obj.getValue());
                } catch (Exception e1) {
                    logger.error("TYPE_OBJECTARRAY: " + e1.toString(), e1);
                }
                break;
            case JFDataTypes.TYPE_LIST:
            case JFDataTypes.TYPE_VECTOR:
                writeJFList((List<JFParam>) obj.getValue());
                break;
            case JFDataTypes.TYPE_TABLE:
            case JFDataTypes.TYPE_MAP:
                writeJFMap((Map<JFParam, JFParam>) obj.getValue());
                break;
            case JFDataTypes.TYPE_STRING:
                jswriter.writeCharacters(replaceNonValidXMLCharacters((String)obj.getValue()));
                break;
            case TYPE_DATE:
                SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
                jswriter.writeCharacters(format.format((Date)obj.getValue()));
                break;
            default:
                jswriter.writeCharacters(""+obj.getValue());

        }

        jswriter.writeEndElement();


    }

    private void writeJFList(List<JFParam> lst) throws XMLStreamException, IOException {
        jswriter.writeStartElement(FIELD_LIST);

        for(JFParam p : lst) {
            jswriter.writeStartElement(TAG_ELEMENT);
            internalWriteJFParam(p);
            jswriter.writeEndElement();
        }

        jswriter.writeEndElement();

    }

    private void writeJFMap(Map<JFParam, JFParam> map) throws XMLStreamException, IOException {
        jswriter.writeStartElement(FIELD_MAP);

        for(Map.Entry<JFParam,JFParam> p : map.entrySet()) {
            jswriter.writeStartElement(FIELD_MAP_ENTRY);

            jswriter.writeStartElement(FIELD_MAP_KEY);
            internalWriteJFParam(p.getKey());
            jswriter.writeEndElement();

            jswriter.writeStartElement(FIELD_MAP_VALUE);
            internalWriteJFParam(p.getValue());
            jswriter.writeEndElement();

            jswriter.writeEndElement();
        }

        jswriter.writeEndElement();

    }


    private void writeJFObjectArray(JFParamArray pArray) throws XMLStreamException, IOException {
        jswriter.writeStartElement(TAG_OBJECTARRAY);

        jswriter.writeAttribute(TAG_CLASS, pArray.getCl());

        for(JFParam p : pArray.getParamArray()) {
            jswriter.writeStartElement(TAG_ELEMENT);
            internalWriteJFParam(p);
            jswriter.writeEndElement();
        }

        jswriter.writeEndElement();

    }

    private String writeDocument(OutputStream os ) throws Exception {
        return os.toString();
    }

    private void writeJFObject(JFObject pObj) throws XMLStreamException, IOException {
        jswriter.writeStartElement(TAG_OBJECT);

        jswriter.writeAttribute(TAG_CLASS, pObj.getClazz());


        for(JFParam p : pObj.getAttr()) {
            jswriter.writeStartElement(TAG_ATTRIBUTE);
            internalWriteJFParam(p);
            jswriter.writeEndElement();
        }


        jswriter.writeEndElement();
    }



    /**
     * This method ensures that the output String has only
     * valid XML unicode characters as specified by the
     * XML 1.0 standard. For reference, please see
     * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
     * standard</a>. This method will return an empty
     * String if the input is null or empty.
     *
     * @param in The String whose non-valid characters we want to remove.
     * @return The in String. Invalid characters are replaced by the following regex #{0x[a-FA-F0-9]+}#.
     */
    private String replaceNonValidXMLCharacters(String in) {
        StringBuilder out = new StringBuilder(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (in == null || ("".equals(in))) return ""; // vacancy test.
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
//            if(current == 0xA) {
//            	out.append("&#xA");
//            } else if(current == 0xD) {
//            	out.append("&#xD");
//            } else
            if ((current == 0x9) ||
//                (current == 0xA) ||
//                (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF))) {
                out.append(current);
            }

//            else if(current == 0x80) {
//            	String c = "#{0x"+Integer.toHexString(current).toUpperCase() +"}#";
//            	out.append(c);
//            }

            else {
                String c = "#{0x"+Integer.toHexString(current).toUpperCase() +"}#";
                out.append(c);
            }

        }
        return out.toString();
    }


}
