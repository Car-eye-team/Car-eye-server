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
 * 类名称：BsjInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-15 下午02:06:26    
 * 修改人：zr    
 * 修改时间：2015-6-15 下午02:06:26    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BsjInfo extends BaseInfo{

	/**文本消息内容*/
	private String contents;

	/**时间间隔*/
	private int interval;

	/**设置速度*/
	private int speedlimit;

	/**里程*/
	private String mileage;

	/**IP*/
	private String ip;

	/**端口*/
	private int terminalport;

	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/**速度*/
	private int speed;

	/**方向*/
	private int direction;

	/**时间*/
	private String time;

	/**GPS*/
	private int d7;

	/**GPS报警*/
	private int d65;

	/**电源报警*/
	private int d43;

	/**D7=1 ACC 关、D7=0  ACC开*/
	private int a7;

	/**D6=1自定义1路高传感器状态为低(正常)、D6=0自定义1路高传感器状态为高(触发)*/
	private int a6;

	/**D5=1自定义2路高传感器状态为低(正常)、D5=0自定义2路高传感器状态为高(触发)  注:A08型终端不支持*/
	private int a5;

	/**D4=1自定义1路低传感器状态为高(正常)、D4=0自定义1路低传感器状态为低(触发)*/
	private int a4;

	/**D3=1自定义2路低传感器状态为高(正常)、D3=0自定义2路低传感器状态为低(触发)*/
	private int a3;

	/**D2=1油路正常/没有超速提示、   D2=0油路断开/超速提示*/
	private int a2;

	/**D1=1没有登签、   D1=0已登签*/
	private int a1;

	/**D0=1油路控制模式 D0=0超速提示控制模式*/
	private int a0;

	/**D7=0 劫警报警、D7=1正常   */
	private int b7;

	/**D6=0 超速报警、D6=1正常*/
	private int b6;
	
	/**D5=0停车超长报警、D5=1正常 */
	private int b5;

	/**温度1*/
	private int t1;

	/**温度2*/
	private int t2;

	/**温度3*/
	private int t3;

	/**温度4*/
	private int t4;



	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getSpeedlimit() {
		return speedlimit;
	}

	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTerminalport() {
		return terminalport;
	}

	public void setTerminalport(int terminalport) {
		this.terminalport = terminalport;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getD7() {
		return d7;
	}

	public void setD7(int d7) {
		this.d7 = d7;
	}

	public int getD65() {
		return d65;
	}

	public void setD65(int d65) {
		this.d65 = d65;
	}

	public int getD43() {
		return d43;
	}

	public void setD43(int d43) {
		this.d43 = d43;
	}

	public int getA7() {
		return a7;
	}

	public void setA7(int a7) {
		this.a7 = a7;
	}

	public int getA6() {
		return a6;
	}

	public void setA6(int a6) {
		this.a6 = a6;
	}

	public int getA5() {
		return a5;
	}

	public void setA5(int a5) {
		this.a5 = a5;
	}

	public int getA4() {
		return a4;
	}

	public void setA4(int a4) {
		this.a4 = a4;
	}

	public int getA3() {
		return a3;
	}

	public void setA3(int a3) {
		this.a3 = a3;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA0() {
		return a0;
	}

	public void setA0(int a0) {
		this.a0 = a0;
	}

	public int getB7() {
		return b7;
	}

	public void setB7(int b7) {
		this.b7 = b7;
	}

	public int getB6() {
		return b6;
	}

	public void setB6(int b6) {
		this.b6 = b6;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}

	public int getT4() {
		return t4;
	}

	public void setT4(int t4) {
		this.t4 = t4;
	}

	public int getB5() {
		return b5;
	}

	public void setB5(int b5) {
		this.b5 = b5;
	}
	
	


}
