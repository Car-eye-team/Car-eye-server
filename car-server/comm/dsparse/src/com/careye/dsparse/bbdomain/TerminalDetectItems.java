/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TerminalDetectItems    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-10-16 上午10:57:39    
 * 修改人：Administrator    
 * 修改时间：2015-10-16 上午10:57:39    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalDetectItems {
	
	/**参数ID*/
	private int id;
	
	/**参数长度*/
	private int len;
	
	/**参数值*/
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
