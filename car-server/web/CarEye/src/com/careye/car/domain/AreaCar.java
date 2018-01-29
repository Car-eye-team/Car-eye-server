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
 * @项目名称：FMS
 * @类名称：AreaCar
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-6-3 下午02:09:38
 * @修改人：zhangrong
 * @修改时间：2014-6-3 下午02:09:38
 * @修改备注：
 * @version 1.0
 */
public class AreaCar extends BaseDomain{

	private Integer id;
	
	/** 车牌号 **/
	private String carnumber;
	
	/** 终端号 **/
	private String terminal;
	
	/** 区域ID **/
	private Integer areaid;
	
	/** 区域类型**/
	private Integer areatype;
	
	/** 流水号 **/
	private Integer seq;
	
	/** 处理结果 **/
	private Integer result;

	/** 创建时间 **/
	private String createtime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}
	
	
}
