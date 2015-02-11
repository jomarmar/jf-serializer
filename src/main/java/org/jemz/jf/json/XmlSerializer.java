/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json;

import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.parser.JFXmlParser;
import org.jemz.jf.json.internal.serializer.JFXmlSerializer;

import java.io.*;


/**
 * The Class JsonSerializer.
 * Implements org.fringe.jf.json.serializer.IJFSerializer interface
 */
public class XmlSerializer implements IJFSerializer {
	
	private static final JFXmlSerializer streamSerializer = new JFXmlSerializer();
	private static final JFXmlParser streamParser = new JFXmlParser();

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.lang.String)
	 */
	@Override
	public final Object parse(final String json) throws Exception {
		JFParam param =  streamParser.readJFParam(new ByteArrayInputStream(json.getBytes()));
		return param.toObject();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.File)
	 */
	@Override
	public final Object parse(final File jsonFile) throws Exception {
		JFParam param = streamParser.readJFParam(jsonFile.getAbsolutePath());
		return param.toObject();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#parse(java.io.InputStream)
	 */
	@Override
	public final Object parse(final InputStream jsonis) throws Exception {
		JFParam param = streamParser.readJFParam(jsonis);
		return param.toObject();
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serialize(java.lang.Object)
	 */
	@Override
	public final String serialize(final Object obj) throws Exception {
        JFParam p = new JFParam("", obj);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        streamSerializer.writeJFParam(p, bos);

        return bos.toString(); // serializer.serialize(p);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToFile(java.lang.Object, java.lang.String)
	 */
	@Override
	public final void serializeToFile(final Object obj, final String file) throws Exception {
        JFParam p = new JFParam("", obj);
		streamSerializer.writeJFParam(p, file);
	}

	/* (non-Javadoc)
	 * @see org.fringe.jf.json.serializer.IJFSerializer#serializeToStream(java.lang.Object, java.io.OutputStream)
	 */
	@Override
	public final void serializeToStream(final Object obj, final OutputStream os) throws Exception {
		JFParam p = new JFParam("", obj);
		streamSerializer.writeJFParam(p, os);
	}

}
