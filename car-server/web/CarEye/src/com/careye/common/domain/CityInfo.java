/**
* Description: car-eye车辆管理平台
* 文件名：CityInfo.java
* 版本信息：1.0
* 日期：2013-8-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：car-eye
 * @类名称：CityInfo
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-8-5 上午09:56:46
 * @修改人：liliang
 * @修改时间：2013-8-5 上午09:56:46
 * @修改备注：
 * @version 1.0
 */
public class CityInfo implements java.io.Serializable {
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**地区编号*/
	private Integer areaid;
	/**区域父类编号*/
	private Integer parentid;
	/**等级*/
	private Integer clevel;
	/**区域代码*/
	private Integer bmcode;
	/**行政代码*/
	private String szcode;
	/**行政区名*/
	private String cnname;
	/**名称拼音*/
	private String enname;
	/**简拼*/
	private String stenname;
	/***/
	private String stcnname;
	/**是否删除状态*/
	private Boolean isdelete;
	/**创建时间*/
	private String createtime;
	/**更新时间*/
	private String updatetime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getClevel() {
		return clevel;
	}
	public void setClevel(Integer clevel) {
		this.clevel = clevel;
	}
	public Integer getBmcode() {
		return bmcode;
	}
	public void setBmcode(Integer bmcode) {
		this.bmcode = bmcode;
	}
	public String getSzcode() {
		return szcode;
	}
	public void setSzcode(String szcode) {
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
	public Boolean getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Boolean isdelete) {
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
	
	

}
