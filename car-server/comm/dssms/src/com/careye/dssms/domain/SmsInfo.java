/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.domain;

import com.careye.dssms.constant.BaseInfo;

/**    
 *     
 * 项目名称：dssms    
 * 类名称：SmsInfo    
 * 类描述：短信实体    
 * 创建人：zr    
 * 创建时间：2015-6-25 下午03:07:26    
 * 修改人：zr    
 * 修改时间：2015-6-25 下午03:07:26    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SmsInfo extends BaseInfo{
	
	/**接收手机号*/
	private String phone;
	
	/**来源*/
	private int source;
	
	/**短信内容*/
	private String content;
	
	/**发送结果*/
	private int result;
	
	/**描述信息*/
	private String description;
	
	/**流水号*/
	private String serialNumber;
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
