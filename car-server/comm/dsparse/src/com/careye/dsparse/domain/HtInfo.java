/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：HtInfo    
 * 类描述：恒天高科GPRS工业采集控制器HT-GPRS-V1.3协议实体    
 * 创建人：zr    
 * 创建时间：2015-11-24 上午10:58:04    
 * 修改人：zr    
 * 修改时间：2015-11-24 上午10:58:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HtInfo extends BaseInfo{
	
	/**目前电机开关的状态 0x00:电机关机 0x01:电机开机*/
	private int state;
	
	/**第1路电路值*/
	private int value1;
	
	/**第2路电路值*/
	private int value2;
	
	/**第3路电路值*/
	private int value3;
	
	/**第4路电路值*/
	private int value4;
	
	/**结果 0x00:失败； 0x01:成功*/
	private int result;
	
	/**内容*/
	private String content;
	
	/**IP字符串长度*/
	private int iplen;
	
	/**IP字符串*/
	private String ip;
	
	/**端口号*/
	private int port;
	
	/**工厂名*/
	private int gcm;
	
	/**分厂名*/
	private int fgcm;
	
	/**采集时间间隔*/
	private int interval;
	
	/**软件版本信息长度*/
	private int verlen;
	
	/**软件版本信息内容*/
	private String verinfo;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public int getValue3() {
		return value3;
	}

	public void setValue3(int value3) {
		this.value3 = value3;
	}

	public int getValue4() {
		return value4;
	}

	public void setValue4(int value4) {
		this.value4 = value4;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIplen() {
		return iplen;
	}

	public void setIplen(int iplen) {
		this.iplen = iplen;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getGcm() {
		return gcm;
	}

	public void setGcm(int gcm) {
		this.gcm = gcm;
	}

	public int getFgcm() {
		return fgcm;
	}

	public void setFgcm(int fgcm) {
		this.fgcm = fgcm;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getVerlen() {
		return verlen;
	}

	public void setVerlen(int verlen) {
		this.verlen = verlen;
	}

	public String getVerinfo() {
		return verinfo;
	}

	public void setVerinfo(String verinfo) {
		this.verinfo = verinfo;
	}
	
}
