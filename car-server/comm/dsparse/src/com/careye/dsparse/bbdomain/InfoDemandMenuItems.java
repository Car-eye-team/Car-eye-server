/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：InfoDemandMenuItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午07:42:14    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午07:42:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class InfoDemandMenuItems {

	/** 信息类型    **/
	private  int type;
	
	/**  信息名称长度    **/
	private  int  len;
	
	/**  信息名称    **/
	private  String content;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
