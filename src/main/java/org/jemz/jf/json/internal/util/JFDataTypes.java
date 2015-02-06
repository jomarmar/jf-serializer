/*
 * jf-serializer
 *
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.json.internal.util;

import java.util.HashMap;
import java.util.Map;

import org.jemz.jf.json.objects.JFParam;

/**
 * The Class JFDataTypes.
 */
public final class JFDataTypes {

	/** The Constant DEFAULT_ENCODING. */
	public static final String DEFAULT_ENCODING = "UTF-8";

	/** The Constant DATE_PATTERN. */
	public static final String DATE_PATTERN = "yyyyMMddHHmmss.SSS-Z";

	/** The Constant TYPE_NULL. */
	public static final int TYPE_NULL 			= 1000;

	/** The Constant TYPE_INTEGER. */
	public static final int TYPE_INTEGER 	    = 1001;

	/** The Constant TYPE_LONG. */
	public static final int TYPE_LONG   		= 1002;

	/** The Constant TYPE_FLOAT. */
	public static final int TYPE_FLOAT   		= 1003;

	/** The Constant TYPE_DOUBLE. */
	public static final int TYPE_DOUBLE   		= 1004;

	/** The Constant TYPE_CHARACTER. */
	public static final int TYPE_CHARACTER		= 1005;

	/** The Constant TYPE_BOOLEAN. */
	public static final int TYPE_BOOLEAN   		= 1006;

	/** The Constant TYPE_BASE64. */
	public static final int TYPE_BASE64 		= 1007;

	/** The Constant TYPE_BYTE. */
	public static final int TYPE_BYTE 			= 1008;

	/** The Constant TYPE_STRING. */
	public static final int TYPE_STRING 		= 1009;

	/** The Constant TYPE_DATE. */
	public static final int TYPE_DATE	   		= 1010;

	/** The Constant TYPE_VECTOR. */
	public static final int TYPE_VECTOR 		= 3000;

	/** The Constant TYPE_LIST. */
	public static final int TYPE_LIST 			= 3001;

	/** The Constant TYPE_TABLE. */
	public static final int TYPE_TABLE 			= 3002;

	/** The Constant TYPE_MAP. */
	public static final int TYPE_MAP 			= 3004;

	/** The Constant TYPE_OBJECT. */
	public static final int TYPE_OBJECT			= 4000;

	/** The Constant TYPE_OBJECTARRAY. */
	public static final int TYPE_OBJECTARRAY	= 5000;

	// JAVA TYPES
	/** The Constant JAVA_TYPE_SIMPLE_INTEGER. */
	public static final Class<?> JAVA_TYPE_SIMPLE_INTEGER = int.class;

	/** The Constant JAVA_TYPE_SIMPLE_LONG. */
	public static final Class<?> JAVA_TYPE_SIMPLE_LONG = long.class;

	/** The Constant JAVA_TYPE_SIMPLE_FLOAT. */
	public static final Class<?> JAVA_TYPE_SIMPLE_FLOAT   = float.class;

	/** The Constant JAVA_TYPE_SIMPLE_DOUBLE. */
	public static final Class<?> JAVA_TYPE_SIMPLE_DOUBLE = double.class;

	/** The Constant JAVA_TYPE_SIMPLE_BOOLEAN. */
	public static final Class<?> JAVA_TYPE_SIMPLE_BOOLEAN = boolean.class;

	/** The Constant JAVA_TYPE_SIMPLE_CHARACTER. */
	public static final Class<?> JAVA_TYPE_SIMPLE_CHARACTER = char.class;

	/** The Constant JAVA_TYPE_SIMPLE_OBJECT. */
	public static final Class<?> JAVA_TYPE_SIMPLE_OBJECT = JFParam.class;

	/** The Constant JAVA_TYPE_INTEGER. */
	public static final String JAVA_TYPE_INTEGER = "java.lang.Integer";

	/** The Constant JAVA_TYPE_LONG. */
	public static final String JAVA_TYPE_LONG = "java.lang.Long";

	/** The Constant JAVA_TYPE_FLOAT. */
	public static final String JAVA_TYPE_FLOAT   = "java.lang.Float";

	/** The Constant JAVA_TYPE_DOUBLE. */
	public static final String JAVA_TYPE_DOUBLE = "java.lang.Double";

	/** The Constant JAVA_TYPE_BOOLEAN. */
	public static final String JAVA_TYPE_BOOLEAN = "java.lang.Boolean";

	/** The Constant JAVA_TYPE_CHARACTER. */
	public static final String JAVA_TYPE_CHARACTER = "java.lang.Character";

	/** The Constant JAVA_TYPE_STRING. */
	public static final String JAVA_TYPE_STRING  = "java.lang.String";

	/** The Constant JAVA_TYPE_DATE. */
	public static final String JAVA_TYPE_DATE  = "java.util.Date";

	/** The Constant JAVA_TYPE_VECTOR. */
	public static final String JAVA_TYPE_VECTOR  = "java.util.Vector";

	/** The Constant JAVA_TYPE_LIST. */
	public static final String JAVA_TYPE_LIST  = "java.util.ArrayList";

	/** The Constant JAVA_TYPE_TABLE. */
	public static final String JAVA_TYPE_TABLE = "java.util.Hashtable";

	/** The Constant JAVA_TYPE_MAP. */
	public static final String JAVA_TYPE_MAP  = "java.util.HashMap";

	/** The Constant JAVA_TYPE_BYTE. */
	public static final String JAVA_TYPE_BYTE  = "java.lang.Byte";

	/** The Constant JAVA_TYPE_BYTEARRAY. */
	public static final String JAVA_TYPE_BYTEARRAY  = "[B";

	/** The Constant JAVA_TYPE_INTEGERARRAY. */
	public static final String JAVA_TYPE_INTEGERARRAY  = "[I";

	/** The Constant JAVA_TYPE_STRINGARRAY. */
	public static final String JAVA_TYPE_STRINGARRAY  = "[Ljava.lang.String;";

	/** The Constant JAVA_TYPE_LONGARRAY. */
	public static final String JAVA_TYPE_LONGARRAY  = "[J";

	/** The Constant JAVA_TYPE_FLOATARRAY. */
	public static final String JAVA_TYPE_FLOATARRAY  = "[F";

	/** The Constant JAVA_TYPE_DOUBLEARRAY. */
	public static final String JAVA_TYPE_DOUBLEARRAY  = "[D";

	/** The Constant JAVA_TYPE_BOOLEANARRAY. */
	public static final String JAVA_TYPE_BOOLEANARRAY  = "[Z";

	/** The Constant JAVA_TYPE_CHARACTERARRAY. */
	public static final String JAVA_TYPE_CHARACTERARRAY = "[C";

	/** The Constant JAVA_TYPE_NULL. */
	public static final String JAVA_TYPE_NULL = "null";

	/**
	 * JF Types hashmap.
	 */
	private static final Map<String, Integer> types = new HashMap<String, Integer>();


	/**
	 * Simple JF Arrays types.
	 */
	private static final Map<Integer, Class<?>> arrySimpleTypes = new HashMap<Integer, Class<?>>();

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

//	static {
//		arrySimpleTypes.put(TYPE_INTEGERARRAY, JAVA_TYPE_SIMPLE_INTEGER);
//		arrySimpleTypes.put(TYPE_LONGARRAY, JAVA_TYPE_SIMPLE_LONG);
//		arrySimpleTypes.put(TYPE_FLOATARRAY, JAVA_TYPE_SIMPLE_FLOAT);
//		arrySimpleTypes.put(TYPE_DOUBLEARRAY, JAVA_TYPE_SIMPLE_DOUBLE);
//		arrySimpleTypes.put(TYPE_BOOLEANARRAY, JAVA_TYPE_SIMPLE_BOOLEAN);
//		arrySimpleTypes.put(TYPE_CHARACTERARRAY, JAVA_TYPE_SIMPLE_CHARACTER);
//	}

	/**
	 * Hidden constructor.
	 */
	private JFDataTypes() {
	}

	/**
	 * Gets the JF type.
	 *
	 * @param value the param value
	 * @return the JF type
	 */
	public static int getJFType(final Object value) {
        Integer jfType = null;

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
