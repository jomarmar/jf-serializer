package org.jemz.jf.json.internal.util;

import javax.management.Attribute;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jmartinez on 2/8/15.
 */
public interface IJFConstants {

    /** The Constant DEFAULT_ENCODING. */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /** The Constant DATE_PATTERN. */
    public static final String DATE_PATTERN = "yyyyMMddHHmmss.SSS-Z";

    //JFParam
    public static final String FIELD_NAME       = "n";
    public static final String FIELD_TYPE       = "t";
    public static final String FIELD_VALUE      = "v";
    public static final String FIELD_CLASS      = "c";
    public static final String FIELD_MAP_ENTRY    = "me";
    public static final String FIELD_MAP_KEY    = "mk";
    public static final String FIELD_MAP_VALUE  = "mv";
    public static final String FIELD_ATTRIBUTE  = "a";
    public static final String FIELD_PARAM      = "p";
    public static final String FIELD_LIST       = "l";
    public static final String FIELD_MAP        = "m";
    public static final String FIELD_ELEMENT    = "e";





    /** The Constant JAVA_TYPE_INTEGERARRAY. */
    public static final String JAVA_TYPE_INTEGERARRAY  = "[I";

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



    /** The Constant JAVA_TYPE_NULL. */
    public static final String JAVA_TYPE_NULL = "null";


    // XML TAGS
    public static final String TAG_PARAM = "p";
    public static final String TAG_ATTRIBUTE = "a";
    public static final String TAG_ELEMENT = "e";
    public static final String TAG_CLASS = "c";




}
