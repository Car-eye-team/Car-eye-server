/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */



package com.careye.redis.domain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsauth    
 * 类名称：AuthInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-11 下午01:46:08    
 * 修改人：zr    
 * 修改时间：2015-5-11 下午01:46:08    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class AuthInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**鉴权表自增ID*/
	private int id;
	
	/**鉴权设备号*/
	private String terminal;
	
	/**终端设备类型*/
	private int deviceType;
	
	/**json协议*/
	private String jsonMsg;
	
	/**平台类型*/
	private String platformType;
	
	/**车架号*/
	private String vin;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getJsonMsg() {
		return jsonMsg;
	}

	public void setJsonMsg(String jsonMsg) {
		this.jsonMsg = jsonMsg;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	

}
