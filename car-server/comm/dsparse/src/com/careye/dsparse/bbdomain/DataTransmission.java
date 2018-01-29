/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：DataTransmission    
 * 类描述： 数据透传   
 * 创建人：zr    
 * 创建时间：2015-6-6 下午03:05:33    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午03:05:33    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DataTransmission extends BaseInfo{

	/**透传消息类型*/
	private int type;
	
	/**厂商标识*/
	private int mid;
	
	/**透传消息内容*/
	private String data;
	

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
