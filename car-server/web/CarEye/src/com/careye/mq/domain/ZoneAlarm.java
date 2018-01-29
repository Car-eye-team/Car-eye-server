/**
* Description: 多森商用车平台
* 文件名：CircleArea.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：FMS
 * @类名称：CircleArea
 * @类描述：区域
 * @创建人：zr
 * @创建时间：2014-5-27 下午01:43:56
 * @修改人：zr
 * @修改时间：2014-5-27 下午01:43:56
 * @修改备注：
 * @version 1.0
 */
public class ZoneAlarm extends BaseDomain{

    private static final long serialVersionUID = 8096762857562124104L;
    
    private Integer id;
    
    /**消息ID**/
    private Integer msgid;
    
    /**添加时间**/
    private String addtime;
    
    /**消息包**/
    private String data;
    
    
    /**车牌号码**/
    private String carNumber;
    
    /**设备号*/
    private String terminal;
    
    /**0：更新区域；1：追加区域；2：修改区域；*/
    private Integer update;

    /**区域总数*/
    private Integer count;

    /**区域ID*/
    private Integer areaId;
    
    /**区域属性*/
    private String attr;

    /**中心点纬度*/
    private Double lat;

    /**中心点经度*/
    private Double lng;

    /**半径*/
    private String radius;

    /**起始时间*/
    private String timeS;

    /**结束时间*/
    private String timeE;

    /**最高速度*/
    private String speedLimit;

    /**超速持续时间**/
    private Integer speedTime;

    /**左上点纬度**/
    private Double latlt;

    /**左上点经度**/
    private Double lnglt;

    /**右下点纬度**/
    private Double latrb;

    /**右下点经度**/
    private Double lngrb;

    /**区域总定点数*/
    private Integer sum;

    /**顶点数*/
    private Integer num;
    
    /**多纬度（以","号隔开）*/
    private String latsrt;
    
    /**多经度（以","号隔开）*/
    private String lngsrt;
    
    /**区域类型 1 圆形区域 2 矩形区域 3 多边形区域 **/
	private Integer areatype;
	
	/** 区域名称 **/
	private String areaname;
	
	/** 根据时间 **/
	private Integer attr0;
	
	/** 限速 **/
	private Integer attr1;
	
	/** 进区域报警给驾驶员 **/
	private Integer attr2;
	
	/** 进区域报警给平台 **/
	private Integer attr3;
	
	/** 出区域报警给驾驶员 **/
	private Integer attr4;
	
	/** 出区域报警给平台 **/
	private Integer attr5;
	
	/** 0：北纬；1：南纬 **/
	private Integer attr6;
	
	/** 0：东经；1：西经 **/
	private Integer attr7;
	
	/** 最高速度 **/
	private Double maxspeed;
	
	/** 超速持续时间 **/
	private Double speedtime;
	
	/** 中心点纬度 **/
	private Double ylat;
	
	/** 中心点经度 **/
	private Double ylng;
	
	/** 创建时间 **/
	private String createtime;
	
	/** 车牌号 **/
	private String carnumber;
	
	/** 操作类型	1追加2更新 3 删除 **/
	private Integer opertype;
	
	/** 流水号**/
	private Integer seq;
	
	/** 处理结果	1成功 2失败 **/
	private Integer result;
	
	/** 区域ID **/
	private Integer areaid;
	
	private Integer termvalidity;
	
	/** 1：稽查区域	(报警给平台，切换顶灯显示，但不报警给驾驶员) **/
	private Integer attr8;
	/** 1：稽查区域	(报警给平台，切换顶灯显示，同时报警给驾驶员) **/
	private Integer attr9;
	/** 区域顶灯提醒信息 **/
	private String ddalertinfo;
	/** 区域司机提醒信息 **/
	private String driveralertinfo;
	

	public Integer getTermvalidity() {
		return termvalidity;
	}

	public void setTermvalidity(Integer termvalidity) {
		this.termvalidity = termvalidity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMsgid() {
		return msgid;
	}

	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getTimeS() {
		return timeS;
	}

	public void setTimeS(String timeS) {
		this.timeS = timeS;
	}

	public String getTimeE() {
		return timeE;
	}

	public void setTimeE(String timeE) {
		this.timeE = timeE;
	}

	public String getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(String speedLimit) {
		this.speedLimit = speedLimit;
	}

	public Integer getSpeedTime() {
		return speedTime;
	}

	public void setSpeedTime(Integer speedTime) {
		this.speedTime = speedTime;
	}

	public Double getLatlt() {
		return latlt;
	}

	public void setLatlt(Double latlt) {
		this.latlt = latlt;
	}

	public Double getLnglt() {
		return lnglt;
	}

	public void setLnglt(Double lnglt) {
		this.lnglt = lnglt;
	}

	public Double getLatrb() {
		return latrb;
	}

	public void setLatrb(Double latrb) {
		this.latrb = latrb;
	}

	public Double getLngrb() {
		return lngrb;
	}

	public void setLngrb(Double lngrb) {
		this.lngrb = lngrb;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getLatsrt() {
		return latsrt;
	}

	public void setLatsrt(String latsrt) {
		this.latsrt = latsrt;
	}

	public String getLngsrt() {
		return lngsrt;
	}

	public void setLngsrt(String lngsrt) {
		this.lngsrt = lngsrt;
	}

	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public Integer getAttr0() {
		return attr0;
	}

	public void setAttr0(Integer attr0) {
		this.attr0 = attr0;
	}

	public Integer getAttr1() {
		return attr1;
	}

	public void setAttr1(Integer attr1) {
		this.attr1 = attr1;
	}

	public Integer getAttr2() {
		return attr2;
	}

	public void setAttr2(Integer attr2) {
		this.attr2 = attr2;
	}

	public Integer getAttr3() {
		return attr3;
	}

	public void setAttr3(Integer attr3) {
		this.attr3 = attr3;
	}

	public Integer getAttr4() {
		return attr4;
	}

	public void setAttr4(Integer attr4) {
		this.attr4 = attr4;
	}

	public Integer getAttr5() {
		return attr5;
	}

	public void setAttr5(Integer attr5) {
		this.attr5 = attr5;
	}

	public Integer getAttr6() {
		return attr6;
	}

	public void setAttr6(Integer attr6) {
		this.attr6 = attr6;
	}

	public Integer getAttr7() {
		return attr7;
	}

	public void setAttr7(Integer attr7) {
		this.attr7 = attr7;
	}

	public Double getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Double maxspeed) {
		this.maxspeed = maxspeed;
	}

	public Double getSpeedtime() {
		return speedtime;
	}

	public void setSpeedtime(Double speedtime) {
		this.speedtime = speedtime;
	}

	public Double getYlat() {
		return ylat;
	}

	public void setYlat(Double ylat) {
		this.ylat = ylat;
	}

	public Double getYlng() {
		return ylng;
	}

	public void setYlng(Double ylng) {
		this.ylng = ylng;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getOpertype() {
		return opertype;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getAttr8() {
		return attr8;
	}

	public void setAttr8(Integer attr8) {
		this.attr8 = attr8;
	}

	public Integer getAttr9() {
		return attr9;
	}

	public void setAttr9(Integer attr9) {
		this.attr9 = attr9;
	}

	public String getDdalertinfo() {
		return ddalertinfo;
	}

	public void setDdalertinfo(String ddalertinfo) {
		this.ddalertinfo = ddalertinfo;
	}

	public String getDriveralertinfo() {
		return driveralertinfo;
	}

	public void setDriveralertinfo(String driveralertinfo) {
		this.driveralertinfo = driveralertinfo;
	}
    
   
    
    
}
