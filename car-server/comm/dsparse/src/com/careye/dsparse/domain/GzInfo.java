/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：GzInfo    
 * 类描述：广州C-OBD 终端通信协议实体    
 * 创建人：Administrator    
 * 创建时间：2016-6-30 下午02:08:05    
 * 修改人：Administrator    
 * 修改时间：2016-6-30 下午02:08:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GzInfo extends BaseInfo{

	/**时间*/
	private String time;

	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/**速度*/
	private String speed;

	/**方向*/
	private int direction;

	/**高度*/
	private int altitude;

	/**0 未定位 1 已定位*/
	private int gpsflag;

	/**电池电量*/
	private int battery;

	/**碰撞报警*/
	private Integer s00;
	/**侧翻报警*/
	private Integer s01;
	/**位移报警*/
	private Integer s11;
	/**车辆处于断油电状态*/
	private Integer s13;
	/**电瓶拆除报警*/
	private Integer s14;
	/**Nobd 模式*/
	private Integer s17;
	/**盲区补报*/
	private Integer s21;
	/**Gps开关状态*/
	private Integer s22;
	/**供电方式*/
	private Integer s23;
	/**非法震动*/
	private Integer s27;
	/**车辆设防状态*/
	private Integer s31;
	/**ACC 状态*/
	private Integer s32;
	/**启动提醒*/
	private Integer s36;
	/**非法点火报警*/
	private Integer s43;
	/**熄火提醒*/
	private Integer s47;

	/**数据补传状态*/
	private Integer s82;

	/**OBD 故障报警状态*/
	private Integer s83;

	/**水温异常报警*/
	private Integer s84;

	/**发动机运行超时报警*/
	private Integer s85;

	/**超速报警*/
	private Integer s86;

	/**电瓶电压低报警*/
	private Integer s87;

	/**数据类型*/
	private int type;

	/**国家代码*/
	private int ccode;

	/**运营商编号*/
	private int operno;

	/**基站号*/
	private int cellid;

	/**小区 ID*/
	private int cid;

	/**记录号*/
	private int recno;

	/**里程*/
	private String mileage;

	private int x;

	private int y;

	private int z;

	/**OBD数组*/
	private List<GzObdInfo> obditems = new ArrayList<GzObdInfo>();
	
	/**故障数组*/
	private List<GzInfoItems> faultitems = new ArrayList<GzInfoItems>();
	
	/**命令类型*/
	private String cmdtype;
	
	/**参数值*/
	private String value;
	
	/**开关*/
	private String sw;
	
	/**间隔*/
	private String interval;
	
	/**参数类型*/
	private String paratype;
	
	/**手机号*/
	private String phone;
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParatype() {
		return paratype;
	}

	public void setParatype(String paratype) {
		this.paratype = paratype;
	}

	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getCmdtype() {
		return cmdtype;
	}

	public void setCmdtype(String cmdtype) {
		this.cmdtype = cmdtype;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<GzObdInfo> getObditems() {
		return obditems;
	}

	public void setObditems(List<GzObdInfo> obditems) {
		this.obditems = obditems;
	}


	public List<GzInfoItems> getFaultitems() {
		return faultitems;
	}

	public void setFaultitems(List<GzInfoItems> faultitems) {
		this.faultitems = faultitems;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public int getCcode() {
		return ccode;
	}

	public void setCcode(int ccode) {
		this.ccode = ccode;
	}

	public int getOperno() {
		return operno;
	}

	public void setOperno(int operno) {
		this.operno = operno;
	}

	public int getCellid() {
		return cellid;
	}

	public void setCellid(int cellid) {
		this.cellid = cellid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getRecno() {
		return recno;
	}

	public void setRecno(int recno) {
		this.recno = recno;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getS82() {
		return s82;
	}

	public void setS82(Integer s82) {
		this.s82 = s82;
	}

	public Integer getS83() {
		return s83;
	}

	public void setS83(Integer s83) {
		this.s83 = s83;
	}

	public Integer getS84() {
		return s84;
	}

	public void setS84(Integer s84) {
		this.s84 = s84;
	}

	public Integer getS85() {
		return s85;
	}

	public void setS85(Integer s85) {
		this.s85 = s85;
	}

	public Integer getS86() {
		return s86;
	}

	public void setS86(Integer s86) {
		this.s86 = s86;
	}

	public Integer getS87() {
		return s87;
	}

	public void setS87(Integer s87) {
		this.s87 = s87;
	}

	public Integer getS00() {
		return s00;
	}

	public void setS00(Integer s00) {
		this.s00 = s00;
	}

	public Integer getS01() {
		return s01;
	}

	public void setS01(Integer s01) {
		this.s01 = s01;
	}

	public Integer getS11() {
		return s11;
	}

	public void setS11(Integer s11) {
		this.s11 = s11;
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

	public Integer getS17() {
		return s17;
	}

	public void setS17(Integer s17) {
		this.s17 = s17;
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

	public Integer getS27() {
		return s27;
	}

	public void setS27(Integer s27) {
		this.s27 = s27;
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

	public Integer getS36() {
		return s36;
	}

	public void setS36(Integer s36) {
		this.s36 = s36;
	}

	public Integer getS43() {
		return s43;
	}

	public void setS43(Integer s43) {
		this.s43 = s43;
	}

	public Integer getS47() {
		return s47;
	}

	public void setS47(Integer s47) {
		this.s47 = s47;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}


}
