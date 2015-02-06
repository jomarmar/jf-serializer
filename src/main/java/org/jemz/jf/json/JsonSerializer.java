/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json;

import java.io.*;

import org.jemz.jf.json.objects.JFParam;
import org.jemz.jf.json.internal.parser.JFStreamParser;

import org.jemz.jf.json.internal.serializer.JFStreamSerializer;


/**
 * The Class JsonSerializer.
 * Implements org.fringe.jf.json.serializer.IJFSerializer interface
 */
public class JsonSerializer implements IJFSerializer {
	
	private static final JFStreamSerializer streamSerializer = new JFStreamSerializer();
	private static final JFStreamParser streamParser = new JFStreamParser();

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.lang.String)
	 */
	@Override
	public final JFParam parse(final String json) throws Exception {
		JFParam param =  streamParser.readJFParam(new ByteArrayInputStream(json.getBytes()));
		return param;
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.File)
	 */
	@Override
	public final JFParam parse(final File jsonFile) throws Exception {
		JFParam param = streamParser.readJFParam(jsonFile.getAbsolutePath());
		return param;
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.InputStream)
	 */
	@Override
	public final JFParam parse(final InputStream jsonis) throws Exception {
		JFParam param = streamParser.readJFParam(jsonis);
		return param;
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serialize(java.lang.Object)
	 */
	@Override
	public final String serialize(final JFParam p) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        streamSerializer.writeJFParam(p, bos);

        return bos.toString(); // serializer.serialize(p);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToFile(java.lang.Object, java.lang.String)
	 */
	@Override
	public final void serializeToFile(final JFParam p, final String file) throws Exception {

		streamSerializer.writeJFParam(p, file);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToStream(java.lang.Object, java.io.OutputStream)
	 */
	@Override
	public final void serializeToStream(final JFParam p, final OutputStream os) throws Exception {
		
		streamSerializer.writeJFParam(p, os);
	}

}
