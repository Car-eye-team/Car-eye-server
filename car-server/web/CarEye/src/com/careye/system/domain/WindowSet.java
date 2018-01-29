/**
* Description: car-eye车辆管理平台
* 文件名：User.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;



/**
 * @项目名称：car-eyeTms
 * @类名称：WindowSet
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-2-19 上午09:09:18
 * @修改人：zhangrong
 * @修改时间：2014-2-19 上午09:09:18
 * @修改备注：
 * @version 1.0
 */
public class WindowSet {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**用户ID*/
	private Integer userid;
	
	/**弹窗种类*/
	private Integer category;
	
	/**类型*/
	private String type;
	
	/**控制值**/
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	

}
