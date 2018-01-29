/**
* Description: 出租车系统
* 文件名：SerEva.java
* 版本信息：1.0
* 日期：2015-3-26
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.car.domain;

/**
 * @项目名称：TAXISERVER
 * @类名称：SerEva
 * @类描述：服务评价实体
 * @创建人：zr
 * @创建时间：2015-3-26 上午11:42:04
 * @修改人：zr
 * @修改时间：2015-3-26 上午11:42:04
 * @修改备注：
 * @version 1.0
 */
public class SerEva {
	
	private Integer id;
	
	/**车辆ID*/
	private Integer carid;
	
	/**当班司机ID*/
	private Integer shiftdriverid;
	
	/**评价结果*/
	private Integer result;
	
	/**不满意原因*/
	private Integer reason;
	
	/**创建时间*/
	private String createtime;
	
	/**备注信息*/
	private String remark;

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

	public Integer getShiftdriverid() {
		return shiftdriverid;
	}

	public void setShiftdriverid(Integer shiftdriverid) {
		this.shiftdriverid = shiftdriverid;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
