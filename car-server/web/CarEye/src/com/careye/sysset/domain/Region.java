/**
* Description: car-eye车辆管理平台
* 文件名：Region.java
* 版本信息：1.0
* 日期：2015-4-7
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：Region
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-7 上午10:52:43
 * @修改人：Yuqk
 * @修改时间：2015-4-7 上午10:52:43
 * @修改备注：
 * @version 1.0
 */
public class Region {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 行政代码
	 */
	private Long szcode;
	/**
	 * 中文名称
	 */
	private String cnname;
	/**
	 * 英文名称
	 */
	private String enname;
	/**
	 * 级别
	 */
	private Integer clevel;
	/**
	 * 父级区域名称
	 */
	private String parentname;
	/**
	 * 父级行政编码
	 */
	private Long parentcode;
	/**
	 * 区域代码
	 */
	private Long bmcode;
	/**
	 * 英文简称
	 */
	private String stenname;
	/**
	 * 中文简称
	 */
	private String stcnname;
	/**
	 * 删除标志
	 */
	private Integer isdelete;
	/**
	 * 创建时间
	 */
	private String createtime;
	/**
	 * 更新时间
	 */
	private String updatetime;
	/**
	 * 区域id
	 */
	private Integer areaid;
	/**
	 * 父级区域id
	 */
	private Integer parentid;
	/*
	 * getter  setter
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getSzcode() {
		return szcode;
	}
	public void setSzcode(Long szcode) {
		this.szcode = szcode;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public Integer getClevel() {
		return clevel;
	}
	public void setClevel(Integer clevel) {
		this.clevel = clevel;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public Long getParentcode() {
		return parentcode;
	}
	public void setParentcode(Long parentcode) {
		this.parentcode = parentcode;
	}
	public Long getBmcode() {
		return bmcode;
	}
	public void setBmcode(Long bmcode) {
		this.bmcode = bmcode;
	}
	public String getStenname() {
		return stenname;
	}
	public void setStenname(String stenname) {
		this.stenname = stenname;
	}
	public String getStcnname() {
		return stcnname;
	}
	public void setStcnname(String stcnname) {
		this.stcnname = stcnname;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
}
