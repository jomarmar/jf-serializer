/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.fringe.jf.test.core.json.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class TestComplexBean.
 */
public class TestComplexBean {
	private boolean[] dataBooleanArray = new boolean[]{true, false, true, false};
	private int[] dataIntegerArray = new int[]{10, 11, 12, 13};
	private long[] dataLongArray = new long[]{1110l, 1111l, 1112l, 1113l, 1114l};
	private float[] dataFloatArray = new float[]{1.1f, 1.2f, 1.3f, 1.4f};
	private double[] dataDoubleArray = new double[]{1.1d, 1.2d, 1.3d, 1.4d};
	private TestSimpleBean[] dataObjectArray = new TestSimpleBean[] {new TestSimpleBean(), new TestSimpleBean()};
	private HashMap<String, TestSimpleBean> dataHashMap = new HashMap<String, TestSimpleBean>();
	private Hashtable<String, HashMap<?, ?>> dataHashtable = new Hashtable<String, HashMap<?, ?>>();
	private List<String> dataList = new ArrayList<String>();
	private Vector<String> dataVector = new Vector<String>();
	
	
	
	/**
	 * Instantiates a new test complex bean.
	 */
	public TestComplexBean() {
		dataHashMap.put("HM1", new TestSimpleBean());
		dataHashMap.put("HM2", new TestSimpleBean());
		
		dataHashtable.put("HT1", dataHashMap);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("hm1", "hmvalue1");
		hm.put("hm2", "hmvalue2");
		dataHashtable.put("HT2", hm);
		
		dataList.add("ListItem1");
		dataList.add("ListItem2");
		dataList.add("ListItem3");
		
		dataVector.add("VectorItem1");
		dataVector.add("VectorItem2");
		dataVector.add("VectorItem3");
		
	}



	/**
	 * Gets the data boolean array.
	 *
	 * @return the data boolean array
	 */
	public boolean[] getDataBooleanArray() {
		return dataBooleanArray;
	}



	/**
	 * Sets the data boolean array.
	 *
	 * @param dataBooleanArray the new data boolean array
	 */
	public void setDataBooleanArray(boolean[] dataBooleanArray) {
		this.dataBooleanArray = dataBooleanArray;
	}



	/**
	 * Gets the data integer array.
	 *
	 * @return the data integer array
	 */
	public int[] getDataIntegerArray() {
		return dataIntegerArray;
	}



	/**
	 * Sets the data integer array.
	 *
	 * @param dataIntegerArray the new data integer array
	 */
	public void setDataIntegerArray(int[] dataIntegerArray) {
		this.dataIntegerArray = dataIntegerArray;
	}



	/**
	 * Gets the data long array.
	 *
	 * @return the data long array
	 */
	public long[] getDataLongArray() {
		return dataLongArray;
	}



	/**
	 * Sets the data long array.
	 *
	 * @param dataLongArray the new data long array
	 */
	public void setDataLongArray(long[] dataLongArray) {
		this.dataLongArray = dataLongArray;
	}



	/**
	 * Gets the data float array.
	 *
	 * @return the data float array
	 */
	public float[] getDataFloatArray() {
		return dataFloatArray;
	}



	/**
	 * Sets the data float array.
	 *
	 * @param dataFloatArray the new data float array
	 */
	public void setDataFloatArray(float[] dataFloatArray) {
		this.dataFloatArray = dataFloatArray;
	}



	/**
	 * Gets the data double array.
	 *
	 * @return the data double array
	 */
	public double[] getDataDoubleArray() {
		return dataDoubleArray;
	}



	/**
	 * Sets the data double array.
	 *
	 * @param dataDoubleArray the new data double array
	 */
	public void setDataDoubleArray(double[] dataDoubleArray) {
		this.dataDoubleArray = dataDoubleArray;
	}



	/**
	 * Gets the data object array.
	 *
	 * @return the data object array
	 */
	public TestSimpleBean[] getDataObjectArray() {
		return dataObjectArray;
	}



	/**
	 * Sets the data object array.
	 *
	 * @param dataObjectArray the new data object array
	 */
	public void setDataObjectArray(TestSimpleBean[] dataObjectArray) {
		this.dataObjectArray = dataObjectArray;
	}



	/**
	 * Gets the data hash map.
	 *
	 * @return the data hash map
	 */
	public HashMap<String, TestSimpleBean> getDataHashMap() {
		return dataHashMap;
	}



	/**
	 * Sets the data hash map.
	 *
	 * @param dataHashMap the data hash map
	 */
	public void setDataHashMap(HashMap<String, TestSimpleBean> dataHashMap) {
		this.dataHashMap = dataHashMap;
	}



	/**
	 * Gets the data hashtable.
	 *
	 * @return the data hashtable
	 */
	public Hashtable<String, HashMap<?, ?>> getDataHashtable() {
		return dataHashtable;
	}



	/**
	 * Sets the data hashtable.
	 *
	 * @param dataHashtable the data hashtable
	 */
	public void setDataHashtable(Hashtable<String, HashMap<?, ?>> dataHashtable) {
		this.dataHashtable = dataHashtable;
	}



	/**
	 * Gets the data list.
	 *
	 * @return the data list
	 */
	public List<String> getDataList() {
		return dataList;
	}



	/**
	 * Sets the data list.
	 *
	 * @param dataList the new data list
	 */
	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}



	/**
	 * Gets the data vector.
	 *
	 * @return the data vector
	 */
	public Vector<String> getDataVector() {
		return dataVector;
	}



	/**
	 * Sets the data vector.
	 *
	 * @param dataVector the new data vector
	 */
	public void setDataVector(Vector<String> dataVector) {
		this.dataVector = dataVector;
	}
	
	

}
