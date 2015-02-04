package org.fringe.jf.json.internal.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import com.sun.deploy.panel.ITreeNode;
import org.fringe.jf.json.internal.objects.JFObject;
import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.objects.JFParamArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JFSonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JFSonUtil.class);
	
	private JFSonUtil() {
		
	}

    public static final Object toObject(JFParam p) throws Exception {
        switch(p.getType()) {
            case JFDataTypes.TYPE_OBJECTARRAY:
                JFParamArray pArray = (JFParamArray) p.getValue();
                return pArray.getArray();
            case JFDataTypes.TYPE_OBJECT:
                JFObject obj = (JFObject) p.getValue();
                return getObject(obj.getClazz(), obj.getAttr());

            case JFDataTypes.TYPE_VECTOR:
                Vector<Object> vdata = new Vector<Object>();
                List<JFParam> vlst = (List<JFParam>) p.getValue();
                Iterator<JFParam> viter = vlst.iterator();
                while(viter.hasNext()) {
                    JFParam jfParam = viter.next();
                    vdata.add(toObject(jfParam));

                }
                return vdata;
            case JFDataTypes.TYPE_LIST:
                List<Object> data = new ArrayList<Object>();
                List<JFParam> lst = (List<JFParam>) p.getValue();
                Iterator<JFParam> iter = lst.iterator();
                while(iter.hasNext()) {
                    JFParam jfParam = iter.next();
                    data.add(toObject(jfParam));

                }
                return data;
            case JFDataTypes.TYPE_TABLE:
                Map<Object, Object> dt = new Hashtable<Object, Object>();
                Map<JFParam, JFParam> tabp = (Map<JFParam, JFParam>) p.getValue();
                Iterator<Map.Entry<JFParam, JFParam>> it = tabp.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry<JFParam, JFParam> entry = it.next();
                    dt.put(JFSonUtil.toObject(entry.getKey()), JFSonUtil.toObject(entry.getValue()));
                }

                return dt;
            case JFDataTypes.TYPE_MAP:

                Map<Object, Object> d = new LinkedHashMap<Object, Object>();
                Map<JFParam, JFParam> map = (Map<JFParam, JFParam>) p.getValue();
                Iterator<Map.Entry<JFParam, JFParam>> ite = map.entrySet().iterator();
                while(ite.hasNext()) {
                    Map.Entry<JFParam, JFParam> entry = ite.next();
                    d.put(JFSonUtil.toObject(entry.getKey()), JFSonUtil.toObject(entry.getValue()));
                }

                return d;

            case JFDataTypes.TYPE_BASE64:
                return Base64.decode((String) p.getValue());

            default:
                return p.getValue();

        }
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
					logger.warn("Cannot find method for: " + cl.getName() + "::" + fields[i].getName());
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
						logger.warn("Cannot find method for: " + superClass.getName() + "::" + fields[i].getName());
						continue;
					}
				}
				Object obj = meth.invoke(v, new Object[0]);
				params.add(new JFParam(tag, obj));
			}
		}
		
		return new JFObject(name, params);
	}
	
	
//	private static final Object toObject(JFParam param) throws Exception {
//		return param.getValue();
//	}
	
	private static final String upFirst(String s) {
		return (s.length() > 0) ? Character.toUpperCase(s.charAt(0)) + s.substring(1) :	s;
	}

}
