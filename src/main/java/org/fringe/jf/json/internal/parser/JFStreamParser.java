/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.util.Base64;
import org.fringe.jf.json.internal.util.JFDataTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;

/**
 * The Class JFStreamParser.
 */
public class JFStreamParser {
	
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
	public JFParam readJFParam(String file) throws Exception {
		FileInputStream fos = new FileInputStream(file);
		jsreader = new JsonReader(new InputStreamReader(fos, "UTF-8"));
		return readJFParam();
	}
	
	/**
	 * Read jf param.
	 *
	 * @param instream the instream
	 * @return the JF param
	 * @throws Exception the exception
	 */
	public JFParam readJFParam(InputStream instream) throws Exception {
		jsreader = new JsonReader(new InputStreamReader(instream, "UTF-8"));
		return readJFParam();
		
	}
	
	/**
	 * Read jf param.
	 *
	 * @return the JF param
	 * @throws Exception the exception
	 */
	public JFParam readJFParam() throws Exception {
		JFParam param = internalReadJFParam();
		jsreader.close();
		return param;
		
	}
	
	private JFParam internalReadJFParam() throws Exception {
		String arrayClass = null;
		JFParam param = new JFParam();
		jsreader.beginObject();
		String name = jsreader.nextName();
		if(name.equals("name")) {
			try {
				param.setName(jsreader.nextString());
			} catch(Exception ex) {
				param.setName(null);
				jsreader.nextNull();
			}
		}
		name = jsreader.nextName();
		if(name.equals("type")) {
			param.setType(jsreader.nextInt());
		}
		if(param.getType() == JFDataTypes.TYPE_OBJECTARRAY) {
			name = jsreader.nextName();
			if(name.equals("arrayClass")) {
				arrayClass = jsreader.nextString();
				
			}
		}
		
		name = jsreader.nextName();
		if(name.equals("value")) {
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
					param.setValue(Base64.decode(jsreader.nextString()));
					break;
				case JFDataTypes.TYPE_DATE:
					SimpleDateFormat format = new SimpleDateFormat(JFDataTypes.DATE_PATTERN);
					param.setValue(format.parse(jsreader.nextString()));
					break;
				case JFDataTypes.TYPE_STRINGARRAY:
					List<String> elems0 = new ArrayList<String>();
					String[] result0 = new String[0];
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems0.add(jsreader.nextString());
					}
					param.setValue(elems0.toArray(result0));
					jsreader.endArray();
					break;
				case JFDataTypes.TYPE_INTEGERARRAY:
					List<Integer> elems = new ArrayList<Integer>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems.add(jsreader.nextInt());
					}
					param.setValue(toIntArray(elems));
					jsreader.endArray();
					break;
				case JFDataTypes.TYPE_LONGARRAY:
					List<Long> elems1 = new ArrayList<Long>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems1.add(jsreader.nextLong());
					}
					param.setValue(toLongArray(elems1));
					jsreader.endArray();
					break;

				case JFDataTypes.TYPE_FLOATARRAY:
					List<Float> elems2 = new ArrayList<Float>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems2.add((float)jsreader.nextDouble());
					}
					param.setValue(toFloatArray(elems2));
					jsreader.endArray();
					break;
					
				case JFDataTypes.TYPE_DOUBLEARRAY:
					List<Double> elems3 = new ArrayList<Double>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems3.add(jsreader.nextDouble());
					}
					param.setValue(toDoubleArray(elems3));
					jsreader.endArray();
					break;

				case JFDataTypes.TYPE_BOOLEANARRAY:
					List<Boolean> elems4 = new ArrayList<Boolean>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems4.add(jsreader.nextBoolean());
					}
					param.setValue(toBooleanArray(elems4));
					jsreader.endArray();
					break;

				case JFDataTypes.TYPE_CHARACTERARRAY:
					List<Character> elems5 = new ArrayList<Character>();
					jsreader.beginArray();
					while(jsreader.hasNext()) {
						elems5.add(jsreader.nextString().charAt(0));
					}
					param.setValue(toCharArray(elems5));
					jsreader.endArray();
					break;
					
				case JFDataTypes.TYPE_LIST:
				case JFDataTypes.TYPE_VECTOR:
					param.setValue(getListObject(param.getType()));
					break;
				
				case JFDataTypes.TYPE_MAP:
				case JFDataTypes.TYPE_TABLE:
					param.setValue(getMapObject(param.getType()));
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
	
	private int[] toIntArray(List<Integer> list)  {
	    int[] ret = new int[list.size()];
	    int i = 0;
	    for (Integer e : list)  
	        ret[i++] = e.intValue();
	    return ret;
	}
	
	private long[] toLongArray(List<Long> list)  {
	    long[] ret = new long[list.size()];
	    int i = 0;
	    for (Long e : list)  
	        ret[i++] = e.longValue();
	    return ret;
	}
	
	private float[] toFloatArray(List<Float> list)  {
		float[] ret = new float[list.size()];
	    int i = 0;
	    for (Float e : list)  
	        ret[i++] = e.floatValue();
	    return ret;
	}
	
	private double[] toDoubleArray(List<Double> list)  {
		double[] ret = new double[list.size()];
	    int i = 0;
	    for (Double e : list)  
	        ret[i++] = e.doubleValue();
	    return ret;
	}
	
	private char[] toCharArray(List<Character> list)  {
		char[] ret = new char[list.size()];
	    int i = 0;
	    for (Character e : list)  
	        ret[i++] = e.charValue();
	    return ret;
	}
	
	private boolean[] toBooleanArray(List<Boolean> list)  {
		boolean[] ret = new boolean[list.size()];
	    int i = 0;
	    for (Boolean e : list)  
	        ret[i++] = e.booleanValue();
	    return ret;
	}


	private List<Object> getListObject(int type) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(type == JFDataTypes.TYPE_VECTOR) {
			list = new Vector<Object>();
		}
		
		jsreader.beginArray();
		while(jsreader.hasNext()) {
			list.add(internalReadJFParam().getValue());
		}
		
		
		jsreader.endArray();
		
		return list;
		
	}
	
	private Object getMapObject(int type) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if(type == JFDataTypes.TYPE_TABLE) {
			map = new Hashtable<Object, Object>();
		}
		
		jsreader.beginArray();
		while(jsreader.hasNext()) {
			jsreader.beginObject();
			String iskey = jsreader.nextName();
			Object objKey = null;
			
			if (iskey.equals("key")) {
				objKey = internalReadJFParam().getValue();
			}
			String isvalue = jsreader.nextName();
			Object objValue = null;
			if (isvalue.equals("value")) {
				objValue = internalReadJFParam().getValue();
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
		if(name.equals("clazz")) {
			cl = jsreader.nextString();
		}
		name = jsreader.nextName();
		if(name.equals("attr")) {
			jsreader.beginArray();
			while(jsreader.hasNext()) {
				attrs.add(internalReadJFParam());
			}
			jsreader.endArray();
			
		}
		jsreader.endObject();
		
		return getObject(cl, attrs);
		
	}
	
	private Object[] getJFObjectArray(String arrayClass) throws Exception {
		Object[] o = (Object[])Array.newInstance(Class.forName(arrayClass), 0);
		
		List<Object> elems = new ArrayList<Object>();
		jsreader.beginArray();
		while(jsreader.hasNext()) {
			elems.add(internalReadJFParam().getValue());
		}
		jsreader.endArray();
		
		return elems.toArray(o);
		
	}
	
	
	private final Object getObject(String clazz, List<JFParam> attr) throws Exception {
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
	
	private final Object toObject(JFParam param) throws Exception {
		return param.getValue();
	}
	
	private final String upFirst(String s) {
		return (s.length() > 0) ? Character.toUpperCase(s.charAt(0)) + s.substring(1) :	s;
	}

}
