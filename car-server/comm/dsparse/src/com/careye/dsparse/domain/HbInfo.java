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
 * 类名称：HbInfo    
 * 类描述：华宝协议实体    
 * 创建人：zr    
 * 创建时间：2015-6-16 上午11:29:02    
 * 修改人：zr    
 * 修改时间：2015-6-16 上午11:29:02    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HbInfo extends BaseInfo{
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;
	
	/**速度*/
	private int speed;
	
	/**方向*/
	private int azimuth;
	
	/**0 数据无效，1 数据有效*/
	private int flag;
	
	/**YY-MM-DD-hh-mm-ss(GMT+8时间，本标准之后涉及的时间均采用此时区)*/
	private String time;
	
	/***/
	private int oil;
	
	/**里程*/
	private String mileage;
	
	/**设备字符*/
	private String driverid;
	
	/**保留  转换为16进制表示*/
	private String status;
	
	/**被劫报警*/
	private int flg1;
	
	/**被盗报警*/
	private int flg2;
	
	/**区域报警*/
	private int flg3;
	
	/**剪线报警*/
	private int flg4;
	
	/**超速报警*/
	private int flg5;
	
	/**低压报警*/
	private int flg6;
	
	/**疲劳驾驶报警*/
	private int flg7;
	
	/***/
	private int flg8;
	
	/**回传周期*/
	private int circle;
	
	/**包序号*/
	private int packid;
	
	/**类型*/
	private int type;
	
	/**总帧数*/
	private int framenum;
	
	/**帧序号*/
	private int frameno;
	
	/**内容*/
	private String contents;
	
	/**采用GPRS传输的时间间隔，单位S*/
	private int tcpcircle;
	
	/**采用GSM传输的时间间隔，单位S*/
	private int smscircle;
	
	/**acc状态*/
	private int accstate;
	
	/**最大时速*/
	private int maxspeed;
	
	/**连续超速时间**/
	private String gsmno;
	
	/**IP*/
	private String ip;
	
	/**端口*/
	private int port;
	
	/**速度上限值*/
	private int speedlimit;

	public int getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getGsmno() {
		return gsmno;
	}

	public void setGsmno(String gsmno) {
		this.gsmno = gsmno;
	}

	public int getTcpcircle() {
		return tcpcircle;
	}

	public void setTcpcircle(int tcpcircle) {
		this.tcpcircle = tcpcircle;
	}

	public int getSmscircle() {
		return smscircle;
	}

	public void setSmscircle(int smscircle) {
		this.smscircle = smscircle;
	}

	public int getAccstate() {
		return accstate;
	}

	public void setAccstate(int accstate) {
		this.accstate = accstate;
	}

	public int getFlg1() {
		return flg1;
	}

	public void setFlg1(int flg1) {
		this.flg1 = flg1;
	}

	public int getFlg2() {
		return flg2;
	}

	public void setFlg2(int flg2) {
		this.flg2 = flg2;
	}

	public int getFlg3() {
		return flg3;
	}

	public void setFlg3(int flg3) {
		this.flg3 = flg3;
	}

	public int getFlg4() {
		return flg4;
	}

	public void setFlg4(int flg4) {
		this.flg4 = flg4;
	}

	public int getFlg5() {
		return flg5;
	}

	public void setFlg5(int flg5) {
		this.flg5 = flg5;
	}

	public int getFlg6() {
		return flg6;
	}

	public void setFlg6(int flg6) {
		this.flg6 = flg6;
	}

	public int getFlg7() {
		return flg7;
	}

	public void setFlg7(int flg7) {
		this.flg7 = flg7;
	}

	public int getFlg8() {
		return flg8;
	}

	public void setFlg8(int flg8) {
		this.flg8 = flg8;
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

	public int getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(int azimuth) {
		this.azimuth = azimuth;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getOil() {
		return oil;
	}

	public void setOil(int oil) {
		this.oil = oil;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCircle() {
		return circle;
	}

	public void setCircle(int circle) {
		this.circle = circle;
	}

	public int getPackid() {
		return packid;
	}

	public void setPackid(int packid) {
		this.packid = packid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFramenum() {
		return framenum;
	}

	public void setFramenum(int framenum) {
		this.framenum = framenum;
	}

	public int getFrameno() {
		return frameno;
	}

	public void setFrameno(int frameno) {
		this.frameno = frameno;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public int getSpeedlimit() {
		return speedlimit;
	}

	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}
	
	
}
