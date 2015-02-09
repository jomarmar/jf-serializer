/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.internal.objects;

import org.jemz.jf.json.internal.util.Base64;
import org.jemz.jf.json.internal.util.JFDataTypes;


import java.util.*;

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
                this.value = new JFObject(value);
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


    private List<JFParam> getListParams(List<?> val) throws Exception {
        List<JFParam> res = new ArrayList<JFParam>();
        int count = 0;
        for(Object p : val) {
            JFParam pp = new JFParam("e"+ count++, p);
            res.add(pp);
        }
        return res;
    }

    private Map<JFParam, JFParam> getMapParams(Map<?,?> val) throws Exception {
        Map<JFParam, JFParam> res = new LinkedHashMap<JFParam, JFParam>();
        int count = 0;
        for(Map.Entry<?, ?> e : val.entrySet()) {
            JFParam ppKey = new JFParam("k"+ count, e.getKey());
            JFParam ppVal = new JFParam("v"+ count, e.getValue());
            res.put(ppKey, ppVal);
            count++;
        }
        return res;
    }

    public Object toObject() throws Exception {
        switch(this.type) {
            case JFDataTypes.TYPE_OBJECTARRAY:
                JFParamArray pArray = (JFParamArray) this.value;
                return pArray.getArray();
            case JFDataTypes.TYPE_OBJECT:
                JFObject obj = (JFObject) this.value;
                return obj.getObject();

            case JFDataTypes.TYPE_VECTOR:
                Vector<Object> vdata = new Vector<Object>();
                List<JFParam> vlst = (List<JFParam>) this.value;
                Iterator<JFParam> viter = vlst.iterator();
                while(viter.hasNext()) {
                    JFParam jfParam = viter.next();
                    vdata.add(jfParam.toObject());

                }
                return vdata;
            case JFDataTypes.TYPE_LIST:
                List<Object> data = new ArrayList<Object>();
                List<JFParam> lst = (List<JFParam>) this.value;
                Iterator<JFParam> iter = lst.iterator();
                while(iter.hasNext()) {
                    JFParam jfParam = iter.next();
                    data.add(jfParam.toObject());

                }
                return data;
            case JFDataTypes.TYPE_TABLE:
                Map<Object, Object> dt = new Hashtable<Object, Object>();
                Map<JFParam, JFParam> tabp = (Map<JFParam, JFParam>) this.value;
                Iterator<Map.Entry<JFParam, JFParam>> it = tabp.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry<JFParam, JFParam> entry = it.next();
                    dt.put(entry.getKey().toObject(), entry.getValue().toObject());
                }

                return dt;
            case JFDataTypes.TYPE_MAP:

                Map<Object, Object> d = new LinkedHashMap<Object, Object>();
                Map<JFParam, JFParam> map = (Map<JFParam, JFParam>) this.value;
                Iterator<Map.Entry<JFParam, JFParam>> ite = map.entrySet().iterator();
                while(ite.hasNext()) {
                    Map.Entry<JFParam, JFParam> entry = ite.next();
                    d.put(entry.getKey().toObject(), entry.getValue().toObject());
                }

                return d;

            case JFDataTypes.TYPE_BASE64:
                return Base64.decode((String) this.value);

            default:
                return this.value;

        }
    }

}
