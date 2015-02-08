/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.test.core.json;

import org.jemz.jf.json.JsonSerializer;
import org.jemz.jf.json.internal.util.JFSonUtil;
import org.jemz.jf.json.objects.JFParam;
import org.jemz.jf.test.core.json.data.JFTestData;
import org.jemz.jf.test.core.json.data.TestSimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// TODO: Auto-generated Javadoc
/**
 * The Class JsonSerializerTest.
 */
public class JsonSerializerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonSerializerTest.class);
	
	private static final JsonSerializer serializer = new JsonSerializer(); 
	
	/**
	 * Test null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testNull() throws Exception {

        JFParam p = new JFParam("testNull", JFTestData.nullTest);

		JFUnitAssert.TimeEllapsed.start("testNull");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testNull");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test integer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testInteger() throws Exception {

        JFParam p = new JFParam("testInteger", JFTestData.intTest);

		JFUnitAssert.TimeEllapsed.start("testInteger");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testInteger");
		JFParam obj = serializer.parse(xml);

		JFUnitAssert.TimeEllapsed.endParse();
		
		JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test float.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloat() throws Exception {

        JFParam p = new JFParam("testFloat", JFTestData.floatTest);

		JFUnitAssert.TimeEllapsed.start("testFloat");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testFloat");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test long.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLong() throws Exception {

        JFParam p = new JFParam("testLong", JFTestData.longTest);
		
		JFUnitAssert.TimeEllapsed.start("testLong");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testLong");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test double.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDouble() throws Exception {

        JFParam p = new JFParam("testDouble", JFTestData.doubleTest);
		
		JFUnitAssert.TimeEllapsed.start("testDouble");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDouble");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test boolean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBoolean() throws Exception {

        JFParam p = new JFParam("testBoolean", JFTestData.boolTest);
		
		JFUnitAssert.TimeEllapsed.start("testBoolean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testBoolean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test character.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharacter() throws Exception {
        JFParam p = new JFParam("testCharacter", JFTestData.charTest);

		JFUnitAssert.TimeEllapsed.start("testCharacter");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCharacter");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test byte.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByte() throws Exception {
        JFParam p = new JFParam("testByte", JFTestData.byteTest);
		
		JFUnitAssert.TimeEllapsed.start("testByte");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testByte");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test string.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testString() throws Exception {
        JFParam p = new JFParam("testString", JFTestData.getString());
		
		JFUnitAssert.TimeEllapsed.start("testString");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testString");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test date.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDate() throws Exception {
        JFParam p = new JFParam("testDate", JFTestData.dateTest);
		
		JFUnitAssert.TimeEllapsed.start("testDate");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDate");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test byte array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByteArray() throws Exception {
        JFParam p = new JFParam("testByteArray", JFTestData.byteATest);

		JFUnitAssert.TimeEllapsed.start("testByteArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testByteArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testIntArray() throws Exception {
        JFParam p = new JFParam("testIntArray", JFTestData.intATest);
		
		JFUnitAssert.TimeEllapsed.start("testIntArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testIntArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test long array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLongArray() throws Exception {
        JFParam p = new JFParam("testLongArray", JFTestData.longATest);
		
		JFUnitAssert.TimeEllapsed.start("testLongArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testLongArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test float array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloatArray() throws Exception {
        JFParam p = new JFParam("testFloatArray", JFTestData.floatATest);
		
		JFUnitAssert.TimeEllapsed.start("testFloatArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testFloatArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test double array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDoubleArray() throws Exception {
        JFParam p = new JFParam("testDoubleArray", JFTestData.doubleATest);
		
		JFUnitAssert.TimeEllapsed.start("testDoubleArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDoubleArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test char array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharArray() throws Exception {
        JFParam p = new JFParam("testCharArray", JFTestData.charATest);

		JFUnitAssert.TimeEllapsed.start("testCharArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCharArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test boolean array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBooleanArray() throws Exception {
        JFParam p = new JFParam("testBoolArray", JFTestData.boolATest);
		
		JFUnitAssert.TimeEllapsed.start("testBoolArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testBoolArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

		
	}
	
	/**
	 * Test string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testStringArray() throws Exception {
        JFParam p = new JFParam("testStringArray", JFTestData.stringATest);
		
		JFUnitAssert.TimeEllapsed.start("testStringArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testStringArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test empty int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyIntArray() throws Exception {
        JFParam p = new JFParam("testEmptyIntArray", JFTestData.emptyIntATest);
		
		JFUnitAssert.TimeEllapsed.start("testEmptyIntArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyIntArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}

	/**
	 * Test empty string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyStringArray() throws Exception {
        JFParam p = new JFParam("testEmptyStringArray", JFTestData.emptyStringATest);
		
		JFUnitAssert.TimeEllapsed.start("testEmptyStringArray");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyStringArray");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSimpleBean() throws Exception {
        JFParam p = new JFParam("testSimpleBean", JFTestData.beanTest);
		
		JFUnitAssert.TimeEllapsed.start("testSimpleBean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testSimpleBean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testArraySimpleBean() throws Exception {
        JFParam p = new JFParam("testArraySimpleBean", JFTestData.beanATest);

		JFUnitAssert.TimeEllapsed.start("testArraySimpleBean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testArraySimpleBean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test empty array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyArraySimpleBean() throws Exception {
        JFParam p = new JFParam("testEmptyArraySimpleBean", JFTestData.emptyBeanATest);
		
		JFUnitAssert.TimeEllapsed.start("testEmptyArraySimpleBean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyArraySimpleBean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}

    @Test
    public void testint2A() throws Exception {


        int[][] int2A = new int[][]{{1,2,3,4}, {10,20,30,40}};

        JFParam p = new JFParam("testInt2Array", int2A);

        JFUnitAssert.TimeEllapsed.start(p.getName());
        String xml = serializer.serialize(p);
        logger.debug("SERIALIZE OBJ: " + xml);
        JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
        JFUnitAssert.TimeEllapsed.start(p.getName());
        JFParam obj = serializer.parse(xml);

        JFUnitAssert.TimeEllapsed.endParse();



        JFUnitAssert.assertJFParam(p, obj);


    }

    @Test
    public void testSimpleBean2A() throws Exception {
        TestSimpleBean[][] sb2A = new TestSimpleBean[][]{JFTestData.beanATest, JFTestData.beanATest};

        JFParam p = new JFParam("testSB2A", sb2A);
        JFUnitAssert.TimeEllapsed.start(p.getName());
        String xml = serializer.serialize(p);
        logger.debug("SERIALIZE OBJ: " + xml);

        JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
        JFUnitAssert.TimeEllapsed.start(p.getName());
        JFParam obj = serializer.parse(xml);

        JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);


    }


	
	/**
	 * Test list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testList() throws Exception {
        JFParam p = new JFParam("testList", JFTestData.getListTest());
		
		JFUnitAssert.TimeEllapsed.start("testList");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testList");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	/**
	 * Test vector.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testVector() throws Exception {
        JFParam p = new JFParam("testVector", JFTestData.getVectorTest());

		JFUnitAssert.TimeEllapsed.start("testVector");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testVector");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	}
	
	/**
	 * Test map.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testMap() throws Exception {
        JFParam p = new JFParam("testMap", JFTestData.getMapTest());
		
		JFUnitAssert.TimeEllapsed.start("testMap");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testMap");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);
	
	}
	
	/**
	 * Test table.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testTable() throws Exception {
        JFParam p = new JFParam("testTable", JFTestData.getTableTest());
		
		JFUnitAssert.TimeEllapsed.start("testTable");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testTable");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test complex bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testComplexBean() throws Exception {
        JFParam p = new JFParam("testComplexBean", JFTestData.complexBeanTest);
		
		JFUnitAssert.TimeEllapsed.start("testComplexBean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testComplexBean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertJFParam(p, obj);

	}
	
	/**
	 * Test compound bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCompoundBean() throws Exception {
        JFParam p = new JFParam("testCompoundBean", JFTestData.compoundBeanTest);
		
		JFUnitAssert.TimeEllapsed.start("testCompoundBean");
		String xml = serializer.serialize(p);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCompoundBean");
		JFParam obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();


        JFUnitAssert.assertJFParam(p, obj);
		
	}
	
	
	

}
