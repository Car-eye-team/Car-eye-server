/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ArrayInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-16 下午02:27:41    
 * 修改人：zr    
 * 修改时间：2015-5-16 下午02:27:41    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ParameterInfo {
	
	/**参数ID*/
	private int id;
	
	/**参数长度*/
	private int size;
	
	/**参数值*/
	private String value;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
