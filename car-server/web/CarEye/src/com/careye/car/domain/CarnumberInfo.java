/**
* Description: car-eye车辆管理平台
* 文件名：CarInfo.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.domain;

import java.io.Serializable;

import net.sf.oval.constraint.Size;

/**
 * @项目名称：FMS
 * @类名称：CarInfo
 * @类描述：车辆信息实体
 * @创建人：zr
 * @创建时间：2014-5-22 上午10:21:04
 * @修改人：zr
 * @修改时间：2014-5-22 上午10:21:04
 * @修改备注：
 * @version 1.0
 */
public class CarnumberInfo implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer carid;
	
	private Integer blocid;
	
	/**终端号码*/
	private String terminal;
	
	/**车牌号*/
	private String carnumber;
	
	/**集团名称**/
	private String blocname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	
	
		
}




