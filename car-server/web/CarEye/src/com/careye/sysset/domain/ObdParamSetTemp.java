/**
* Description: 多森商用车平台
* 文件名：CarParam.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：FMS
 * @类名称：ObdParamSet
 * @类描述：参数设置
 * @创建人：zhangrong
 * @创建时间：2014-7-13 下午01:32:54
 * @修改人：zhangrong
 * @修改时间：2014-7-13 下午01:32:54
 * @修改备注：
 * @version 1.0
 */
public class ObdParamSetTemp {
	
	private Integer id;
	
	private Integer carid;
	
	/**车牌号**/
	private String carnumber;
	
	private String terminal;
	
	/**组织机构*/
	private Integer blocid;
	/**组织机构*/
	private String blocname;
	
	
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
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


