/*
 * jf-serializer
 * 
 * @author jomarmar (jomarmar@gmail.com)
 */
package org.jemz.jf.test.core.json.data;


// TODO: Auto-generated Javadoc
/**
 * The Class TestCompoundBean.
 */
public class TestCompoundBean {
	private String dataString = new String(new byte[]{0x02, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35});
	private TestSimpleBean simpleBean = new TestSimpleBean();
	
	
	/**
	 * Gets the data string.
	 *
	 * @return the data string
	 */
	public String getDataString() {
		return dataString;
	}
	
	/**
	 * Sets the data string.
	 *
	 * @param dataString the new data string
	 */
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	
	/**
	 * Gets the simple bean.
	 *
	 * @return the simple bean
	 */
	public TestSimpleBean getSimpleBean() {
		return simpleBean;
	}
	
	/**
	 * Sets the simple bean.
	 *
	 * @param simpleBean the new simple bean
	 */
	public void setSimpleBean(TestSimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}

}
