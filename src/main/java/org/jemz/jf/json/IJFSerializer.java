/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json;

import org.jemz.jf.json.objects.JFParam;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Interface IJFSerializer.
 */
public interface IJFSerializer {
	
	/**
	 * Parses a JF formatted json string to a Java Object.
	 *
	 * @param js the js
	 * @return the object
	 * @throws Exception the exception
	 */
	JFParam parse(String js) throws Exception;
	
	/**
	 * Parses a JF formatted json file to a Java Object.
	 *
	 * @param f the f
	 * @return the object
	 * @throws Exception the exception
	 */
    JFParam parse(File f) throws Exception;
	
	/**
	 * Parses a JF formatted json InputStream to a Java Object.
	 *
	 * @param is the is
	 * @return the object
	 * @throws Exception the exception
	 */
    JFParam parse(InputStream is) throws Exception;
	
	/**
	 * Serialize a Java Object to a JF formatted json String
	 *
	 * @param obj the java object
	 * @return the string
	 * @throws Exception the exception
	 */
	String serialize(JFParam obj) throws Exception;
	
	/**
	 * Serialize a Java Object to a JF formatted json file.
	 *
	 * @param obj the java object
	 * @param file the full file path
	 * @throws Exception the exception
	 */
	void serializeToFile(JFParam obj, String file) throws Exception;

	/**
	 * Serialize a Java Object to a JF formatted json OutputStream.
	 *
	 * @param obj the obj
	 * @param os the os
	 * @throws Exception the exception
	 */
	void serializeToStream(JFParam obj, OutputStream os) throws Exception;
	
}
