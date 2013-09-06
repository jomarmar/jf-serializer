/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.test.core.json.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class JFTestData.
 */
public class JFTestData {

		// Basic Types 
		/** The Constant nullTest. */
		public static final Object		nullTest		= null;
		
		/** The Constant intTest. */
		public static final Integer 	intTest 		= new Integer(5);
		
		/** The Constant longTest. */
		public static final Long 		longTest 		= new Long(12344222122L);
		
		/** The Constant floatTest. */
		public static final Float 		floatTest 		= new Float(3.23f);
		
		/** The Constant doubleTest. */
		public static final Double 		doubleTest 		= new Double(0.67844d);
		
		/** The Constant boolTest. */
		public static final Boolean 	boolTest  		= Boolean.TRUE;
		
		/** The Constant charTest. */
		public static final Character	charTest		= new Character('A');
		
		/** The Constant dateTest. */
		public static final Date		dateTest		= new Date(1329685550793L);
		
		/** The Constant byteTest. */
		public static final byte 		byteTest		= (byte)0x20;
		
		/** The Constant byteATest. */
		public static final byte[] 		byteATest		= new byte[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35};
		
		/** The Constant intATest. */
		public static final int[]		intATest		= new int[]{0, 1, 2, 3, 4, 5, 6, 7};
		
		/** The Constant longATest. */
		public static final long[]		longATest		= new long[]{111111, 222222, 333333};
		
		/** The Constant floatATest. */
		public static final float[]		floatATest		= new float[]{111111f, 222222f, 333333f};
		
		/** The Constant doubleATest. */
		public static final double[]	doubleATest		= new double[]{111111d, 222222d, 333333d};
		
		/** The Constant boolATest. */
		public static final boolean[]	boolATest		= new boolean[]{true, false, true};
		
		/** The Constant charATest. */
		public static final char[]		charATest		= new char[]{'A', 'B', 'C'};
		
		/** The Constant stringATest. */
		public static final String[] 	stringATest		= new String[]{"test1", "test2", "test3"};
		
		/** The Constant emptyIntATest. */
		public static final int[]					emptyIntATest	= new int[0];
		
		/** The Constant emptyStringATest. */
		public static final String[] 				emptyStringATest	= new String[0];
		
		/** The Constant beanTest. */
		public static final TestSimpleBean 		beanTest			= new TestSimpleBean();
		
		/** The Constant emptyBeanATest. */
		public static final TestSimpleBean[]		emptyBeanATest	= new TestSimpleBean[0];
		
//

		/** The Constant complexBeanTest. */
		public static final TestComplexBean 	complexBeanTest		= new TestComplexBean();
		
		/** The Constant compoundBeanTest. */
		public static final TestCompoundBean 	compoundBeanTest	= new TestCompoundBean();
		
		/** The Constant beanATest. */
		public static final TestSimpleBean[] 	beanATest			= new TestSimpleBean[]{beanTest, beanTest};
		
		private static Vector<String>				vectTest	= null;
		private static List<String>					listTest	= null;
		private static HashMap<String, String>		mapTest		= null;
		private static Hashtable<String, String>	tableTest	= null;

		private JFTestData() {
			
		}
		
		/**
		 * Gets the string.
		 *
		 * @return the string
		 */
		public static String getString() {
			return new String(new byte[]{(byte)0xE6, (byte)0xAC, (byte) 0xA2, (byte)0xE8, (byte)0xBF, (byte) 0x8E, (byte)0x20, (byte)0x54, (byte)0x65, (byte)0x73, (byte)0x74, (byte)0x20, (byte)0x4A, (byte)0x46});
		}
		
		
		/**
		 * Gets the vector test.
		 *
		 * @return the vector test
		 */
		public static synchronized Vector<String> getVectorTest() {
			if(vectTest == null) {
				vectTest = new Vector<String>();
				vectTest.add("1");
				vectTest.add("2");
			}
			return vectTest;
		}
		
		/**
		 * Gets the list test.
		 *
		 * @return the list test
		 */
		public static synchronized List<String> getListTest() {
			if(listTest == null) {
				listTest = new ArrayList<String>();	
				listTest.add("1");
				listTest.add("2");
			}
			return listTest;
		}
		
		/**
		 * Gets the table test.
		 *
		 * @return the table test
		 */
		public static synchronized Hashtable<String, String> getTableTest() {
			if(tableTest == null) {
				tableTest = new Hashtable<String, String>();
				tableTest.put("T1", "Value1");
				tableTest.put("T2", "Value2");
			}
			return tableTest;
		}
		
		/**
		 * Gets the map test.
		 *
		 * @return the map test
		 */
		public static synchronized Map<String, String> getMapTest() {
			if(mapTest == null) {
				mapTest = new HashMap<String, String>();
				mapTest.put("M1", "Value1");
				mapTest.put("M2", "Value2");
			}
			return mapTest;
		}
		
}
