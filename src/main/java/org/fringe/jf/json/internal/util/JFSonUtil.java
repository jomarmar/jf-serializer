package org.fringe.jf.json.internal.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.fringe.jf.json.internal.objects.JFObject;
import org.fringe.jf.json.internal.objects.JFParam;

public final class JFSonUtil {
	
	private JFSonUtil() {
		
	}
	
	
	public static final Object getObject(String clazz, List<JFParam> attr) throws Exception {
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
	
	
	public final static JFObject toJFObject(Object v) throws Exception {
		if(v instanceof JFObject) {
			return (JFObject)v;
		}
		String name = v.getClass().getName();
	
		Class<?> cl = Class.forName(name);
		
		Class<?> superClass =  (Class<?>) cl.getSuperclass();
		Field[] fields = cl.getDeclaredFields();
		List<JFParam> params = new ArrayList<JFParam>();
		for(int i = 0; i < fields.length; i++) {
			String tag = fields[i].getName();
			
			Method meth = null;
			try {
				meth =  cl.getMethod("get" + upFirst(fields[i].getName()), new Class[0]);
			} catch(NoSuchMethodException ex) {
				try {
					meth =  cl.getMethod("is" + upFirst(fields[i].getName()), new Class[0]);	
				} catch(NoSuchMethodException ex1) {
					System.out.println("Cannot find method for: " + cl.getName() + "::" + fields[i].getName());
					continue;
				}
				
			}
			Object obj = meth.invoke(v, new Object[0]);
			params.add(new JFParam(tag, obj));
		}
		if(superClass != null) {
			Field[] sfields = superClass.getDeclaredFields();
			for(int i = 0; i < sfields.length; i++) {
				String tag = sfields[i].getName();
				Method meth = null;
				try {
					meth =  superClass.getMethod("get" + upFirst(sfields[i].getName()), new Class[0]);
				} catch(NoSuchMethodException ex) {
					try {
					meth =  superClass.getMethod("is" + upFirst(sfields[i].getName()), new Class[0]);
					} catch(NoSuchMethodException ex1) {
						System.out.println("Cannot find method for: " + superClass.getName() + "::" + fields[i].getName());
						continue;
					}
				}
				Object obj = meth.invoke(v, new Object[0]);
				params.add(new JFParam(tag, obj));
			}
		}
		
		return new JFObject(name, params);
	}
	
	
	private static final Object toObject(JFParam param) throws Exception {
		return param.getValue();
	}
	
	private static final String upFirst(String s) {
		return (s.length() > 0) ? Character.toUpperCase(s.charAt(0)) + s.substring(1) :	s;
	}

}
