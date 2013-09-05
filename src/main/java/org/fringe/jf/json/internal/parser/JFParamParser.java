/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.util.Base64;
import org.fringe.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * The Class JFParamParser.
 */
public class JFParamParser implements JsonDeserializer<JFParam> {
	
	private static final Logger logger = LoggerFactory.getLogger(JFParamParser.class);

	/* (non-Javadoc)
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public JFParam deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		String name = null;
		try {
			name = jsonObject.getAsJsonPrimitive("name").getAsString();
		} catch (Exception e) {
			logger.info("Name attribute is NULL");
		}
		
		try {
			return new JFParam(name, getValue(jsonObject));
		} catch (Exception e) {
			throw new JsonParseException(e.getCause().toString());
		}
		
	}
	
	
	private Object getValue(JsonObject obj) throws Exception {
		int type = obj.getAsJsonPrimitive("type").getAsInt();
		
		switch(type) {
			case JFDataTypes.TYPE_INTEGER:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Integer.parseInt(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_FLOAT:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Float.parseFloat(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_LONG:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Long.parseLong(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_DOUBLE:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Double.parseDouble(obj.getAsJsonPrimitive("value").getAsString());

			case JFDataTypes.TYPE_BOOLEAN:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Boolean.parseBoolean(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_CHARACTER:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return new Character(obj.getAsJsonPrimitive("value").getAsString().charAt(0));
				
			case JFDataTypes.TYPE_BYTE:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Byte.parseByte(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_BASE64:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return Base64.decode(obj.getAsJsonPrimitive("value").getAsString());

			case JFDataTypes.TYPE_STRING:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				return obj.getAsJsonPrimitive("value").getAsString();
				
			case JFDataTypes.TYPE_DATE:
				logger.debug("PRIMITIVE: " + obj.getAsJsonPrimitive("value").getAsString());
				SimpleDateFormat format = new SimpleDateFormat(JFDataTypes.DATE_PATTERN);
				return format.parse(obj.getAsJsonPrimitive("value").getAsString());
				
			case JFDataTypes.TYPE_NULL:
				logger.debug("PRIMITIVE:  null");
				return null;
			
			case JFDataTypes.TYPE_BOOLEANARRAY:
			case JFDataTypes.TYPE_CHARACTERARRAY:
			case JFDataTypes.TYPE_INTEGERARRAY:
			case JFDataTypes.TYPE_LONGARRAY:
			case JFDataTypes.TYPE_FLOATARRAY:
			case JFDataTypes.TYPE_DOUBLEARRAY:
			case JFDataTypes.TYPE_STRINGARRAY:
				logger.debug("ARRAY PRIMITIVE: " + obj.getAsJsonArray("value"));
				return getArrayValue(type, obj.getAsJsonArray("value"));
				
			case JFDataTypes.TYPE_OBJECT:
				logger.debug("OBJECT: " + obj.getAsJsonObject("value"));
				return getObjectValue(obj.getAsJsonObject("value"));
			
			case JFDataTypes.TYPE_OBJECTARRAY:
				logger.debug("OBJECT ARRAY: " + obj.getAsJsonArray("value"));
				return getObjectArrayValue(obj.getAsJsonArray("value"), obj.getAsJsonPrimitive("arrayClass").getAsString());
			
			case JFDataTypes.TYPE_LIST:
			case JFDataTypes.TYPE_VECTOR:
				logger.debug("LIST: " + obj.getAsJsonArray("value"));
				return getListValue(type, obj.getAsJsonArray("value"));
			
			case JFDataTypes.TYPE_MAP:
			case JFDataTypes.TYPE_TABLE:
				logger.debug("MAP: " + obj.getAsJsonArray("value"));
				return getMapValue(type, obj.getAsJsonArray("value"));
			
			default:
				return null;

				
		} 
		
		
		
	}
	
	private Object getListValue(int type, JsonArray arrObj) {
		List<Object> list = new ArrayList<Object>();
		if(type == JFDataTypes.TYPE_VECTOR) {
			list = new Vector<Object>();
		}
		JFParser parser = new JFParser();
		Iterator<JsonElement> iter = arrObj.iterator();
		while(iter.hasNext()) {
			JsonElement elem = iter.next();
			JFParam param = (JFParam) parser.parse(elem.toString());
			list.add(param.getValue());
			
		}
		
		return list;
	}
	
	private Object getMapValue(int type, JsonArray arrObj) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if(type == JFDataTypes.TYPE_TABLE) {
			map = new Hashtable<Object, Object>();
		}
		JFParser parser = new JFParser();
		Iterator<JsonElement> iter = arrObj.iterator();
		while(iter.hasNext()) {
			JsonObject elem = iter.next().getAsJsonObject();
			JsonObject jkey = elem.getAsJsonObject("key");
			JsonObject jval = elem.getAsJsonObject("value");
			JFParam paramKey = (JFParam) parser.parse(jkey.toString());
			JFParam paramValue = (JFParam) parser.parse(jval.toString());
			map.put(paramKey.getValue(), paramValue.getValue());
			
		}
		
		return map;
	}

	
	private Object getObjectArrayValue(JsonArray arrObj, String arrayType) throws Exception {
		int len = arrObj.size();
		int i = 0;
		Object o = Array.newInstance(Class.forName(arrayType), len);
		Iterator<JsonElement> iter = arrObj.iterator();
		while(iter.hasNext()) {
			JsonObject elem = iter.next().getAsJsonObject();
			Object obj = getObjectValue(elem);
			Array.set(o, i++, obj);
		}
		
		return o;
		
	}
	
	private Object getObjectValue(JsonObject obj) throws Exception {
		JFParser parser = new JFParser();
		
		String cl = obj.getAsJsonPrimitive("clazz").getAsString();
		JsonArray array = obj.getAsJsonArray("attr");
		List<JFParam> p = new ArrayList<JFParam>();
		Iterator<JsonElement> iter = array.iterator();
		while(iter.hasNext()) {
			JsonElement elem = iter.next();
			JFParam param = (JFParam) parser.parse(elem.toString());
			p.add(param);
		}
		return getObject(cl, p);
		
	}
	
	private Object getArrayValue(int type, JsonArray jarray) {
		int i = 0;
		switch(type) {
			case JFDataTypes.TYPE_STRINGARRAY:
				String[] result0 = new String[jarray.size()];
				Iterator<JsonElement> iter0 = jarray.iterator();
				while(iter0.hasNext()) {
					result0[i++] = iter0.next().getAsString();
				}
				return result0;
			case JFDataTypes.TYPE_INTEGERARRAY:
				int[] result = new int[jarray.size()];
				Iterator<JsonElement> iter = jarray.iterator();
				while(iter.hasNext()) {
					result[i++] = iter.next().getAsInt();
				}
				return result;
			case JFDataTypes.TYPE_LONGARRAY:
				long[] result1 = new long[jarray.size()];
				Iterator<JsonElement> iter1 = jarray.iterator();
				while(iter1.hasNext()) {
					result1[i++] = iter1.next().getAsLong();
				}
				return result1;
			case JFDataTypes.TYPE_FLOATARRAY:
				float[] result2 = new float[jarray.size()];
				Iterator<JsonElement> iter2 = jarray.iterator();
				while(iter2.hasNext()) {
					result2[i++] = iter2.next().getAsFloat();
				}
				return result2;
			case JFDataTypes.TYPE_DOUBLEARRAY:
				double[] result3 = new double[jarray.size()];
				Iterator<JsonElement> iter3 = jarray.iterator();
				while(iter3.hasNext()) {
					result3[i++] = iter3.next().getAsDouble();
				}
				return result3;
			case JFDataTypes.TYPE_BOOLEANARRAY:
				boolean[] result4 = new boolean[jarray.size()];
				Iterator<JsonElement> iter4 = jarray.iterator();
				while(iter4.hasNext()) {
					result4[i++] = iter4.next().getAsBoolean();
				}
				return result4;
			case JFDataTypes.TYPE_CHARACTERARRAY:
				char[] result5 = new char[jarray.size()];
				Iterator<JsonElement> iter5 = jarray.iterator();
				while(iter5.hasNext()) {
					result5[i++] = iter5.next().getAsCharacter();
				}
				return result5;
			default: 
				return null;
		}
	}
	
	
	/**
	 * Gets the object.
	 *
	 * @param clazz the clazz
	 * @param attr the attr
	 * @return the object
	 * @throws Exception the exception
	 */
	public final Object getObject(String clazz, List<JFParam> attr) throws Exception {
		try {
			
			
			Class<?> cl = Class.forName(clazz);
			Object pbi = cl.newInstance();
			for(int i = 0; i < attr.size(); i++) {
				Object obj = toObject(attr.get(i));

				try {
					Method m = cl.getMethod("set" + upFirst(attr.get(i).getName()), obj.getClass());
					m.invoke(pbi, obj);
					
				} catch(Exception ex) {
					Method[] methods = cl.getMethods();
					Method meth = null;
					for(int j = 0; j < methods.length; j++) {
						if(methods[j].getName().equals("set" + upFirst(attr.get(i).getName()))) {
							meth = methods[j];
							break;
						}
					}
					if(meth != null) {
						meth.invoke(pbi, obj);
					}
				}
			}
			return pbi;
			
		} catch (Exception e) {
			throw new Exception("Error returning object of type: " + clazz, e);
			
		}
	}
	
	/**
	 * To object.
	 *
	 * @param param the param
	 * @return the object
	 * @throws Exception the exception
	 */
	public final Object toObject(JFParam param) throws Exception {
		return param.getValue();
	}
	
	/**
	 * Internal method to get a camel string
	 * @param s 
	 * @return camel string
	 */
	private final String upFirst(String s) {
		return (s.length() > 0) ? Character.toUpperCase(s.charAt(0)) + s.substring(1) :	s;
	}

}
