/**
* Description: car-eye车辆管理平台
* 文件名：AreaSet.java
* 版本信息：1.0
* 日期：2014-6-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：AreaSet
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-19 下午02:09:38
 * @修改人：huangqin
 * @修改时间：2015-3-19 下午02:09:38
 * @修改备注：
 * @version 1.0
 */
public class AreaSet extends BaseDomain{

	private Integer id;
	
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
	private String maxspeed;
	/** 超速持续时间 **/
	private String speedtime;
	/** 中心点纬度 **/
	private String ylat;
	/** 中心点经度 **/
	private String ylng;
	/** 半径 **/
	private String radius;
	/** 左上点纬度 **/
	private String latlt;
	/** 左上点经度 **/
	private String lnglt;
	/** 右下点纬度 **/
	private String latrb;
	/** 右下点经度 **/
	private String lngrb;
	/** 多边形多纬度 **/
	private String latsrt;
	/** 多边形多经度 **/
	private String lngsrt;
	
	/** 车牌号 **/
	private String carnumber;
	/** 终端号 **/
	private String terminal;
	/** 数据包 **/
	private String data;
	/** 操作类型	1追加2更新 3 删除 **/
	private Integer opertype;
	/** 流水号**/
	private Integer seq;
	/** 处理结果	1成功 2失败 **/
	private Integer result;
	/** 区域ID **/
	private Integer areaid;

	/** 创建时间 **/
	private String createtime;
	
	/**有效期 1每年 2 每月 3 每天 4 每小时 5 每分钟**/
	private Integer termvalidity;
	
	
	private  Integer  blocid;
	private String  blocname;
	
	/** 1：稽查区域	(报警给平台，切换顶灯显示，但不报警给驾驶员) **/
	private Integer attr8;
	/** 1：稽查区域	(报警给平台，切换顶灯显示，同时报警给驾驶员) **/
	private Integer attr9;
	/** 区域顶灯提醒信息 **/
	private String ddalertinfo;
	/** 区域司机提醒信息 **/
	private String driveralertinfo;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(String maxspeed) {
		this.maxspeed = maxspeed;
	}

	public String getSpeedtime() {
		return speedtime;
	}

	public void setSpeedtime(String speedtime) {
		this.speedtime = speedtime;
	}

	public String getYlat() {
		return ylat;
	}

	public void setYlat(String ylat) {
		this.ylat = ylat;
	}

	public String getYlng() {
		return ylng;
	}

	public void setYlng(String ylng) {
		this.ylng = ylng;
	}


	public String getLatlt() {
		return latlt;
	}

	public void setLatlt(String latlt) {
		this.latlt = latlt;
	}

	public String getLnglt() {
		return lnglt;
	}

	public void setLnglt(String lnglt) {
		this.lnglt = lnglt;
	}

	public String getLatrb() {
		return latrb;
	}

	public void setLatrb(String latrb) {
		this.latrb = latrb;
	}

	public String getLngrb() {
		return lngrb;
	}

	public void setLngrb(String lngrb) {
		this.lngrb = lngrb;
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

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getTermvalidity() {
		return termvalidity;
	}

	public void setTermvalidity(Integer termvalidity) {
		this.termvalidity = termvalidity;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
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
