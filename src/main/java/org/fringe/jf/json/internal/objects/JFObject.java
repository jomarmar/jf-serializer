/*
 * jf-serializer
 *
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.objects;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class JFObject.
 */
public class JFObject {

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
}
