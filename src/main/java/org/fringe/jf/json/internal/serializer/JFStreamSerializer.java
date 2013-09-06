/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.serializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fringe.jf.json.internal.objects.JFObject;
import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.util.Base64;
import org.fringe.jf.json.internal.util.JFDataTypes;
import org.fringe.jf.json.internal.util.JFSonUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class JFStreamSerializer.
 */
public class JFStreamSerializer {
	private static Gson gson = null;
	private JsonWriter jswriter = null;
	
	
	static {
		GsonBuilder gson_builder =  new GsonBuilder();
		gson_builder.serializeNulls();
		gson_builder.setDateFormat("yyyyMMddHHmmss.SSS-Z");
//		gson_builder.registerTypeAdapter(JFParam.class, new JFParamSerializer());
		gson_builder.disableHtmlEscaping();
		
		gson = gson_builder.create();
	}
	
	/**
	 * Instantiates a new JF stream serializer.
	 */
	public JFStreamSerializer() {
		
	}
	
	
	/**
	 * Write jf param.
	 *
	 * @param param the param
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeJFParam(JFParam param) throws IOException {
		internalWriteJFParam(param);
		jswriter.close();
	}
	
	/**
	 * Write jf param.
	 *
	 * @param param the param
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeJFParam(JFParam param, String file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		jswriter = new JsonWriter(new OutputStreamWriter(fos, "UTF-8"));
		writeJFParam(param);
	}
	
	/**
	 * Write jf param.
	 *
	 * @param param the param
	 * @param ostream the ostream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeJFParam(JFParam param, OutputStream ostream) throws IOException {
		jswriter = new JsonWriter(new OutputStreamWriter(ostream, "UTF-8"));
		writeJFParam(param);
	}
	
	private void internalWriteJFParam(JFParam param) throws IOException {
		
		
		jswriter.beginObject();
//		if(param.getName() != null) {
			jswriter.name("name").value(param.getName());
//		}
		jswriter.name("type").value(param.getType());
		
		writeValue(param.getType(), param.getValue());
		
		jswriter.endObject();
		
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	private void writeValue(int type, Object obj) throws IOException {
		switch(type) {
			case JFDataTypes.TYPE_BASE64:
				jswriter.name("value").value(Base64.encode((byte[])obj));
				break;
			case JFDataTypes.TYPE_OBJECT:
				
				try {
					writeJFObject(obj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case JFDataTypes.TYPE_OBJECTARRAY:
			try {
				writeJFObjectArray(obj);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
			case JFDataTypes.TYPE_LIST:
			case JFDataTypes.TYPE_VECTOR:
				List<Object> orig = (List<Object>)obj;
				Iterator<Object> iter = orig.iterator();
				int i = 0;
				jswriter.name("value");
				jswriter.beginArray();
				while(iter.hasNext()) {
					try {
						JFParam p = new JFParam("elem" + (i++), iter.next());
						internalWriteJFParam(p);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
								
				jswriter.endArray();
				break;
			case JFDataTypes.TYPE_TABLE:
			case JFDataTypes.TYPE_MAP:
				jswriter.name("value");
				jswriter.beginArray();
				Map<?, ?> origMap = (Map<?, ?>)obj;
				Iterator<?> iterMap = origMap.keySet().iterator();
				int j = 0;
				while(iterMap.hasNext()) {
					try {
						jswriter.beginObject();
						Object key = iterMap.next();
						JFParam keyParam = new JFParam("key" + (j), key);
						JFParam valParam = new JFParam("val" + (j), origMap.get(key));
						jswriter.name("key");
						internalWriteJFParam(keyParam);
						jswriter.name("value");
						internalWriteJFParam(valParam);
						jswriter.endObject();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				jswriter.endArray();
				break;
			default:
				
				jswriter.name("value");
				gson.toJson(obj, Object.class, jswriter);
				
		
		}
	
			
	}
	
	private void writeJFObject(Object obj) throws Exception {
		JFObject jfobj = JFSonUtil.toJFObject(obj);
		jswriter.name("value");
		jswriter.beginObject();
		jswriter.name("clazz").value(jfobj.getClazz());
		jswriter.name("attr");
		jswriter.beginArray();
		Iterator<JFParam> param = jfobj.getAttr().iterator();
		while(param.hasNext()) {
			JFParam jfp = param.next();
			internalWriteJFParam(jfp);		
			
		}
		jswriter.endArray();
		
		jswriter.endObject();
	}
	
	private void writeJFObjectArray(Object obj) throws Exception {
		String arrayType = obj.getClass().getName();
		jswriter.name("arrayClass").value(arrayType.substring(2, arrayType.length() - 1));
		jswriter.name("value");
		
		
		Object[] objA = (Object[])obj;
		jswriter.beginArray();
		for(int i = 0; i < objA.length; i++) {
			internalWriteJFParam(new JFParam("elem" + (i), objA[i]));
		}
		jswriter.endArray();
		
		
	}

//	/**
//	 * Transforms any {@link java.lang.Object} into a {@link JFObject}
//	 * @param v object to be transformed
//	 * @return a JFObject
//	 * @throws Exception if v cannot be converted into a {@link JFObject}
//	 */
//	private final JFObject toJFObject(Object v) throws Exception {
//		if(v instanceof JFObject) {
//			return (JFObject)v;
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
//	
	
}
