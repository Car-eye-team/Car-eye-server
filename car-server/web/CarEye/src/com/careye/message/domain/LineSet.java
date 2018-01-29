/**
* Description: 多森商用车平台
* 文件名：Line.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.message.domain;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：FMS
 * @类名称：LineSet
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-27 下午04:19:19
 * @修改人：zhangrong
 * @修改时间：2014-5-27 下午04:19:19
 * @修改备注：
 * @version 1.0
 */
public class LineSet extends BaseDomain {
	
	private Integer id;
	private Integer carid;
	private Integer detailid;
	
	/**路线ID*/
	private Integer lineid;
	
	/** 根据时间 **/
	private Integer attr0;
	/** 保留 **/
	private Integer attr1;
	/** 进线路报警给驾驶员 **/
	private Integer attr2;
	/** 进线路报警给平台 **/
	private Integer attr3;
	/** 出线路报警给驾驶员 **/
	private Integer attr4;
	/** 出线路报警给平台 **/
	private Integer attr5;
	
	/**线路名称*/
	private String linename;
	
	/**路段名称*/
	private String roadname;
	
	/**车牌号*/
	private String carnumber;
	
	/**z终端号*/
	private String terminal;
	
	/**起始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	/**创建时间*/
	private String createtime;
	
	/**路段编号*/
	private Integer lid;
	
	/**拐点纬度*/
    private Double lat;

    /**拐点经度*/
    private Double lng;
    
    /**路段宽度*/
    private Integer width;
    
    /**路段行驶时长*/
    private Integer runlongtime;
    
    /**路段行驶不足阀值*/
    private Integer runlesstime;
    
    /**路段最高速度*/
    private String runtopspeed;
    
    /**路段超速持续时间*/
    private Integer passspeedingtime;
    
    /** 数据包 **/
	private String data;
	/** 操作类型	1追加2更新 3 删除 **/
	private Integer opertype;
	/** 流水号**/
	private Integer seq;
	/** 处理结果	1成功 2失败 **/
	private Integer result;

	/** 标记 0代表临时的，1代表真正产生的**/
	private Integer flag;
	
	
	private Integer detailattr0;
	private Integer detailattr1;
	private Integer detailattr2;
	private Integer detailattr3;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLineid() {
		return lineid;
	}

	public void setLineid(Integer lineid) {
		this.lineid = lineid;
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

	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getRoadname() {
		return roadname;
	}

	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getRunlongtime() {
		return runlongtime;
	}

	public void setRunlongtime(Integer runlongtime) {
		this.runlongtime = runlongtime;
	}

	public String getRuntopspeed() {
		return runtopspeed;
	}

	public void setRuntopspeed(String runtopspeed) {
		this.runtopspeed = runtopspeed;
	}

	public Integer getPassspeedingtime() {
		return passspeedingtime;
	}

	public void setPassspeedingtime(Integer passspeedingtime) {
		this.passspeedingtime = passspeedingtime;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getDetailid() {
		return detailid;
	}

	public void setDetailid(Integer detailid) {
		this.detailid = detailid;
	}

	public Integer getRunlesstime() {
		return runlesstime;
	}

	public void setRunlesstime(Integer runlesstime) {
		this.runlesstime = runlesstime;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getDetailattr0() {
		return detailattr0;
	}

	public void setDetailattr0(Integer detailattr0) {
		this.detailattr0 = detailattr0;
	}

	public Integer getDetailattr1() {
		return detailattr1;
	}

	public void setDetailattr1(Integer detailattr1) {
		this.detailattr1 = detailattr1;
	}

	public Integer getDetailattr2() {
		return detailattr2;
	}

	public void setDetailattr2(Integer detailattr2) {
		this.detailattr2 = detailattr2;
	}

	public Integer getDetailattr3() {
		return detailattr3;
	}

	public void setDetailattr3(Integer detailattr3) {
		this.detailattr3 = detailattr3;
	}

    

}
