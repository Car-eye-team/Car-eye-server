package com.careye.car.domain;

import java.io.Serializable;

/**
 * @项目名称：TAXISERVER
 * @类名称：PositionInfo
 * @类描述：位置信息实体
 * @创建人：zr
 * @创建时间：2015-3-13 下午12:44:32
 * @修改人：zr
 * @修改时间：2015-3-13 下午12:44:32
 * @修改备注：
 * @version 1.0
 */
public class PositionInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**车辆位置表表名**/
	private String tName;
	private String timestamp;

	/**车辆ID*/
	private Integer carid;
	private Integer blocid;

	/**终端号码*/
	private String terminal;
	private String blocname;

	/**车牌号*/
	private String carnumber;

	/**信息ID*/
	private Integer msgid;

	/**ACC状态	0: ACC关;1:ACC开**/
	private Integer acc;

	/**经度*/
	private Double lng;

	/**纬度 */
	private Double lat;

	/**高度 */
	private String altitude;

	/**速度 */
	private String speed;

	/**方向 */
	private String direction;

	/**GPS时间 */
	private String gpstime;

	/**GPS是否有效0为无效,1为有效,2为上次信号有效时的位置 */
	private int gpsflag;

	/**省*/
	private String province;

	/**市*/
	private String city;

	/**县*/
	private String district;

	/**百度经度*/
	private Double glng;

	/**百度纬度 */
	private Double glat;

	/**百度地址 */
	private String address;

	/**百度经度*/
	private Double blng;

	/**百度纬度 */
	private Double blat;

	/**高德地址**/
	private String gaddress;

	/**当前里程**/
	private Float mileage;

	/**总里程**/
	private Float summileage;

	/**日里程**/
	private Float daymileage;

	/**创建时间**/
	private String createtime;

	/**数据包序号*/
	private Integer seq;

	/**数据包*/
	private String datapacket;

	/**状态*/
	private String sn;

	/**状态值*/
	private Integer snvalue;

	/**报警标志*/
	private String an;

	/**报警标志值*/
	private Integer anvalue;

	/**1长时间离线2离线3 熄火4停车 5 行驶 6 报警 7在线 8 未定位 默认：1*/
	private Integer carstatus;

	/**油量*/
	private Float oil;

	/**总油耗*/
	private Float oilsum;

	/**平均油耗	100公里平均油耗*/
	private Float oilavg;

	/**瞬时油耗*/
	private Float oilat;

	/**空重状态	0 空车 1 有客*/
	private Integer zkstate;

	/**车门	0 关 1 开*/
	private Integer cardoorstate;

	/**刹车	0 无 1 刹车*/
	private Integer brakestate;

	/**备用字段1*/
	private String reserve1;

	/**备用字段2*/
	private String reserve2;

	/**备用字段3*/
	private String reserve3;

	/**备用字段4*/
	private String reserve4;

	/**备用字段5*/
	private String reserve5;

	/**备注信息*/
	private String remark;

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getMsgid() {
		return msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}

	public Integer getAcc() {
		return acc;
	}

	public void setAcc(Integer acc) {
		this.acc = acc;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
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

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getGaddress() {
		return gaddress;
	}

	public void setGaddress(String gaddress) {
		this.gaddress = gaddress;
	}

	public Float getMileage() {
		return mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	public Float getSummileage() {
		return summileage;
	}

	public void setSummileage(Float summileage) {
		this.summileage = summileage;
	}

	public Float getDaymileage() {
		return daymileage;
	}

	public void setDaymileage(Float daymileage) {
		this.daymileage = daymileage;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDatapacket() {
		return datapacket;
	}

	public void setDatapacket(String datapacket) {
		this.datapacket = datapacket;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getSnvalue() {
		return snvalue;
	}

	public void setSnvalue(Integer snvalue) {
		this.snvalue = snvalue;
	}

	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public Integer getAnvalue() {
		return anvalue;
	}

	public void setAnvalue(Integer anvalue) {
		this.anvalue = anvalue;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public Float getOil() {
		return oil;
	}

	public void setOil(Float oil) {
		this.oil = oil;
	}

	public Float getOilsum() {
		return oilsum;
	}

	public void setOilsum(Float oilsum) {
		this.oilsum = oilsum;
	}

	public Float getOilavg() {
		return oilavg;
	}

	public void setOilavg(Float oilavg) {
		this.oilavg = oilavg;
	}

	public Float getOilat() {
		return oilat;
	}

	public void setOilat(Float oilat) {
		this.oilat = oilat;
	}

	public Integer getZkstate() {
		return zkstate;
	}

	public void setZkstate(Integer zkstate) {
		this.zkstate = zkstate;
	}

	public Integer getCardoorstate() {
		return cardoorstate;
	}

	public void setCardoorstate(Integer cardoorstate) {
		this.cardoorstate = cardoorstate;
	}

	public Integer getBrakestate() {
		return brakestate;
	}

	public void setBrakestate(Integer brakestate) {
		this.brakestate = brakestate;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Double getGlng() {
		return glng;
	}

	public void setGlng(Double glng) {
		this.glng = glng;
	}

	public Double getGlat() {
		return glat;
	}

	public void setGlat(Double glat) {
		this.glat = glat;
	}

	public Double getBlng() {
		return blng;
	}

	public void setBlng(Double blng) {
		this.blng = blng;
	}

	public Double getBlat() {
		return blat;
	}

	public void setBlat(Double blat) {
		this.blat = blat;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	
}
