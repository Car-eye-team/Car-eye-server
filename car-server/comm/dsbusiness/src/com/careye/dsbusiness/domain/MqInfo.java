/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsbusiness.domain;

/**    
 *     
 * 项目名称：dsbusiness    
 * 类名称：MqInfo    
 * 类描述：队列信息实体    
 * 创建人：zr    
 * 创建时间：2015-5-12 下午02:03:42    
 * 修改人：zr    
 * 修改时间：2015-5-12 下午02:03:42    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MqInfo {
	
	/**
	 * 队列名称
	 */
	private String mqName;
	
	/**
	 * 服务名称
	 */
	private String serverName;
	
	/**
	 * 设备类型
	 */
	private String deviceType;

	public String getMqName() {
		return mqName;
	}

	public void setMqName(String mqName) {
		this.mqName = mqName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
}
