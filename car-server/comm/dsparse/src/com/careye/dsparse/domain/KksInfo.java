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
 * 类名称：KksInfo    
 * 类描述：康凯斯协议实体    
 * 创建人：zr    
 * 创建时间：2015-7-9 下午02:44:10    
 * 修改人：zr    
 * 修改时间：2015-7-9 下午02:44:10    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class KksInfo extends BaseInfo{
	
	/**定位状态*/
	private Integer flag;
	
	/**日期时间*/
	private String gpstime;
	
	/**GPS 信息卫星数*/
	private Integer num;
	
	/**纬度*/
	private Integer lat;
	
	/**经度*/
	private Integer lng;
	
	/**速度*/
	private String speed;
	
	/**方向*/
	private String direction;
	
	/**高度*/
	private Integer altitude;
	
	/***/
	private Integer mcc;
	
	/***/
	private Integer mnc;
	
	/***/
	private Integer lac;
	
	/***/
	private Integer cellid;
	
	private Integer s1;
	
	private Integer s2;
	
	private Integer s3;
	
	/**1：设防0：撤防*/
	private Integer a0;
	
	/**1：acc 高0：acc 低*/
	private Integer a1;
	
	/**1：已接电源充电0：未接电源充电*/
	private Integer a2;
	
	/**1：sos 求救
	2：低电报警
	3：断电报警
	3：震动报警
	0：正常
	*/
	private Integer a3;
	
	/**1：gps 已定位0：gps 未定位*/
	private Integer a4;
	
	/**1：油电断开0：油电接通*/
	private Integer a5;
	
	/**报警6*/
	private Integer a6;
	
	/**电压等级*/
	private Integer vlevel;
	
	/**gsm 信号强度等级*/
	private Integer gsm;
	
	/**语言*/
	private Integer language;
	
	/**服务器标志*/
	private int mark;
	
	/**指令内容*/
	private String content;
	
	/**1 位置返回 2 断油电返回 3恢复油电返回*/
	private Integer type;
	
	/**返回结果 0 成功 1 失败*/
	private Integer result;
	
	

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getLat() {
		return lat;
	}

	public void setLat(Integer lat) {
		this.lat = lat;
	}

	public Integer getLng() {
		return lng;
	}

	public void setLng(Integer lng) {
		this.lng = lng;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getMcc() {
		return mcc;
	}

	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}

	public Integer getMnc() {
		return mnc;
	}

	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}

	public Integer getLac() {
		return lac;
	}

	public void setLac(Integer lac) {
		this.lac = lac;
	}

	public Integer getCellid() {
		return cellid;
	}

	public void setCellid(Integer cellid) {
		this.cellid = cellid;
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

	public Integer getA0() {
		return a0;
	}

	public void setA0(Integer a0) {
		this.a0 = a0;
	}

	public Integer getA1() {
		return a1;
	}

	public void setA1(Integer a1) {
		this.a1 = a1;
	}

	public Integer getA2() {
		return a2;
	}

	public void setA2(Integer a2) {
		this.a2 = a2;
	}

	public Integer getA3() {
		return a3;
	}

	public void setA3(Integer a3) {
		this.a3 = a3;
	}

	public Integer getA4() {
		return a4;
	}

	public void setA4(Integer a4) {
		this.a4 = a4;
	}

	public Integer getA5() {
		return a5;
	}

	public void setA5(Integer a5) {
		this.a5 = a5;
	}

	public Integer getA6() {
		return a6;
	}

	public void setA6(Integer a6) {
		this.a6 = a6;
	}

	public Integer getVlevel() {
		return vlevel;
	}

	public void setVlevel(Integer vlevel) {
		this.vlevel = vlevel;
	}

	public Integer getGsm() {
		return gsm;
	}

	public void setGsm(Integer gsm) {
		this.gsm = gsm;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
}
