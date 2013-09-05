/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.test.core.json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fringe.jf.json.internal.objects.JFParam;
import org.fringe.jf.test.core.json.data.JFTestData;
import org.fringe.jf.test.core.json.data.TestComplexBean;
import org.fringe.jf.test.core.json.data.TestCompoundBean;
import org.fringe.jf.test.core.json.data.TestSimpleBean;
import org.junit.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class JFUnitAssert.
 */
public class JFUnitAssert {
	
	/**
	 * Assert complex bean param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertComplexBeanParam(JFParam expected, JFParam actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		assertComplexBean((TestComplexBean)expected.getValue(), (TestComplexBean)actual.getValue());
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
	public static final  void assertCompoundBeanParam(JFParam expected, JFParam actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		TestCompoundBean expectedBean = (TestCompoundBean)expected.getValue();
		TestCompoundBean actualBean = (TestCompoundBean)actual.getValue();
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
		
		Map<Object, Object> expectedList = (Map<Object, Object>)expected.getValue();
		Map<Object, Object> actualList = (Map<Object, Object>)actual.getValue();
		
		Iterator<Object> iter = expectedList.keySet().iterator();
		while(iter.hasNext()) {
			Object expectedKey = iter.next();
			Object expectedValue = expectedList.get(expectedKey);
			Object actualValue = actualList.get(expectedKey);
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
		
		
		List<Object> expectedList = (List<Object>)expected.getValue();
		List<Object> actualList = (List<Object>)actual.getValue();
		assertList(expectedList, actualList);
	}
	
	/**
	 * Assert list.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final void assertList(List<?> expected, List<?> actual) {
		int i = 0;
		Iterator<?> iter = expected.iterator();
		while(iter.hasNext()) {
			Object expectedElement = iter.next();
			Object actualElement = actual.get(i++);
			Assert.assertEquals(expectedElement, actualElement);
		}
	}

	
	
	/**
	 * Assert test simple bean array.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertTestSimpleBeanArray(JFParam expected, JFParam actual) {
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getType(), actual.getType());
		if(expected.getValue() == null) {
			Assert.assertNull(actual.getValue());
		} else {
			Assert.assertNotNull(actual.getValue());
		}
		TestSimpleBean[] expectedObj = (TestSimpleBean[])expected.getValue();
		TestSimpleBean[] actualObj = (TestSimpleBean[])actual.getValue();
		
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
	public static final  void assertTestSimpleBeanParam(JFParam expectedParam, JFParam actualParam) {
		Assert.assertEquals(expectedParam.getName(), actualParam.getName());
		Assert.assertEquals(expectedParam.getType(), actualParam.getType());
		if(expectedParam.getValue() == null) {
			Assert.assertNull(actualParam.getValue());
		} else {
			Assert.assertNotNull(actualParam.getValue());
		}
		TestSimpleBean expected = (TestSimpleBean)expectedParam.getValue();
		TestSimpleBean actual = (TestSimpleBean)actualParam.getValue();
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
	
	/**
	 * Assert jf param.
	 *
	 * @param expected the expected
	 * @param actual the actual
	 */
	public static final  void assertJFParam(JFParam expected, JFParam actual) {
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
			Assert.assertArrayEquals((Object[])expected.getValue(), (Object[])actual.getValue());
		} else {
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
			System.out.println(mesg + "[SERIALIZE]: "
					+ (System.nanoTime() - startTime) + "("
					+ (System.currentTimeMillis() - startTimeMS) + ")");
		}

		/**
		 * End parse.
		 */
		public static void endParse() {
			System.out.println(mesg + "[PARSE]: "
					+ (System.nanoTime() - startTime) + "("
					+ (System.currentTimeMillis() - startTimeMS) + ")");
		}
	}


}
