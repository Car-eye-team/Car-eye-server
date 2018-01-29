/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */



package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：AuthInfo    
 * 类描述：鉴权信息实体    
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:26:05    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:26:05    
 * 修改备注：    
 * @version 1.0  
 *      
 */
public class Auth{
	
	/**鉴权密码*/
	private String passwd;
	
	/**版本号*/
	private String version;
	
	/**时间戳*/
	private String timestamp;
	
	/**SIM卡号*/
	private String sim;
	
	/**设备编号*/
	private String deviceid;
	
	/**登录原因*/
	private int reason;
	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
