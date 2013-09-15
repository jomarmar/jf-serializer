/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.parser;

import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.util.JFDataTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class JFParser.
 */
public class JFParser {
	
	private static GsonBuilder gson_builder = new GsonBuilder();

	
	static { 
		gson_builder.serializeNulls();
		gson_builder.setDateFormat(JFDataTypes.DATE_PATTERN);
		gson_builder.registerTypeAdapter(JFParam.class, new JFParamParser());
		
	}
	private final Gson gson = gson_builder.create();
		
	/**
	 * Instantiates a new JF parser.
	 */
	public JFParser() {
	}
	
	/**
	 * Parses a Json string into a JFParam object.
	 *
	 * @param js the json serialized object
	 * @return the JFParam
	 */
	public JFParam parse(String js) {
		return gson.fromJson(js, JFParam.class);
	}
	

}
