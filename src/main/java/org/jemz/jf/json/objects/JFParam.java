/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.objects;

import org.jemz.jf.json.internal.util.Base64;
import org.jemz.jf.json.internal.util.JFDataTypes;
import org.jemz.jf.json.internal.util.JFSonUtil;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class JFParam.
 */
public class JFParam {
	
	/** The name if any, otherwise is null. */
	private String name;
	
	/** 
	 * The JF data type
	 * 
	 *  {@link org.jemz.jf.json.internal.util.JFDataTypes}
	 * 
	 */
	private int type;
	
	/** The value. */
	private Object value;
	
	/**
	 * Instantiates a new JF param.
	 */
	public JFParam() {
		
	}
	
	/**
	 * Instantiates a new JF param.
	 *
	 * @param value the value
	 * @throws Exception the exception
	 */
	public JFParam(Object value) throws Exception {
		setJFParam(null, value);
	}
	
	/**
	 * Instantiates a new JF param.
	 *
	 * @param name the name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public JFParam(String name, Object value) throws Exception {
		setJFParam(name, value);
	}

    private void setJFParam(String name, Object value) throws Exception {
        this.name = name;
        this.type = JFDataTypes.getJFType(value);
//        if(value == null) {
//            this.type = JFDataTypes.TYPE_NULL;
//        } else {
//            if(value.getClass().isArray()) {
//                if(value instanceof byte[] || value instanceof Byte[]) {
//                    this.type = JFDataTypes.TYPE_BASE64;
//                } else {
//                    this.type = JFDataTypes.TYPE_OBJECTARRAY;
//                }
//            } else {
//                this.type = JFDataTypes.getJFType(value.getClass().getName());
//            }
//
//        }

        switch(this.type) {
            case JFDataTypes.TYPE_BASE64:
                this.value = Base64.encode((byte[]) value);
                break;
            case JFDataTypes.TYPE_OBJECTARRAY:
                this.value = new JFParamArray(value);
                break;
            case JFDataTypes.TYPE_MAP:
            case JFDataTypes.TYPE_TABLE:
                this.value = getMapParams((Map<?, ?>) value);
                break;
            case JFDataTypes.TYPE_LIST:
            case JFDataTypes.TYPE_VECTOR:
                this.value = getListParams((List<?>) value);
                break;
            case JFDataTypes.TYPE_OBJECT:
                this.value = JFSonUtil.toJFObject(value);
                break;
            default:
                this.value = value;
        }

    }
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the JF type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the JF type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
			return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}


    private final List<JFParam> getListParams(List<?> val) throws Exception {
        List<JFParam> res = new ArrayList<JFParam>();
        int count = 0;
        Iterator<?> iter = val.iterator();
        while(iter.hasNext()) {
            Object obj = iter.next();
            JFParam pp = new JFParam("elem"+ count++, obj);
            res.add(pp);
        }
        return res;
    }

    private final Map<JFParam, JFParam> getMapParams(Map<?,?> val) throws Exception {
        Map<JFParam, JFParam> res = new LinkedHashMap<JFParam, JFParam>();
        int count = 0;
        Iterator<?> iter = val.keySet().iterator();
        while(iter.hasNext()) {
            Object key = iter.next();
            Object value = val.get(key);
            JFParam ppKey = new JFParam("key"+ count, key);
            JFParam ppVal = new JFParam("value"+ count, value);
            res.put(ppKey, ppVal);
            count++;
        }
        return res;
    }

}
