/**
* Description: 车队管理系统
* 文件名：AreaSet.java
* 版本信息：1.0
* 日期：2014-6-3
* Copyright car-eye车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

import com.careye.base.action.BaseDomain;


/**
 * @项目名称：FMS
 * @类名称：AreaSet
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2014-6-3 下午02:09:38
 * @修改人：wuyongde
 * @修改时间：2014-6-3 下午02:09:38
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
	private Double maxspeed;
	/** 超速持续时间 **/
	private Double speedtime;
	/** 中心点纬度 **/
	private Double ylat;
	/** 中心点经度 **/
	private Double ylng;
	/** 半径 **/
	private Double radius;
	/** 左上点纬度 **/
	private Double latlt;
	/** 左上点经度 **/
	private Double lnglt;
	/** 右下点纬度 **/
	private Double latrb;
	/** 右下点经度 **/
	private Double lngrb;
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
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
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
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	
	
}
