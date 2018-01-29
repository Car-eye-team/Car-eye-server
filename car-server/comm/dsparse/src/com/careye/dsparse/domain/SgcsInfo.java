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
 * 类名称：SgcsInfo    
 * 类描述：赛格车圣空中传输通信协议实体    
 * 创建人：Administrator    
 * 创建时间：2016-5-18 下午07:26:41    
 * 修改人：Administrator    
 * 修改时间：2016-5-18 下午07:26:41    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SgcsInfo extends BaseInfo{
	
	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;
	
	/**速度*/
	private String speed;

	/**方向*/
	private int direction;

	/**时间*/
	private String time;
	
	/**0	GPS未定位1	GPS已定位*/
	private int gpsflg;
	
	private int s0;
	private int s1;
	private int s2;
	private int s3;
	private int s4;
	private int s5;
	private int s6;
	private int s7;
	private int s8;
	private int s9;
	private int s10;
	private int s11;
	private int s12;
	private int s13;
	private int s14;
	private int s15;
	private int s16;
	private int s17;
	private int s18;
	private int s19;
	private int s20;
	private int s21;
	private int s22;
	private int s23;
	private int s24;
	private int s25;
	private int s26;
	private int s27;
	private int s28;
	private int s29;
	private int s30;
	private int s31;
	private int s32;
	
	private int count;
	
	/**间隔*/
	private int interval;
	
	/**终端电话号码*/
	private String telephone;
	
	/**IP地址*/
	private String ip;
	
	/**服务器端口号*/
	private int port;
	
	/**APN*/
	private String apn;
	
	/**限制速度*/
	private int speedlimit;
	
	
	public int getSpeedlimit() {
		return speedlimit;
	}
	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
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
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
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
	public int getGpsflg() {
		return gpsflg;
	}
	public void setGpsflg(int gpsflg) {
		this.gpsflg = gpsflg;
	}
	public int getS0() {
		return s0;
	}
	public void setS0(int s0) {
		this.s0 = s0;
	}
	public int getS1() {
		return s1;
	}
	public void setS1(int s1) {
		this.s1 = s1;
	}
	public int getS2() {
		return s2;
	}
	public void setS2(int s2) {
		this.s2 = s2;
	}
	public int getS3() {
		return s3;
	}
	public void setS3(int s3) {
		this.s3 = s3;
	}
	public int getS4() {
		return s4;
	}
	public void setS4(int s4) {
		this.s4 = s4;
	}
	public int getS5() {
		return s5;
	}
	public void setS5(int s5) {
		this.s5 = s5;
	}
	public int getS6() {
		return s6;
	}
	public void setS6(int s6) {
		this.s6 = s6;
	}
	public int getS7() {
		return s7;
	}
	public void setS7(int s7) {
		this.s7 = s7;
	}
	public int getS8() {
		return s8;
	}
	public void setS8(int s8) {
		this.s8 = s8;
	}
	public int getS9() {
		return s9;
	}
	public void setS9(int s9) {
		this.s9 = s9;
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
	public int getS14() {
		return s14;
	}
	public void setS14(int s14) {
		this.s14 = s14;
	}
	public int getS15() {
		return s15;
	}
	public void setS15(int s15) {
		this.s15 = s15;
	}
	public int getS16() {
		return s16;
	}
	public void setS16(int s16) {
		this.s16 = s16;
	}
	public int getS17() {
		return s17;
	}
	public void setS17(int s17) {
		this.s17 = s17;
	}
	public int getS18() {
		return s18;
	}
	public void setS18(int s18) {
		this.s18 = s18;
	}
	public int getS19() {
		return s19;
	}
	public void setS19(int s19) {
		this.s19 = s19;
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
	public int getS22() {
		return s22;
	}
	public void setS22(int s22) {
		this.s22 = s22;
	}
	public int getS23() {
		return s23;
	}
	public void setS23(int s23) {
		this.s23 = s23;
	}
	public int getS24() {
		return s24;
	}
	public void setS24(int s24) {
		this.s24 = s24;
	}
	public int getS25() {
		return s25;
	}
	public void setS25(int s25) {
		this.s25 = s25;
	}
	public int getS26() {
		return s26;
	}
	public void setS26(int s26) {
		this.s26 = s26;
	}
	public int getS27() {
		return s27;
	}
	public void setS27(int s27) {
		this.s27 = s27;
	}
	public int getS28() {
		return s28;
	}
	public void setS28(int s28) {
		this.s28 = s28;
	}
	public int getS29() {
		return s29;
	}
	public void setS29(int s29) {
		this.s29 = s29;
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
	
	
}
