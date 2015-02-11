/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.internal.parser;

import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jemz.jf.json.internal.util.IJFConstants;
import org.jemz.jf.json.internal.objects.JFObject;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.objects.JFParamArray;
import org.jemz.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;

/**
 * The Class JFStreamParser.
 */
public class JFStreamParser implements IJFConstants {
	
	private static final Logger logger = LoggerFactory.getLogger(JFStreamParser.class);
	
	private JsonReader jsreader = null;
	
	/**
	 * Instantiates a new JF stream parser.
	 */
	public JFStreamParser() {
		
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
		jsreader = new JsonReader(new InputStreamReader(instream, DEFAULT_ENCODING));
		return readJFParam();
		
	}

    public JFParam readJFParam(String xml) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes());
        return readJFParam(bis);
    }

	
	/**
	 * Read jf param.
	 *
	 * @return the JF param
	 * @throws Exception the exception
	 */
	private JFParam readJFParam() throws Exception {
		JFParam param = internalReadJFParam();
		jsreader.close();
		return param;
		
	}
	
	private JFParam internalReadJFParam() throws Exception {
		String arrayClass = null;
		JFParam param = new JFParam();
		jsreader.beginObject();
		String name = jsreader.nextName();
		if(FIELD_NAME.equals(name)) {
			try {
				param.setName(jsreader.nextString());
			} catch(Exception ex) {
				param.setName(null);
				jsreader.nextNull();
			}
		}
		name = jsreader.nextName();
		if(FIELD_TYPE.equals(name)) {
			param.setType(jsreader.nextInt());
		}
		if(param.getType() == JFDataTypes.TYPE_OBJECTARRAY) {
			name = jsreader.nextName();
			if(FIELD_CLASS.equals(name)) {
				arrayClass = jsreader.nextString();
				
			}
		}
		
		name = jsreader.nextName();
		if(FIELD_VALUE.equals(name)) {
			switch(param.getType()) {
				case JFDataTypes.TYPE_STRING:
					param.setValue(jsreader.nextString());
					break;
				
				case JFDataTypes.TYPE_NULL:
					jsreader.nextNull();
					param.setValue(null);
					break;
				case JFDataTypes.TYPE_BOOLEAN:
					param.setValue(jsreader.nextBoolean());
					break;
				case JFDataTypes.TYPE_INTEGER:
					param.setValue(jsreader.nextInt());
					break;
				case JFDataTypes.TYPE_LONG:
					param.setValue(jsreader.nextLong());
					break;
				case JFDataTypes.TYPE_FLOAT:
					param.setValue((float)jsreader.nextDouble());
					break;
				case JFDataTypes.TYPE_DOUBLE:
					param.setValue(jsreader.nextDouble());
					break;
				case JFDataTypes.TYPE_BYTE:
					param.setValue(Byte.parseByte(jsreader.nextString()));
					break;
				case JFDataTypes.TYPE_CHARACTER:
					param.setValue(jsreader.nextString().charAt(0));
					break;
				case JFDataTypes.TYPE_BASE64:
					param.setValue(jsreader.nextString());
					break;
				case JFDataTypes.TYPE_DATE:
					SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
					param.setValue(format.parse(jsreader.nextString()));
					break;
				case JFDataTypes.TYPE_LIST:
				case JFDataTypes.TYPE_VECTOR:
					param.setValue(getListObject());
					break;
				
				case JFDataTypes.TYPE_MAP:
				case JFDataTypes.TYPE_TABLE:
					param.setValue(getMapObject());
					break;
				
					
				case JFDataTypes.TYPE_OBJECT:
					param.setValue(getJFObject());
					break;
				case JFDataTypes.TYPE_OBJECTARRAY:
					param.setValue(getJFObjectArray(arrayClass));
					break;
					
					
				default:
					logger.error("Unknown type: " + param.getType());
			
			}
			
		}
		
		jsreader.endObject();
		
		
		logger.debug("Name: " + param.getName() + " type: " + param.getType() + " value: " + param.getValue());
		
		
		return param;
	}
	


    private List<JFParam> getListObject() throws Exception {
        List<JFParam> list = new ArrayList<JFParam>();
        jsreader.beginArray();
        while(jsreader.hasNext()) {
            list.add(internalReadJFParam());
        }


        jsreader.endArray();

        return list;

    }

    private Map<JFParam, JFParam> getMapObject() throws Exception {
        Map<JFParam, JFParam> map = new LinkedHashMap<JFParam, JFParam>();
        jsreader.beginArray();
        while(jsreader.hasNext()) {
            jsreader.beginObject();
            String iskey = jsreader.nextName();
            JFParam objKey = null;

            if (FIELD_MAP_KEY.equals(iskey)) {
                objKey = internalReadJFParam();
            }
            String isvalue = jsreader.nextName();
            JFParam objValue = null;
            if (FIELD_MAP_VALUE.equals(isvalue)) {
                objValue = internalReadJFParam();
            }
            map.put(objKey,  objValue);

            jsreader.endObject();
        }
        jsreader.endArray();

        return map;
    }


    private Object getJFObject() throws Exception {
        List<JFParam> attrs = new ArrayList<JFParam>();
        String cl = null;
        jsreader.beginObject();
        String name = jsreader.nextName();
        if(FIELD_CLASS.equals(name)) {
            cl = jsreader.nextString();
        }
        name = jsreader.nextName();
        if(FIELD_ATTRIBUTE.equals(name)) {
            jsreader.beginArray();
            while(jsreader.hasNext()) {

                attrs.add(internalReadJFParam());
            }
            jsreader.endArray();
        }

        jsreader.endObject();

        return new JFObject(cl, attrs);

    }
	
	private JFParamArray getJFObjectArray(String arrayClass) throws Exception {
        JFParam[] o = (JFParam[]) Array.newInstance(JFParam.class, 0);

        List<JFParam> elems = new ArrayList<JFParam>();

        jsreader.beginArray();
        while (jsreader.hasNext()) {
            elems.add(internalReadJFParam());
        }
        jsreader.endArray();

        JFParamArray array = new JFParamArray();
        array.setCl(arrayClass);
        array.setParamArray(elems);
        return array;
		
	}
	
	
}
