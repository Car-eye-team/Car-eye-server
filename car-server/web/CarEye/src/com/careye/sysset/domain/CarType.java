/**
* Description: car-eye车辆管理平台
* 文件名：SetParamSet.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：CarType
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午10:51:59
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午10:51:59
 * @修改备注：
 * @version 1.0
 */
public class CarType extends BaseDomain  implements  Serializable {
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**id**/
	private Integer id;
	/**名称**/
	private String typename;
	/**创建时间**/
	private String createtime;
	/** 备注 **/
	private String  remark;
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




