/*
 * jf-serializer
 *
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.internal.objects;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;



/**
 * The Class JFObject.
 */
public class JFObject {

    private static final Logger logger = LoggerFactory.getLogger(JFObject.class);

	/**
	 * The fully qualified class name of the object to be encapsulated.
	 *
	 */
	private String clazz = null;

	/**
	 * The attributes list of the class to be encapsulated.
	 *
	 */
	private List<JFParam> attr = new ArrayList<JFParam>();

	public JFObject() {}

    /**
	 * Instantiates a new JF object.
	 *
	 * @param cl the fully qualified class name
	 * @param attribute the attr
	 */
	public JFObject(final String cl, final List<JFParam> attribute) {
		this.clazz = cl;
		this.attr = attribute;
	}

    public JFObject(final Object obj) throws Exception {
        toJFObject(obj);
    }

    public Object getObject() throws Exception {
        try {


            Class<?> cl = Class.forName(clazz);
            Object pbi = cl.newInstance();
            for (JFParam anAttr : attr) {
                Object obj = anAttr.toObject();

                try {
                    Method m = cl.getMethod("set" + upFirst(anAttr.getName()), obj.getClass());
                    m.invoke(pbi, obj);
                } catch (Exception ex) {
                    Method[] methods = cl.getMethods();
                    Method meth = null;
                    for (Method method : methods) {
                        if (method.getName().equals("set" + upFirst(anAttr.getName()))) {
                            meth = method;
                            break;
                        }
                    }
                    if (meth != null) {
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
	 * Gets the clazz.
	 *
	 * @return the clazz
	 */
	public final String getClazz() {
		return clazz;
	}

	/**
	 * Sets the clazz.
	 *
	 * @param cl the new fully qualified class name
	 */
	public final void setClazz(final String cl) {
		this.clazz = cl;
	}

	/**
	 * Gets the attr.
	 *
	 * @return the attr
	 */
	public final List<JFParam> getAttr() {
		return attr;
	}

	/**
	 * Sets the attr.
	 *
	 * @param attribute the new attr
	 */
	public final void setAttr(final List<JFParam> attribute) {
		this.attr = attribute;
	}

    private void toJFObject(Object v) throws Exception {
        this.clazz = v.getClass().getName();

        Class<?> cl = Class.forName(this.clazz);

        Class<?> superClass = cl.getSuperclass();
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            String tag = field.getName();

            Method meth;
            try {
                meth = cl.getMethod("get" + upFirst(field.getName()));
            } catch (NoSuchMethodException ex) {
                try {
                    meth = cl.getMethod("is" + upFirst(field.getName()));
                } catch (NoSuchMethodException ex1) {
                    logger.warn("Cannot find method for: " + cl.getName() + "::" + field.getName());
                    continue;
                }

            }
            Object obj = meth.invoke(v);
            this.attr.add(new JFParam(tag, obj));
        }
        if(superClass != null) {
            Field[] sfields = superClass.getDeclaredFields();
            for(int i = 0; i < sfields.length; i++) {
                String tag = sfields[i].getName();
                Method meth;
                try {
                    meth =  superClass.getMethod("get" + upFirst(sfields[i].getName()));
                } catch(NoSuchMethodException ex) {
                    try {
                        meth =  superClass.getMethod("is" + upFirst(sfields[i].getName()));
                    } catch(NoSuchMethodException ex1) {
                        logger.warn("Cannot find method for: " + superClass.getName() + "::" + fields[i].getName());
                        continue;
                    }
                }
                Object obj = meth.invoke(v);
                this.attr.add(new JFParam(tag, obj));
            }
        }

    }

    private String upFirst(String s) {
        return (s.length() > 0) ? (Character.toUpperCase(s.charAt(0)) + s.substring(1)) : s;
    }

}
