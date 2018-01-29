/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;


/**    
 *     
 * 项目名称：dsparse    
 * 类名称：HttxInfo    
 * 类描述：深圳市航天无线通信技术有限公司213GD协议实体    
 * 创建人：Administrator    
 * 创建时间：2015-12-23 下午06:02:14    
 * 修改人：Administrator    
 * 修改时间：2015-12-23 下午06:02:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HttxStatData{
	
	/**最近 ACC点火时间*/
	private String lastaccontime;
	
	/**设备时间*/
	private String utctime;
	
	/**累计里程*/
	private String totaltripmileage;
	
	/**当前里程*/
	private String currenttripmileage;
	
	/**累计油耗*/
	private String totalfuel;
	
	/**当前油耗*/
	private String currentfuel;
	
	
	private int s00;
	private int s01;
	private int s02;
	private int s03;
	private int s04;
	private int s05;
	private int s06;
	private int s07;
	
	private int s10;
	private int s11;
	private int s12;
	private int s13;
	private int s14;
	private int s15;
	private int s16;
	private int s17;
	
	private int s20;
	private int s21;
	private int s22;
	private int s23;
	private int s24;
	private int s25;
	private int s26;
	private int s27;
	
	/**发动机诊断协议类型*/
	private int protocoltype;
	
	/**obd请求成功比例*/
	private int request;
	
	/**参数错误的次数*/
	private int error;
	
	/**代码*/
	private int code;
	
	/**信号强度*/
	private int signal;
	
	/**误码率*/
	private int ber;
	
	private int b0;
	private int b1;
	private int b2;
	private int b3;
	private int b4;
	private int b5;
	private int b6;
	private int b7;
	public String getLastaccontime() {
		return lastaccontime;
	}
	public void setLastaccontime(String lastaccontime) {
		this.lastaccontime = lastaccontime;
	}
	public String getUtctime() {
		return utctime;
	}
	public void setUtctime(String utctime) {
		this.utctime = utctime;
	}
	public String getTotaltripmileage() {
		return totaltripmileage;
	}
	public void setTotaltripmileage(String totaltripmileage) {
		this.totaltripmileage = totaltripmileage;
	}
	public String getCurrenttripmileage() {
		return currenttripmileage;
	}
	public void setCurrenttripmileage(String currenttripmileage) {
		this.currenttripmileage = currenttripmileage;
	}
	public String getTotalfuel() {
		return totalfuel;
	}
	public void setTotalfuel(String totalfuel) {
		this.totalfuel = totalfuel;
	}
	public String getCurrentfuel() {
		return currentfuel;
	}
	public void setCurrentfuel(String currentfuel) {
		this.currentfuel = currentfuel;
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
	public int getS04() {
		return s04;
	}
	public void setS04(int s04) {
		this.s04 = s04;
	}
	public int getS05() {
		return s05;
	}
	public void setS05(int s05) {
		this.s05 = s05;
	}
	public int getS06() {
		return s06;
	}
	public void setS06(int s06) {
		this.s06 = s06;
	}
	public int getS07() {
		return s07;
	}
	public void setS07(int s07) {
		this.s07 = s07;
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
	public int getProtocoltype() {
		return protocoltype;
	}
	public void setProtocoltype(int protocoltype) {
		this.protocoltype = protocoltype;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
	public int getBer() {
		return ber;
	}
	public void setBer(int ber) {
		this.ber = ber;
	}
	public int getB0() {
		return b0;
	}
	public void setB0(int b0) {
		this.b0 = b0;
	}
	public int getB1() {
		return b1;
	}
	public void setB1(int b1) {
		this.b1 = b1;
	}
	public int getB2() {
		return b2;
	}
	public void setB2(int b2) {
		this.b2 = b2;
	}
	public int getB3() {
		return b3;
	}
	public void setB3(int b3) {
		this.b3 = b3;
	}
	public int getB4() {
		return b4;
	}
	public void setB4(int b4) {
		this.b4 = b4;
	}
	public int getB5() {
		return b5;
	}
	public void setB5(int b5) {
		this.b5 = b5;
	}
	public int getB6() {
		return b6;
	}
	public void setB6(int b6) {
		this.b6 = b6;
	}
	public int getB7() {
		return b7;
	}
	public void setB7(int b7) {
		this.b7 = b7;
	}
	
	
}
