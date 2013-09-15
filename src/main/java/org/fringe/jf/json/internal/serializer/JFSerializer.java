/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json.internal.serializer;


import org.fringe.jf.json.internal.objects.JFParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class JFSerializer.
 */
public class JFSerializer {

	private static GsonBuilder gson_builder = new GsonBuilder();
	
	
	static { 
		gson_builder.serializeNulls();
		gson_builder.setDateFormat("yyyyMMddHHmmss.SSS-Z");
		gson_builder.registerTypeAdapter(JFParam.class, new JFParamSerializer());
	}
	
	private final Gson gson = gson_builder.create();
		
	/**
	 * Instantiates a new JF serializer.
	 */
	public JFSerializer() {
		//gson = gson_builder.create();
	}
	
	/**
	 * Serialize.
	 *
	 * @param param the param
	 * @return the string
	 */
	public String serialize(JFParam param) {
		return gson.toJson(param, JFParam.class);
	}
	
}
