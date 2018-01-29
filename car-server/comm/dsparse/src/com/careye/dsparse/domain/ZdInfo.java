/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：ZdInfo    
 * 类描述： 中导协议实体   
 * 创建人：zr    
 * 创建时间：2015-6-18 下午12:55:36    
 * 修改人：zr    
 * 修改时间：2015-6-18 下午12:55:36    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ZdInfo extends BaseInfo{

	/**用户名*/
	private String name;
	
	/**密码*/
	private String password;
	
	/**字符T,表示设置成功*/
	private String textvalue;
	
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
	
	/**里程*/
	private String mileage;
	
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
	
	/**信息类型*/
	private String infotype;
	
	/**信息内容*/
	private String contents;
	
	/**超速报警是否使能*/
	private int flag;
	
	/**超速报警是否使能: 0 表示禁止， 1 表示使能， 其他不操作*/
	private int useflag;
	
	/**最低速度限制*/
	private int minspeed;
	
	/**最高速度限制*/
	private int maxspeed;
	
	/**设置/查询: 0 表示查询， 1 表示设置， 其他不操作*/
	private int type;

	/**参数项*/
	private  List<ZdInfoItems> items = new ArrayList<ZdInfoItems>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTextvalue() {
		return textvalue;
	}

	public void setTextvalue(String textvalue) {
		this.textvalue = textvalue;
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

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
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


	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getUseflag() {
		return useflag;
	}

	public void setUseflag(int useflag) {
		this.useflag = useflag;
	}

	public int getMinspeed() {
		return minspeed;
	}

	public void setMinspeed(int minspeed) {
		this.minspeed = minspeed;
	}

	public int getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(int maxspeed) {
		this.maxspeed = maxspeed;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<ZdInfoItems> getItems() {
		return items;
	}

	public void setItems(List<ZdInfoItems> items) {
		this.items = items;
	}
	
	
	
}
