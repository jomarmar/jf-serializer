/*
 * jf-serializer
 *
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.internal.util;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class JFDataTypes.
 */
public final class JFDataTypes implements IJFConstants {


	/**
	 * JF Types hashmap.
	 */
	private static final Map<String, Integer> types = new HashMap<String, Integer>();


	static {
		types.put(JAVA_TYPE_NULL, TYPE_NULL);
		types.put(JAVA_TYPE_INTEGER, TYPE_INTEGER);
		types.put(JAVA_TYPE_LONG, TYPE_LONG);
		types.put(JAVA_TYPE_FLOAT, TYPE_FLOAT);
		types.put(JAVA_TYPE_BOOLEAN, TYPE_BOOLEAN);
		types.put(JAVA_TYPE_DOUBLE, TYPE_DOUBLE);
		types.put(JAVA_TYPE_CHARACTER, TYPE_CHARACTER);
		types.put(JAVA_TYPE_BYTE, TYPE_BYTE);
		types.put(JAVA_TYPE_BYTEARRAY, TYPE_BASE64);
		types.put(JAVA_TYPE_STRING, TYPE_STRING);
		types.put(JAVA_TYPE_DATE, TYPE_DATE);
		types.put(JAVA_TYPE_VECTOR, TYPE_VECTOR);
		types.put(JAVA_TYPE_LIST, TYPE_LIST);
		types.put(JAVA_TYPE_TABLE, TYPE_TABLE);
		types.put(JAVA_TYPE_MAP, TYPE_MAP);
	}


	/**
	 * Gets the JF type.
	 *
	 * @param value the param value
	 * @return the JF type
	 */
	public static int getJFType(final Object value) {
        Integer jfType;

        if (value == null) {
            jfType = JFDataTypes.TYPE_NULL;
        } else {
            if (value.getClass().isArray()) {
                if (value instanceof byte[] || value instanceof Byte[]) {
                    jfType = JFDataTypes.TYPE_BASE64;
                } else {
                    jfType = JFDataTypes.TYPE_OBJECTARRAY;
                }
            } else {
                jfType = types.get(value.getClass().getName());
                if(jfType == null) {
                    jfType = TYPE_OBJECT;
                }

            }

        }
        return jfType;
    }
}
