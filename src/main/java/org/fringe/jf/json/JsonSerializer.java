/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.json;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.json.internal.parser.JFParser;
import org.fringe.jf.json.internal.parser.JFStreamParser;
import org.fringe.jf.json.internal.serializer.JFSerializer;
import org.fringe.jf.json.internal.serializer.JFStreamSerializer;

 
/**
 * The Class JsonSerializer.
 * Implements org.fringe.jf.json.serializer.IJFSerializer interface
 */
public class JsonSerializer implements IJFSerializer {
	
	private static final JFSerializer serializer = new JFSerializer();
	private static final JFParser parser = new JFParser();
	private static final JFStreamSerializer streamSerializer = new JFStreamSerializer();
	private static final JFStreamParser streamParser = new JFStreamParser();

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.lang.String)
	 */
	@Override
	public final Object parse(final String json) throws Exception {
		JFParam param =  parser.parse(json);
		return param.getValue();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.File)
	 */
	@Override
	public final Object parse(final File jsonFile) throws Exception {
		JFParam param = streamParser.readJFParam(jsonFile.getAbsolutePath());
		return param.getValue();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.InputStream)
	 */
	@Override
	public final Object parse(final InputStream jsonis) throws Exception {
		JFParam param = streamParser.readJFParam(jsonis);
		return param.getValue();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serialize(java.lang.Object)
	 */
	@Override
	public final String serialize(final Object obj) throws Exception {
		JFParam p = new JFParam(obj);
		return serializer.serialize(p);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToFile(java.lang.Object, java.lang.String)
	 */
	@Override
	public final void serializeToFile(final Object obj, final String file) throws Exception {
		JFParam p = new JFParam(obj);
		streamSerializer.writeJFParam(p, file);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToStream(java.lang.Object, java.io.OutputStream)
	 */
	@Override
	public final void serializeToStream(final Object obj, final OutputStream os) throws Exception {
		JFParam p = new JFParam(obj);
		streamSerializer.writeJFParam(p, os);
	}

}
