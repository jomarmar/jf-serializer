/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.test.core.json;


import org.jemz.jf.json.JsonSerializer;
import org.jemz.jf.json.XmlSerializer;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.test.core.json.data.JFTestData;
import org.jemz.jf.test.core.json.data.TestComplexBean;
import org.jemz.jf.test.core.json.data.TestCompoundBean;
import org.jemz.jf.test.core.json.data.TestSimpleBean;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


// TODO: Auto-generated Javadoc

/**
 * The Class JsonSerializerTest.
 */
public class XmlSerializerTest {

	private static final Logger logger = LoggerFactory.getLogger(XmlSerializerTest.class);

	private static final XmlSerializer serializer = new XmlSerializer();

	/**
	 * Test null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testNull() throws Exception {

        JFUnitAssert.TimeEllapsed.start("testNull");
		String xml = serializer.serialize(JFTestData.nullTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testNull");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertNull(obj);
    }

	/**
	 * Test integer.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testInteger() throws Exception {

        JFUnitAssert.TimeEllapsed.start("testInteger");
		String xml = serializer.serialize(JFTestData.intTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testInteger");
		Object obj = serializer.parse(xml);

		JFUnitAssert.TimeEllapsed.endParse();


        Assert.assertEquals(JFTestData.intTest, obj);
	}

	/**
	 * Test float.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloat() throws Exception {

        JFUnitAssert.TimeEllapsed.start("testFloat");
		String xml = serializer.serialize(JFTestData.floatTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testFloat");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.floatTest, obj);
	}

	/**
	 * Test long.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLong() throws Exception {



		JFUnitAssert.TimeEllapsed.start("testLong");
		String xml = serializer.serialize(JFTestData.longTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testLong");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.longTest, obj);

	}

	/**
	 * Test double.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDouble() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testDouble");
		String xml = serializer.serialize(JFTestData.doubleTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDouble");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.doubleTest, obj);


	}

	/**
	 * Test boolean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBoolean() throws Exception {



		JFUnitAssert.TimeEllapsed.start("testBoolean");
		String xml = serializer.serialize(JFTestData.boolTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testBoolean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.boolTest, obj);

	}

	/**
	 * Test character.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharacter() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testCharacter");
		String xml = serializer.serialize(JFTestData.charTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCharacter");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.charTest, obj);

	}

	/**
	 * Test byte.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByte() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testByte");
		String xml = serializer.serialize(JFTestData.byteTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testByte");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.byteTest, obj);

	}

	/**
	 * Test string.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testString() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testString");
		String xml = serializer.serialize(JFTestData.getString());

		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testString");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.getString(), obj);

	}

	/**
	 * Test date.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDate() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testDate");
		String xml = serializer.serialize(JFTestData.dateTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDate");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertEquals(JFTestData.dateTest, obj);

	}

	/**
	 * Test byte array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testByteArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testByteArray");
		String xml = serializer.serialize(JFTestData.byteATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testByteArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.byteATest, (byte[])obj);

	}

	/**
	 * Test int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testIntArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testIntArray");
		String xml = serializer.serialize(JFTestData.intATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testIntArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.intATest, (int[])obj);

	}

	/**
	 * Test long array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLongArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testLongArray");
		String xml = serializer.serialize(JFTestData.longATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testLongArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.longATest, (long[])obj);
	}

	/**
	 * Test float array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFloatArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testFloatArray");
		String xml = serializer.serialize(JFTestData.floatATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testFloatArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.floatATest, (float[]) obj, 0.1f);

	}

	/**
	 * Test double array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDoubleArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testDoubleArray");
		String xml = serializer.serialize(JFTestData.doubleATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testDoubleArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.doubleATest, (double[]) obj, 0.01d);

	}

	/**
	 * Test char array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testCharArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testCharArray");
		String xml = serializer.serialize(JFTestData.charATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCharArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.charATest, (char[]) obj);

	}

	/**
	 * Test boolean array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testBooleanArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testBoolArray");
		String xml = serializer.serialize(JFTestData.boolATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testBoolArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        for (int i = 0; i < JFTestData.boolATest.length; i++) {
            Assert.assertTrue(JFTestData.boolATest[i] == ((boolean[]) obj)[i]);
        }


	}

	/**
	 * Test string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testStringArray() throws Exception {

		JFUnitAssert.TimeEllapsed.start("testStringArray");
		String xml = serializer.serialize(JFTestData.stringATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testStringArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.stringATest, (Object[])obj);

	}

	/**
	 * Test empty int array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyIntArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testEmptyIntArray");
		String xml = serializer.serialize(JFTestData.emptyIntATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyIntArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.emptyIntATest, (int[])obj);

	}

	/**
	 * Test empty string array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyStringArray() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testEmptyStringArray");
		String xml = serializer.serialize(JFTestData.emptyStringATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyStringArray");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        Assert.assertArrayEquals(JFTestData.emptyStringATest, (Object[])obj);

	}

	/**
	 * Test simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSimpleBean() throws Exception {

		JFUnitAssert.TimeEllapsed.start("testSimpleBean");
		String xml = serializer.serialize(JFTestData.beanTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testSimpleBean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertTestSimpleBean(JFTestData.beanTest, (TestSimpleBean)obj);
	}

	/**
	 * Test array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testArraySimpleBean() throws Exception {

		JFUnitAssert.TimeEllapsed.start("testArraySimpleBean");
		String xml = serializer.serialize(JFTestData.beanATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testArraySimpleBean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        for(int i=0;i<JFTestData.beanATest.length;i++) {
            JFUnitAssert.assertTestSimpleBean(JFTestData.beanATest[i], ((TestSimpleBean[])obj)[i]);
        }

	}

	/**
	 * Test empty array simple bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEmptyArraySimpleBean() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testEmptyArraySimpleBean");
		String xml = serializer.serialize(JFTestData.emptyBeanATest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testEmptyArraySimpleBean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        for(int i=0;i<JFTestData.emptyBeanATest.length;i++) {
            JFUnitAssert.assertTestSimpleBean(JFTestData.emptyBeanATest[i], ((TestSimpleBean[])obj)[i]);
        }

	}

    @Test
    public void testint2A() throws Exception {


        int[][] int2A = new int[][]{{1,2,3,4}, {10,20,30,40}};



        JFUnitAssert.TimeEllapsed.start("testInt2Array");
        String xml = serializer.serialize(int2A);
        logger.debug("SERIALIZE OBJ: " + xml);
        JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
        JFUnitAssert.TimeEllapsed.start("testInt2Array");
        Object obj = serializer.parse(xml);

        JFUnitAssert.TimeEllapsed.endParse();



        for(int i=0;i<int2A.length;i++) {
            Assert.assertArrayEquals(int2A[i], ((int[][])obj)[i]);
        }


    }

    @Test
    public void testSimpleBean2A() throws Exception {
        TestSimpleBean[][] sb2A = new TestSimpleBean[][]{JFTestData.beanATest, JFTestData.beanATest};

        JFParam p = new JFParam("testSB2A", sb2A);
        JFUnitAssert.TimeEllapsed.start(p.getName());
        String xml = serializer.serialize(sb2A);
        logger.debug("SERIALIZE OBJ: " + xml);

        JFUnitAssert.TimeEllapsed.endSerialize();

//		// Parse
        JFUnitAssert.TimeEllapsed.start(p.getName());
        Object obj = serializer.parse(xml);

        JFUnitAssert.TimeEllapsed.endParse();




        for(int i=0;i<sb2A.length;i++) {
            for(int j=0;j<JFTestData.beanATest.length;j++) {
                JFUnitAssert.assertTestSimpleBean(sb2A[i][j], ((TestSimpleBean[][])obj)[i][j]);
            }

        }
    }



	/**
	 * Test list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testList() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testList");
		String xml = serializer.serialize(JFTestData.getListTest());
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testList");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        int i = 0;
        Iterator<String> iter = JFTestData.getListTest().iterator();
        while(iter.hasNext()) {
            String expectedElement = iter.next();
            String actualElement = ((List<String>)obj).get(i++);
            Assert.assertEquals(expectedElement, actualElement);
        }

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
		String xml = serializer.serialize(JFTestData.getVectorTest());
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testVector");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        int i = 0;
        Iterator<String> iter = JFTestData.getVectorTest().iterator();
        while(iter.hasNext()) {
            String expectedElement = iter.next();
            String actualElement = ((List<String>)obj).get(i++);
            Assert.assertEquals(expectedElement, actualElement);
        }
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
		String xml = serializer.serialize(JFTestData.getMapTest());
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testMap");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertMap(JFTestData.getMapTest(), (Map) obj);

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
		String xml = serializer.serialize(JFTestData.getTableTest());
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testTable");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertMap(JFTestData.getTableTest(), (Map) obj);

	}

	/**
	 * Test complex bean.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testComplexBean() throws Exception {


		JFUnitAssert.TimeEllapsed.start("testComplexBean");
		String xml = serializer.serialize(JFTestData.complexBeanTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testComplexBean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();

        JFUnitAssert.assertComplexBean(JFTestData.complexBeanTest, (TestComplexBean)obj);

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
		String xml = serializer.serialize(JFTestData.compoundBeanTest);
		logger.debug("SERIALIZE OBJ: " + xml);
		JFUnitAssert.TimeEllapsed.endSerialize();

		JFUnitAssert.TimeEllapsed.start("testCompoundBean");
		Object obj = serializer.parse(xml);
		JFUnitAssert.TimeEllapsed.endParse();


        TestCompoundBean actualBean = (TestCompoundBean)obj;
        Assert.assertEquals(JFTestData.compoundBeanTest.getDataString(), actualBean.getDataString());
        JFUnitAssert.assertTestSimpleBean(JFTestData.compoundBeanTest.getSimpleBean(), actualBean.getSimpleBean());


		
	}
	
	
	

}
