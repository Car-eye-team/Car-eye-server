/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PositionInfo    
 * 类描述：位置信息实体    
 * 创建人：zr    
 * 创建时间：2015-5-14 下午05:43:50    
 * 修改人：zr    
 * 修改时间：2015-5-14 下午05:43:50    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PositionInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**应答流水号*/
	private int seqR;
	
	/**报警信息*/
	private String alarminfo;
	
	/**状态信息*/
	private String stateinfo;
	
	/**1：紧急报警，触动报警开关后触发*/
	private int a0;
	
	/**1：超速报警*/
	private int a1;
	
	/**1：疲劳驾驶*/
	private int a2;
	
	/**1：危险预警*/
	private int a3;
	
	/**1：GNSS 模块发生故障*/
	private int a4;
	
	/**1：GNSS 天线未接或被剪断*/
	private int a5;
	
	/**1：GNSS 天线短路*/
	private int a6;
	
	/**1：终端主电源欠压*/
	private int a7;
	
	/**1：终端主电源掉电*/
	private int a8;
	
	/**1：终端LCD 或显示器故障*/
	private int a9;
	
	/**1：TTS 模块故障*/
	private int a10;
	
	/**1：摄像头故障*/
	private int a11;
	
	/**1：道路运输证IC 卡模块故障*/
	private int a12;
	
	/**1：超速预警*/
	private int a13;
	
	/**1：疲劳驾驶预警*/
	private int a14;
	
	/**保留*/
	private int a15;
	
	/**保留*/
	private int a16;
	
	/**保留*/
	private int a17;
	
	/**1：当天累计驾驶超时*/
	private int a18;
	
	/**1：超时停车*/
	private int a19;
	
	/**1：进出区域*/
	private int a20;
	
	/**1：进出路线*/
	private int a21;
	
	/**1：路段行驶时间不足/过长*/
	private int a22;
	
	/**1：路线偏离报警*/
	private int a23;
	
	/**1：车辆VSS 故障*/
	private int a24;
	
	/**1：车辆油量异常*/
	private int a25;
	
	/**1：车辆被盗(通过车辆防盗器)*/
	private int a26;
	
	/**1：车辆非法点火*/
	private int a27;
	
	/**1：车辆非法位移*/
	private int a28;
	
	/**1：碰撞预警*/
	private int a29;
	
	/**1：侧翻预警*/
	private int a30;
	
	/**1：非法开门报警（终端未设置区域时，不判断非法开门）*/
	private int a31;
	
	/**0：ACC 关；1： ACC 开*/
	private Integer s0;
	
	/**0：未定位；1：定位*/
	private Integer s1;
	
	/**0：北纬；1：南纬*/
	private Integer s2;
	
	/**0：东经；1：西经*/
	private Integer s3;
	
	/**0：运营状态；1：停运状态*/
	private Integer s4;
	
	/**0：经纬度未经保密插件加密；1：经纬度已经保密插件加密*/
	private Integer s5;
	
	/**保留*/
	private Integer s6;
	
	/**保留*/
	private Integer s7;
	
	/**00：空车；01：半载；10：保留；11：满载*/
	private Integer s8;
	
	/**00：空车；01：半载；10：保留；11：满载*/
	private Integer s9;
	
	/**0：车辆油路正常；1：车辆油路断开*/
	private Integer s10;
	
	/**0：车辆电路正常；1：车辆电路断开*/
	private Integer s11;
	
	/**0：车门解锁；1：车门加锁*/
	private Integer s12;
	
	/**0：门1 关；1：门1 开（前门）*/
	private Integer s13;
	
	/**0：门2 关；1：门2 开（中门）*/
	private Integer s14;
	
	/**0：门3 关；1：门3 开（后门）*/
	private Integer s15;
	
	/**0：门4 关；1：门4 开（驾驶席门）*/
	private Integer s16;
	
	/**0：门5 关；1：门5 开（自定义）*/
	private Integer s17;
	
	/**0：未使用GPS 卫星进行定位；1：使用GPS 卫星进行定位*/
	private Integer s18;
	
	/**0：未使用北斗卫星进行定位；1：使用北斗卫星进行定位*/
	private Integer s19;
	
	/**0：未使用GLONASS 卫星进行定位；1：使用GLONASS 卫星进行定位*/
	private Integer s20;
	
	/**0：未使用Galileo 卫星进行定位；1：使用Galileo 卫星进行定位*/
	private Integer s21;
	
	/**保留*/
	private Integer s22;
	
	/**保留*/
	private Integer s23;
	
	/**保留*/
	private Integer s24;
	
	/**保留*/
	private Integer s25;
	
	/**保留*/
	private Integer s26;
	
	/**保留*/
	private Integer s27;
	
	/**保留*/
	private Integer s28;
	
	/**保留*/
	private Integer s29;
	
	/**保留*/
	private Integer s30;
	
	/**保留*/
	private Integer s31;
	
	/**保留*/
	private Integer s32;
	
	/***/
	private Integer s33;
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;
	
	/**高程*/
	private int altitude;
	
	/**速度*/
	private String speed;
	
	/**方向*/
	private int direction;
	
	/**时间*/
	private String time;
	
	/**里程*/
	private String mileage;
	
	/**附加信息*/
	private  List<PositionInfoItems> items = new ArrayList<PositionInfoItems>();

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public Integer getS0() {
		return s0;
	}

	public void setS0(Integer s0) {
		this.s0 = s0;
	}

	public Integer getS1() {
		return s1;
	}

	public void setS1(Integer s1) {
		this.s1 = s1;
	}

	public Integer getS2() {
		return s2;
	}

	public void setS2(Integer s2) {
		this.s2 = s2;
	}

	public Integer getS3() {
		return s3;
	}

	public void setS3(Integer s3) {
		this.s3 = s3;
	}

	public Integer getS4() {
		return s4;
	}

	public void setS4(Integer s4) {
		this.s4 = s4;
	}

	public Integer getS5() {
		return s5;
	}

	public void setS5(Integer s5) {
		this.s5 = s5;
	}

	public Integer getS6() {
		return s6;
	}

	public void setS6(Integer s6) {
		this.s6 = s6;
	}

	public Integer getS7() {
		return s7;
	}

	public void setS7(Integer s7) {
		this.s7 = s7;
	}

	public Integer getS8() {
		return s8;
	}

	public void setS8(Integer s8) {
		this.s8 = s8;
	}

	public Integer getS9() {
		return s9;
	}

	public void setS9(Integer s9) {
		this.s9 = s9;
	}

	public Integer getS10() {
		return s10;
	}

	public void setS10(Integer s10) {
		this.s10 = s10;
	}

	public Integer getS11() {
		return s11;
	}

	public void setS11(Integer s11) {
		this.s11 = s11;
	}

	public Integer getS12() {
		return s12;
	}

	public void setS12(Integer s12) {
		this.s12 = s12;
	}

	public Integer getS13() {
		return s13;
	}

	public void setS13(Integer s13) {
		this.s13 = s13;
	}

	public Integer getS14() {
		return s14;
	}

	public void setS14(Integer s14) {
		this.s14 = s14;
	}

	public Integer getS15() {
		return s15;
	}

	public void setS15(Integer s15) {
		this.s15 = s15;
	}

	public Integer getS16() {
		return s16;
	}

	public void setS16(Integer s16) {
		this.s16 = s16;
	}

	public Integer getS17() {
		return s17;
	}

	public void setS17(Integer s17) {
		this.s17 = s17;
	}

	public Integer getS18() {
		return s18;
	}

	public void setS18(Integer s18) {
		this.s18 = s18;
	}

	public Integer getS19() {
		return s19;
	}

	public void setS19(Integer s19) {
		this.s19 = s19;
	}

	public Integer getS20() {
		return s20;
	}

	public void setS20(Integer s20) {
		this.s20 = s20;
	}

	public Integer getS21() {
		return s21;
	}

	public void setS21(Integer s21) {
		this.s21 = s21;
	}

	public Integer getS22() {
		return s22;
	}

	public void setS22(Integer s22) {
		this.s22 = s22;
	}

	public Integer getS23() {
		return s23;
	}

	public void setS23(Integer s23) {
		this.s23 = s23;
	}

	public Integer getS24() {
		return s24;
	}

	public void setS24(Integer s24) {
		this.s24 = s24;
	}

	public Integer getS25() {
		return s25;
	}

	public void setS25(Integer s25) {
		this.s25 = s25;
	}

	public Integer getS26() {
		return s26;
	}

	public void setS26(Integer s26) {
		this.s26 = s26;
	}

	public Integer getS27() {
		return s27;
	}

	public void setS27(Integer s27) {
		this.s27 = s27;
	}

	public Integer getS28() {
		return s28;
	}

	public void setS28(Integer s28) {
		this.s28 = s28;
	}

	public Integer getS29() {
		return s29;
	}

	public void setS29(Integer s29) {
		this.s29 = s29;
	}

	public Integer getS30() {
		return s30;
	}

	public void setS30(Integer s30) {
		this.s30 = s30;
	}

	public Integer getS31() {
		return s31;
	}

	public void setS31(Integer s31) {
		this.s31 = s31;
	}

	public Integer getS32() {
		return s32;
	}

	public void setS32(Integer s32) {
		this.s32 = s32;
	}

	public Integer getS33() {
		return s33;
	}

	public void setS33(Integer s33) {
		this.s33 = s33;
	}

	public int getA0() {
		return a0;
	}

	public void setA0(int a0) {
		this.a0 = a0;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getA3() {
		return a3;
	}

	public void setA3(int a3) {
		this.a3 = a3;
	}

	public int getA4() {
		return a4;
	}

	public void setA4(int a4) {
		this.a4 = a4;
	}

	public int getA5() {
		return a5;
	}

	public void setA5(int a5) {
		this.a5 = a5;
	}

	public int getA6() {
		return a6;
	}

	public void setA6(int a6) {
		this.a6 = a6;
	}

	public int getA7() {
		return a7;
	}

	public void setA7(int a7) {
		this.a7 = a7;
	}

	public int getA8() {
		return a8;
	}

	public void setA8(int a8) {
		this.a8 = a8;
	}

	public int getA9() {
		return a9;
	}

	public void setA9(int a9) {
		this.a9 = a9;
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

	public int getA14() {
		return a14;
	}

	public void setA14(int a14) {
		this.a14 = a14;
	}

	public int getA15() {
		return a15;
	}

	public void setA15(int a15) {
		this.a15 = a15;
	}

	public int getA16() {
		return a16;
	}

	public void setA16(int a16) {
		this.a16 = a16;
	}

	public int getA17() {
		return a17;
	}

	public void setA17(int a17) {
		this.a17 = a17;
	}

	public int getA18() {
		return a18;
	}

	public void setA18(int a18) {
		this.a18 = a18;
	}

	public int getA19() {
		return a19;
	}

	public void setA19(int a19) {
		this.a19 = a19;
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

	public int getA24() {
		return a24;
	}

	public void setA24(int a24) {
		this.a24 = a24;
	}

	public int getA25() {
		return a25;
	}

	public void setA25(int a25) {
		this.a25 = a25;
	}

	public int getA26() {
		return a26;
	}

	public void setA26(int a26) {
		this.a26 = a26;
	}

	public int getA27() {
		return a27;
	}

	public void setA27(int a27) {
		this.a27 = a27;
	}

	public int getA28() {
		return a28;
	}

	public void setA28(int a28) {
		this.a28 = a28;
	}

	public int getA29() {
		return a29;
	}

	public void setA29(int a29) {
		this.a29 = a29;
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

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
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

	public String getAlarminfo() {
		return alarminfo;
	}

	public void setAlarminfo(String alarminfo) {
		this.alarminfo = alarminfo;
	}

	public String getStateinfo() {
		return stateinfo;
	}

	public void setStateinfo(String stateinfo) {
		this.stateinfo = stateinfo;
	}

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public List<PositionInfoItems> getItems() {
		return items;
	}

	public void setItems(List<PositionInfoItems> items) {
		this.items = items;
	}
	
}
