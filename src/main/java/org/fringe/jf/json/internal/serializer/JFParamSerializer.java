/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fringe.jf.json.internal.objects.JFObject;
import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.util.Base64;
import org.fringe.jf.json.internal.util.JFDataTypes;
import org.fringe.jf.json.internal.util.JFSonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// TODO: Auto-generated Javadoc
/**
 * The Class JFParamSerializer.
 */
public class JFParamSerializer implements JsonSerializer<JFParam> {

	private static final Logger logger = LoggerFactory.getLogger(JFParamSerializer.class);
	
	private static Gson gson = null;
	static {
		GsonBuilder gson_builder =  new GsonBuilder();
		
		gson_builder.serializeNulls();
		gson_builder.setDateFormat("yyyyMMddHHmmss.SSS-Z");
		gson_builder.registerTypeAdapter(JFParam.class, new JFParamSerializer());
		gson_builder.disableHtmlEscaping();
		
		gson = gson_builder.create();
	}
	
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JsonElement serialize(JFParam param, Type type, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		if(param.getName() != null) {
			obj.addProperty("name", param.getName());
		}
		obj.addProperty("type", param.getType());
		
		switch(param.getType()) {
			case JFDataTypes.TYPE_INTEGER:
			case JFDataTypes.TYPE_LONG:
			case JFDataTypes.TYPE_FLOAT:
			case JFDataTypes.TYPE_DOUBLE:
			case JFDataTypes.TYPE_BYTE:
			case JFDataTypes.TYPE_CHARACTER:
				obj.add("value", gson.toJsonTree(param.getValue()));
				return obj;
			case JFDataTypes.TYPE_DATE:
				obj.add("value", gson.toJsonTree(param.getValue()));
				return obj;
			case JFDataTypes.TYPE_BASE64:
				obj.addProperty("value", Base64.encode((byte[]) param.getValue()));
				return obj;
			case JFDataTypes.TYPE_OBJECT:
			try {
				obj.add("value", gson.toJsonTree(JFSonUtil.toJFObject(param.getValue())));
			} catch (Exception e) {
				logger.error("TYPE_OBJECT: " + e.toString(), e);
			}
				return obj;
			case JFDataTypes.TYPE_OBJECTARRAY:
				String arrayType = param.getValue().getClass().getName();
				obj.addProperty("arrayClass", arrayType.substring(2, arrayType.length() - 1));
				
			try {
				obj.add("value", gson.toJsonTree(toJFObjectArray(param.getValue())));
			} catch (Exception e) {
				logger.error("TYPE_OBJECTARRAY: " + e.toString(), e);
			}
				return obj;
			case JFDataTypes.TYPE_LIST:
			case JFDataTypes.TYPE_VECTOR:
				List<JFParam> list = new ArrayList<JFParam>();
				List<Object> orig = (List<Object>) param.getValue();
				Iterator<Object> iter = orig.iterator();
				int i = 0;
				while(iter.hasNext()) {
					try {
						JFParam p = new JFParam("elem" + (i++), iter.next());
						list.add(p);
					} catch (Exception e) {
						logger.error("TYPE_VECTOR (Element): " + e.toString(), e);
					}
					
				}
								
				try {
					obj.add("value", gson.toJsonTree(list));
				} catch (Exception e) {
					logger.error("TYPE_VECTOR: " + e.toString(), e);
				}
				return obj;
			case JFDataTypes.TYPE_TABLE:
			case JFDataTypes.TYPE_MAP:
				JsonArray array = new JsonArray();
				Map<?, ?> origMap = (Map<?, ?>) param.getValue();
				int j=0;
				for(Map.Entry<?, ?> entry : origMap.entrySet()) {
					try {
						JFParam keyParam = new JFParam("key" + (j), entry.getKey());
						JFParam valParam = new JFParam("val" + (j), entry.getValue());
						JsonObject jobj = new JsonObject();
						jobj.add("key", gson.toJsonTree(keyParam));
						jobj.add("value", gson.toJsonTree(valParam));
						array.add(jobj);
						j++;
					} catch (Exception e) {
						logger.error("TYPE_MAP (Param): " + e.toString(), e);
					}
				}
								
				try {
					obj.add("value", array);
				} catch (Exception e) {
					logger.error("TYPE_MAP: " + e.toString(), e);
				}
				return obj;
			default:
				
			try {
				obj.add("value", gson.toJsonTree(param.getValue()));
			} catch (Exception e) {
				logger.error("INVALID TYPE: " + param.getType(), e);
			}
			
		}
		
		

		
		return obj;
		
	}
	
	private final JFObject[] toJFObjectArray(Object v) throws Exception {
		if(v instanceof Object[]) {
			Object[] obj = (Object[]) v;
			int len = obj.length;
			JFObject[] objArray = new JFObject[len];
			for(int i = 0; i < obj.length; i++) {
				objArray[i] = JFSonUtil.toJFObject(obj[i]);
			}
			return objArray;
		} else {
			return null;
		}
		
	}
	
	
	
//	private final JFObject toJFObject(Object v) throws Exception {
//		if (v instanceof JFObject) {
//			return (JFObject) v;
//		}
//		String name = v.getClass().getName();
//	
//		Class<?> cl = Class.forName(name);
//		
//		Class<?> superClass =  (Class<?>) cl.getSuperclass();
//		Field[] fields = cl.getDeclaredFields();
//		List<JFParam> params = new ArrayList<JFParam>();
//		for(int i = 0; i < fields.length; i++) {
//			String tag = fields[i].getName();
//			
//			Method meth = null;
//			try {
//				meth =  cl.getMethod("get" + upFirst(fields[i].getName()), new Class[0]);
//			} catch(NoSuchMethodException ex) {
//				try {
//					meth =  cl.getMethod("is" + upFirst(fields[i].getName()), new Class[0]);	
//				} catch(NoSuchMethodException ex1) {
//					System.out.println("Cannot find method for: " + cl.getName() + "::" + fields[i].getName());
//					continue;
//				}
//				
//			}
//			Object obj = meth.invoke(v, new Object[0]);
//			params.add(new JFParam(tag, obj));
//		}
//		if(superClass != null) {
//			Field[] sfields = superClass.getDeclaredFields();
//			for(int i = 0; i < sfields.length; i++) {
//				String tag = sfields[i].getName();
//				Method meth = null;
//				try {
//					meth =  superClass.getMethod("get" + upFirst(sfields[i].getName()), new Class[0]);
//				} catch(NoSuchMethodException ex) {
//					try {
//					meth =  superClass.getMethod("is" + upFirst(sfields[i].getName()), new Class[0]);
//					} catch(NoSuchMethodException ex1) {
//						System.out.println("Cannot find method for: " + superClass.getName() + "::" + fields[i].getName());
//						continue;
//					}
//				}
//				Object obj = meth.invoke(v, new Object[0]);
//				params.add(new JFParam(tag, obj));
//			}
//		}
//		
//		return new JFObject(name, params);
//	}
//	
//	private final String upFirst(String s) {
//		return (s.length() > 0) ? Character.toUpperCase(s.charAt(0)) + s.substring(1) :	s;
//	}
	

}
