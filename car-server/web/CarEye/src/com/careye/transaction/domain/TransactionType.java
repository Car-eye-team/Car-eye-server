/**
* Description: car-eye车辆管理平台
* 文件名：CustomerInfo.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.transaction.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：CustomerType
 * @类描述：客户信息
 * @创建人：huangqin
 * @创建时间：2015-3-16 上午10:07:35
 * @修改人：huangqin
 * @修改时间：2015-3-16 上午10:07:35
 * @修改备注：
 * @version 1.0
 */
public class TransactionType extends BaseDomain implements Serializable{
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**  主键   **/
	private Integer id;
	/** 类型名称   **/
	private String  typename;
	/** 备注 **/
	private String remark;
	/**  创建时间   **/
	private String createtime;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
