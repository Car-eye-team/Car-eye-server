/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PositionInfoItems    
 * 类描述： 位置信息附加信息   
 * 创建人：zr    
 * 创建时间：2015-9-6 下午03:10:21    
 * 修改人：zr    
 * 修改时间：2015-9-6 下午03:10:21    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PositionInfoItems {
	
	/**附加信息ID*/
	private Integer aid;
	
	/**附加信息长度*/
	private Integer alen;
	
	/**附加信息*/
	private String avalue;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAlen() {
		return alen;
	}

	public void setAlen(Integer alen) {
		this.alen = alen;
	}

	public String getAvalue() {
		return avalue;
	}

	public void setAvalue(String avalue) {
		this.avalue = avalue;
	}
	
	
}
