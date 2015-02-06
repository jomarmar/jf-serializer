/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.test.core.json;

import org.jemz.jf.json.objects.JFParam;
import org.jemz.jf.json.internal.parser.JFStreamParser;
import org.jemz.jf.json.internal.serializer.JFStreamSerializer;
import org.jemz.jf.test.core.json.data.JFTestData;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class JFStreamSerializationTest.
 */
public class JFStreamSerializationTest {

	private static final String TEST_FILES_PATH = "./src/test/resources/org/jemz/jf/test/core/json/res/";
	private static JFStreamSerializer ser = new JFStreamSerializer();
	private static JFStreamParser parser = new JFStreamParser();
	

	/**
	 * Test null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testNull() throws Exception {
		// Serialize
		JFParam par = new JFParam("testNull", JFTestData.nullTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test integer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testInteger() throws Exception {
		// Serialize
		JFParam par = new JFParam("testInteger", JFTestData.intTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test float.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloat() throws Exception {
		// Serialize
		JFParam par = new JFParam("testFloat", JFTestData.floatTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test long.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLong() throws Exception {
		// Serialize
		JFParam par = new JFParam("testLong", JFTestData.longTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test double.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDouble() throws Exception {
		// Serialize
		JFParam par = new JFParam("testDouble", JFTestData.doubleTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test boolean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBoolean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testBoolean", JFTestData.boolTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test character.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharacter() throws Exception {
		// Serialize
		JFParam par = new JFParam("testCharacter", JFTestData.charTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test byte.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByte() throws Exception {
		// Serialize
		JFParam par = new JFParam("testByte", JFTestData.byteTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test string.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testString() throws Exception {
		// Serialize
		JFParam par = new JFParam("testString", JFTestData.getString());

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test date.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDate() throws Exception {
		// Serialize
		JFParam par = new JFParam("testDate", JFTestData.dateTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test byte array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByteArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testByteArray", JFTestData.byteATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParam(par, obj);
	}
	
	/**
	 * Test int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testIntArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testIntArray", JFTestData.intATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test long array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLongArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testLongArray", JFTestData.longATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test float array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloatArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testFloatArray", JFTestData.floatATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test double array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDoubleArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testDoubleArray", JFTestData.doubleATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test char array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testCharArray", JFTestData.charATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test boolean array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBooleanArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testBoolArray", JFTestData.boolATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testStringArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testStringArray", JFTestData.stringATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test empty int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyIntArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testEmptyIntArray", JFTestData.emptyIntATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}

	/**
	 * Test empty string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyStringArray() throws Exception {
		// Serialize
		JFParam par = new JFParam("testEmptyStringArray", JFTestData.emptyStringATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertJFParamArray(par, obj);
	}
	
	/**
	 * Test simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSimpleBean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testSimpleBean", JFTestData.beanTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertTestSimpleBeanParam(par, obj);
	}
	
	/**
	 * Test array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testArraySimpleBean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testArraySimpleBean", JFTestData.beanATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertTestSimpleBeanArray(par, obj);
	}
	
	/**
	 * Test empty array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyArraySimpleBean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testEmptyArraySimpleBean", JFTestData.emptyBeanATest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertTestSimpleBeanArray(par, obj);
	}
	
	/**
	 * Test list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testList() throws Exception {
		// Serialize
		JFParam par = new JFParam("testList", JFTestData.getListTest());

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertListParam(par, obj);
	}
	
	/**
	 * Test vector.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testVector() throws Exception {
		// Serialize
		JFParam par = new JFParam("testVector", JFTestData.getVectorTest());

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertListParam(par, obj);
	}
	
	/**
	 * Test map.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testMap() throws Exception {
		// Serialize
		JFParam par = new JFParam("testMap", JFTestData.getMapTest());

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertMapParam(par, obj);
	}
	
	/**
	 * Test table.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testTable() throws Exception {
		// Serialize
		JFParam par = new JFParam("testTable", JFTestData.getTableTest());

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertMapParam(par, obj);
	}
	
	/**
	 * Test complex bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testComplexBean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testComplexBean", JFTestData.complexBeanTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertComplexBeanParam(par, obj);
	}
	
	/**
	 * Test compound bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCompoundBean() throws Exception {
		// Serialize
		JFParam par = new JFParam("testCompoundBean", JFTestData.compoundBeanTest);

		JFUnitAssert.TimeEllapsed.start(par.getName());
		ser.writeJFParam(par, TEST_FILES_PATH + par.getName());
		
		JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
		JFUnitAssert.TimeEllapsed.start(par.getName());
		JFParam obj = (JFParam)parser.readJFParam(TEST_FILES_PATH + par.getName());

		JFUnitAssert.TimeEllapsed.endParse();
		JFUnitAssert.assertCompoundBeanParam(par, obj);
	}
	
}

