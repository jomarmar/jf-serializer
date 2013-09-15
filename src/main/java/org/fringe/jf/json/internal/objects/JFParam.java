/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.objects;

import org.fringe.jf.json.internal.util.JFDataTypes;

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
	 *  {@link org.fringe.jf.json.internal.util.JFDataTypes}
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
		this.name = null;
		if(value == null) {
			this.type = JFDataTypes.TYPE_NULL;
		} else {
			this.type = JFDataTypes.getJFType(value.getClass().getName());
			
		}
		
		this.value = value;
	}
	
	/**
	 * Instantiates a new JF param.
	 *
	 * @param name the name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public JFParam(String name, Object value) {
		this.name = name;
		if(value == null) {
			this.type = JFDataTypes.TYPE_NULL;
		} else {
			this.type = JFDataTypes.getJFType(value.getClass().getName());
			
		}
		
		this.value = value;
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
}
