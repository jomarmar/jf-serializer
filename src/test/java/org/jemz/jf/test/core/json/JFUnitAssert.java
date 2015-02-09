/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.test.core.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jemz.jf.json.internal.objects.JFObject;
import org.jemz.jf.json.internal.objects.JFParam;
import org.jemz.jf.json.internal.objects.JFParamArray;

import org.jemz.jf.test.core.json.data.JFTestData;
import org.jemz.jf.test.core.json.data.TestComplexBean;
import org.jemz.jf.test.core.json.data.TestCompoundBean;
import org.jemz.jf.test.core.json.data.TestSimpleBean;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class JFUnitAssert.
 */
public class JFUnitAssert {
	
	private static final Logger logger = LoggerFactory.getLogger(JFUnitAssert.class);
	
	private JFUnitAssert() {
		
	}
	
	/**
	 * Assert complex bean param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertComplexBeanParam(JFParam expected, JFParam actual) throws Exception {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		assertComplexBean((TestComplexBean)expected.toObject(), (TestComplexBean)actual.toObject());
	}
	
	/**
	 * Assert complex bean.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertComplexBean(TestComplexBean expected, TestComplexBean actual) {
		
		Assert.assertArrayEquals(expected.getDataIntegerArray(), actual.getDataIntegerArray());
		Assert.assertArrayEquals(expected.getDataLongArray(), actual.getDataLongArray());
		Assert.assertArrayEquals(expected.getDataFloatArray(), actual.getDataFloatArray(), 0.01f);
		Assert.assertArrayEquals(expected.getDataDoubleArray(), actual.getDataDoubleArray(), 0.01);
		for (int i = 0; i < expected.getDataBooleanArray().length; i++) {
			Assert.assertTrue(expected.getDataBooleanArray()[i] ==  actual.getDataBooleanArray()[i]);
		}
		for(int i = 0; i < expected.getDataObjectArray().length; i++) {
			assertTestSimpleBean(expected.getDataObjectArray()[i], actual.getDataObjectArray()[i]);
		}
		int i = 0;
		Iterator<String> expectedIter = expected.getDataList().iterator();
		while(expectedIter.hasNext()) {
			String expectedValue = expectedIter.next();
			Assert.assertEquals(expectedValue, actual.getDataList().get(i++));
		}
		i = 0;
		Iterator<String> expectedIterv = expected.getDataVector().iterator();
		while(expectedIterv.hasNext()) {
			String expectedValue = expectedIterv.next();
			Assert.assertEquals(expectedValue, actual.getDataVector().get(i++));
		}
		assertMap(expected.getDataHashMap(), actual.getDataHashMap());
		assertMap(expected.getDataHashtable(), actual.getDataHashtable());
	}
	
	/**
	 * Assert map.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertMap(Map<?, ?> expected, Map<?, ?> actual) {
		Iterator<?> iter = expected.keySet().iterator();
		while(iter.hasNext()) {
			Object expectedKey = iter.next();
			if(expected.get(expectedKey) instanceof TestSimpleBean) {
				assertTestSimpleBean((TestSimpleBean)expected.get(expectedKey), (TestSimpleBean)actual.get(expectedKey));
			} else if(expected.get(expectedKey) instanceof Map) {
				assertMap((Map<?, ?>)expected.get(expectedKey), (Map<?, ?>)actual.get(expectedKey));
			
			} else {
				Assert.assertEquals(expected.get(expectedKey), actual.get(expectedKey));
			}
			
		}
	}
	
	/**
	 * Assert compound bean param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertCompoundBeanParam(JFParam expected, JFParam actual) throws Exception {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		TestCompoundBean expectedBean = (TestCompoundBean)expected.toObject();
		TestCompoundBean actualBean = (TestCompoundBean)actual.toObject();
		Assert.assertEquals(expectedBean.getDataString(), actualBean.getDataString());
		assertTestSimpleBean(expectedBean.getSimpleBean(), actualBean.getSimpleBean());

	
	}
	
	
	
	/**
	 * Assert map param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	@SuppressWarnings("unchecked")
    public static final  void assertMapParam(JFParam expected, JFParam actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        if(expected.getValue() == null) {
            Assert.assertNull(actual.getValue());
        } else {
            Assert.assertNotNull(actual.getValue());
        }

        Map<JFParam, JFParam> expectedList = (Map<JFParam, JFParam>)expected.getValue();
        Map<JFParam, JFParam> actualList = (Map<JFParam, JFParam>)actual.getValue();

        Map<Object, Object> actualMap = new HashMap<Object, Object>();
        Iterator<JFParam> iter = actualList.keySet().iterator();
        while(iter.hasNext()) {
            JFParam actualKey = iter.next();
            JFParam actualValue = actualList.get(actualKey);
            actualMap.put(actualKey.getValue(), actualValue.getValue());

        }
        Map<Object, Object> expectedMap = new HashMap<Object, Object>();
        Iterator<JFParam> iterExpected = expectedList.keySet().iterator();
        while(iterExpected.hasNext()) {
            JFParam expectedKey = iterExpected.next();
            JFParam expectedValue = expectedList.get(expectedKey);
            expectedMap.put(expectedKey.getValue(), expectedValue.getValue());

        }


        Iterator<Object> iterObjects = expectedMap.keySet().iterator();
        while(iterObjects.hasNext()) {
            Object expectedKey = iterObjects.next();
            Object expectedValue = expectedMap.get(expectedKey);
            Object actualValue = actualMap.get(expectedKey);
            Assert.assertEquals(expectedValue, actualValue);
        }

    }
	
	/**
	 * Assert list param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	@SuppressWarnings("unchecked")
	public static final  void assertListParam(JFParam expected, JFParam actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		
		
		List<JFParam> expectedList = (List<JFParam>)expected.getValue();
		List<JFParam> actualList = (List<JFParam>)actual.getValue();
		assertList(expectedList, actualList);
	}
	
	/**
	 * Assert list.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final void assertList(List<JFParam> expected, List<JFParam> actual) {
		int i = 0;
		Iterator<JFParam> iter = expected.iterator();
		while(iter.hasNext()) {
            JFParam expectedElement = iter.next();
            JFParam actualElement = actual.get(i++);
			Assert.assertEquals(expectedElement.getValue(), actualElement.getValue());
		}
	}

	
	
	/**
	 * Assert test simple bean array.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertTestSimpleBeanArray(JFParam expected, JFParam actual) throws Exception {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		TestSimpleBean[] expectedObj = (TestSimpleBean[])((JFParamArray)expected.getValue()).getArray();
		TestSimpleBean[] actualObj = (TestSimpleBean[])((JFParamArray)actual.getValue()).getArray();
		
		for(int i = 0; i < expectedObj.length; i++) {
			assertTestSimpleBean(expectedObj[i], actualObj[i]);
		}

		
	}
	
	/**
	 * Assert test simple bean param.
	 *
	 * @param expectedParam the expected param
	 * @param actualParam the actual param
	 */
	public static final  void assertTestSimpleBeanParam(JFParam expectedParam, JFParam actualParam) throws Exception {
		Assert.assertEquals(expectedParam.getName(), actualParam.getName());
		Assert.assertEquals(expectedParam.getType(), actualParam.getType());
		if(expectedParam.getValue() == null) {
			Assert.assertNull(actualParam.getValue());
		} else {
			Assert.assertNotNull(actualParam.getValue());
		}
		TestSimpleBean expected = (TestSimpleBean)expectedParam.toObject();
		TestSimpleBean actual = (TestSimpleBean)actualParam.toObject();
		assertTestSimpleBean(expected, actual);
	}
	
	/**
	 * Assert test simple bean.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertTestSimpleBean(TestSimpleBean expected, TestSimpleBean actual) {
		Assert.assertEquals(expected.getDataDouble(), actual.getDataDouble(), 0.05);
		Assert.assertEquals(expected.getDataLong(), actual.getDataLong());
		Assert.assertNull(actual.getDataNull());
		Assert.assertEquals(expected.getDataString(), actual.getDataString());
		Assert.assertEquals(expected.getDataByte(), actual.getDataByte());
		Assert.assertArrayEquals(expected.getDataByteArray(), actual.getDataByteArray());
		Assert.assertEquals(expected.getDataDate().toString(), actual.getDataDate().toString());
		Assert.assertEquals(expected.getDataFloat(), actual.getDataFloat(), 0.05f);
		Assert.assertEquals(expected.getDataInt(), actual.getDataInt());
	}

    public static final  void assertJFParamArray(JFParam expected, JFParam actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getType(), actual.getType());
        if(expected.getValue() == null) {
            Assert.assertNull(actual.getValue());
        } else {
            Assert.assertNotNull(actual.getValue());
        }
        JFParam[] expectedObj = ((JFParamArray)expected.getValue()).getParamArray();
        JFParam[] actualObj = ((JFParamArray)actual.getValue()).getParamArray();

        try {

            for(int i = 0; i < expectedObj.length; i++) {
                assertJFParam(expectedObj[i], actualObj[i]);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


    }
	
	/**
	 * Assert jf param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertJFParam(JFParam expected, JFParam actual) throws Exception {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		if(expected.getValue() instanceof int[]) {
			Assert.assertArrayEquals((int[])expected.getValue(), (int[])actual.getValue());
		} else if(expected.getValue() instanceof long[]) {
			Assert.assertArrayEquals((long[])expected.getValue(), (long[])actual.getValue());
		} else if(expected.getValue() instanceof float[]) {
			Assert.assertArrayEquals((float[])expected.getValue(), (float[])actual.getValue(), 0.01f);
		} else if(expected.getValue() instanceof double[]) {
			Assert.assertArrayEquals((double[])expected.getValue(), (double[])actual.getValue(), 0.01);
		} else if(expected.getValue() instanceof char[]) {
			Assert.assertArrayEquals((char[])expected.getValue(), (char[])actual.getValue());
		} else if(expected.getValue() instanceof byte[]) {
			Assert.assertArrayEquals((byte[])expected.getValue(), (byte[])actual.getValue());
		} else if(expected.getValue() instanceof boolean[]) {
			for (int i = 0; i < JFTestData.boolATest.length; i++) {
				Assert.assertTrue(JFTestData.boolATest[i] == ((boolean[]) actual.getValue())[i]);
			}
		} else if(expected.getValue() instanceof String[]) {
			Assert.assertArrayEquals((Object[]) expected.getValue(), (Object[]) actual.getValue());
		} else if(expected.getValue() instanceof JFParamArray) {
            assertJFParamArray(expected, actual);
        } else if(expected.getValue() instanceof JFObject) {
            if(expected.toObject() instanceof TestSimpleBean) {
                assertTestSimpleBeanParam(expected, actual);
            } else if (expected.toObject() instanceof TestComplexBean) {
                assertComplexBeanParam(expected, actual);
            } else if (expected.toObject() instanceof TestCompoundBean) {
                assertCompoundBeanParam(expected, actual);
            }
        } else if (expected.getValue() instanceof List) {
            assertListParam(expected, actual);
        } else if (expected.getValue() instanceof Map) {
            assertMapParam(expected, actual);
        }

        else {
			Assert.assertEquals(expected.getValue(), actual.getValue());
		}
		
	}
	
	
	
	
	/**
	 * The Class TimeEllapsed.
	 */
	public static class TimeEllapsed {
		private static long startTime;
		private static long startTimeMS;
		private static String mesg;

		/**
		 * Start.
		 *
		 * @param msg the msg
		 */
		public static void start(String msg) {
			mesg = msg;
			startTimeMS = System.currentTimeMillis();
			startTime = System.nanoTime();
		}

		/**
		 * End serialize.
		 */
		public static void endSerialize() {
			logger.info(mesg + "[SERIALIZE]: "
					+ (System.nanoTime() - startTime) + "("
					+ (System.currentTimeMillis() - startTimeMS) + ")");
		}

		/**
		 * End parse.
		 */
		public static void endParse() {
			logger.info(mesg + "[PARSE]: "
					+ (System.nanoTime() - startTime) + "("
					+ (System.currentTimeMillis() - startTimeMS) + ")");
		}
	}


}
