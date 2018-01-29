/**
* Description: car-eye车辆管理平台
* 文件名：CustomerInfo.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.customer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：CustomerInfo
 * @类描述：客户信息
 * @创建人：huangqin
 * @创建时间：2015-3-16 上午10:07:35
 * @修改人：huangqin
 * @修改时间：2015-3-16 上午10:07:35
 * @修改备注：
 * @version 1.0
 */
public class Customer  extends BaseDomain implements Serializable{
    /**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** ID **/
    private  Integer  id;
    /** 客户类型ID**/
	private  Integer  typeid;
	/**客户姓名 **/
	private  String  cname;
	/**性别  男/女;默认:男 **/
	private  String  sex;
	/** 电话 **/
	private  String  tel;
	/** 电话号码**/
	private  String  phone;
	/** 住址**/
	private  String  postaddr;
	/** 邮政编码**/
	private  String  postalcode;
	/** 客户信息描述**/
	private  String  remark;
	/**客户来源  1 96106  **/
	private  Integer  source;
	/** 创建时间**/
	private  String  createtime;
	/** 客户类型 名称**/
    private String typename;
    /** 客户状态    1、正常 0 删除 默认1 **/
    private Integer flag;
    
    
    /** 放主键 **/
	private List<Integer> ids = new ArrayList<Integer>();

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostaddr() {
		return postaddr;
	}

	public void setPostaddr(String postaddr) {
		this.postaddr = postaddr;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
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

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
	
}
