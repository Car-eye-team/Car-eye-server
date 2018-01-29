/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：CsmbInfoItems    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-3-18 下午01:34:55    
 * 修改人：Administrator    
 * 修改时间：2016-3-18 下午01:34:55    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CsmbInfoItems {
	
	/**序号*/
	private int sn;
	
	/**亲情号码*/
	private String phone;

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
