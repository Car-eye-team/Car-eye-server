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
 * 类名称：YstInfo    
 * 类描述：赢时通协议实体    
 * 创建人：zr    
 * 创建时间：2015-6-17 下午02:21:21    
 * 修改人：zr    
 * 修改时间：2015-6-17 下午02:21:21    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class YstInfo extends BaseInfo{
	
	/**服务器IP*/
	private String ip;
	
	/**本地端口号*/
	private int terminalport;
	
	/**服务器端口号*/
	private int serverport;
	
	/**文本消息*/
	private String contents;
	
	/**间隔*/
	private int interval;
	
	/**次数*/
	private int num;
	
	/**登陆状态*/
	private int state;
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;
	
	/**GPS 标志位*/
	private int flg;
	
	/**速度*/
	private int speed;
	
	/**方向*/
	private int direction;
	
	/**时间*/
	private String time;
	
	private int s00;
	
	private int s01;
	
	private int s02;
	
	private int s03;
	
	private int s10;
	
	private int s11;
	
	private int s12;
	
	private int s13;
	
	private int s20;
	private int s21;
	private int s30;
	private int s31;
	private int s32;
	private int s33;
	private int s40;
	private int s41;
	private int s42;
	private int s43;
	private int a00;
	private int a01;
	private int a02;
	private int a10;
	private int a11;
	private int a12;
	private int a13;
	private int a20;
	private int a21;
	private int a22;
	private int a23;
	private int a30;
	private int a31;
	private int a32;
	private int a33;
	private int a40;
	private int a41;
	
	/**里程*/
	private String mileage;
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
	public int getFlg() {
		return flg;
	}
	public void setFlg(int flg) {
		this.flg = flg;
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
	public int getS00() {
		return s00;
	}
	public void setS00(int s00) {
		this.s00 = s00;
	}
	public int getS01() {
		return s01;
	}
	public void setS01(int s01) {
		this.s01 = s01;
	}
	public int getS02() {
		return s02;
	}
	public void setS02(int s02) {
		this.s02 = s02;
	}
	public int getS03() {
		return s03;
	}
	public void setS03(int s03) {
		this.s03 = s03;
	}
	public int getS10() {
		return s10;
	}
	public void setS10(int s10) {
		this.s10 = s10;
	}
	public int getS11() {
		return s11;
	}
	public void setS11(int s11) {
		this.s11 = s11;
	}
	public int getS12() {
		return s12;
	}
	public void setS12(int s12) {
		this.s12 = s12;
	}
	public int getS13() {
		return s13;
	}
	public void setS13(int s13) {
		this.s13 = s13;
	}
	public int getS20() {
		return s20;
	}
	public void setS20(int s20) {
		this.s20 = s20;
	}
	public int getS21() {
		return s21;
	}
	public void setS21(int s21) {
		this.s21 = s21;
	}
	public int getS30() {
		return s30;
	}
	public void setS30(int s30) {
		this.s30 = s30;
	}
	public int getS31() {
		return s31;
	}
	public void setS31(int s31) {
		this.s31 = s31;
	}
	public int getS32() {
		return s32;
	}
	public void setS32(int s32) {
		this.s32 = s32;
	}
	public int getS33() {
		return s33;
	}
	public void setS33(int s33) {
		this.s33 = s33;
	}
	public int getS40() {
		return s40;
	}
	public void setS40(int s40) {
		this.s40 = s40;
	}
	public int getS41() {
		return s41;
	}
	public void setS41(int s41) {
		this.s41 = s41;
	}
	public int getS42() {
		return s42;
	}
	public void setS42(int s42) {
		this.s42 = s42;
	}
	public int getS43() {
		return s43;
	}
	public void setS43(int s43) {
		this.s43 = s43;
	}
	public int getA00() {
		return a00;
	}
	public void setA00(int a00) {
		this.a00 = a00;
	}
	public int getA01() {
		return a01;
	}
	public void setA01(int a01) {
		this.a01 = a01;
	}
	public int getA02() {
		return a02;
	}
	public void setA02(int a02) {
		this.a02 = a02;
	}
	public int getA10() {
		return a10;
	}
	public void setA10(int a10) {
		this.a10 = a10;
	}
	public int getA11() {
		return a11;
	}
	public void setA11(int a11) {
		this.a11 = a11;
	}
	public int getA12() {
		return a12;
	}
	public void setA12(int a12) {
		this.a12 = a12;
	}
	public int getA13() {
		return a13;
	}
	public void setA13(int a13) {
		this.a13 = a13;
	}
	public int getA20() {
		return a20;
	}
	public void setA20(int a20) {
		this.a20 = a20;
	}
	public int getA21() {
		return a21;
	}
	public void setA21(int a21) {
		this.a21 = a21;
	}
	public int getA22() {
		return a22;
	}
	public void setA22(int a22) {
		this.a22 = a22;
	}
	public int getA23() {
		return a23;
	}
	public void setA23(int a23) {
		this.a23 = a23;
	}
	public int getA30() {
		return a30;
	}
	public void setA30(int a30) {
		this.a30 = a30;
	}
	public int getA31() {
		return a31;
	}
	public void setA31(int a31) {
		this.a31 = a31;
	}
	public int getA32() {
		return a32;
	}
	public void setA32(int a32) {
		this.a32 = a32;
	}
	public int getA33() {
		return a33;
	}
	public void setA33(int a33) {
		this.a33 = a33;
	}
	public int getA40() {
		return a40;
	}
	public void setA40(int a40) {
		this.a40 = a40;
	}
	public int getA41() {
		return a41;
	}
	public void setA41(int a41) {
		this.a41 = a41;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public int getTerminalport() {
		return terminalport;
	}
	public void setTerminalport(int terminalport) {
		this.terminalport = terminalport;
	}
	public int getServerport() {
		return serverport;
	}
	public void setServerport(int serverport) {
		this.serverport = serverport;
	}

}
