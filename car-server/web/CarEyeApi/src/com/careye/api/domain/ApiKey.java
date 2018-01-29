/**
* Description: car-eye车辆管理平台
* 文件名：User.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.api.domain;

import java.io.Serializable;

/**
 * @项目名称：APP
 * @类名称：User
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-2-19 上午09:09:18
 * @修改人：lxh
 * @修改时间：2014-2-19 上午09:09:18
 * @修改备注：
 * @version 1.0
 */
public class ApiKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**ID**/
	private Integer id;
	
	/**APP版本类型	APP版本类型表id**/
	private Integer typeid;
	private String typename;
	
	/**密钥名称**/
	private String key;
	
	/**描述**/
	private String descs;
	
	/**状态	默认1 （1 正常/ 2 禁用）**/
	private Integer status;
	
	/**请求次数**/
	private Integer requestcount;
	
	
	
	/**创建时间**/
	private String createtime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getRequestcount() {
		return requestcount;
	}
	public void setRequestcount(Integer requestcount) {
		this.requestcount = requestcount;
	}
	
	

}
