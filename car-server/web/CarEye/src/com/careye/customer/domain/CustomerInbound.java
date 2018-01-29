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
public class CustomerInbound  extends BaseDomain implements Serializable{
    /**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/** ID **/
    private  Integer  id;
    /** 客户ID **/
    private  Integer  cid;
    /** 坐席ID **/
    private  String agentid;
    /** 来电号码 **/
    private  String  callnumber;
    /** 来电时间 **/
    private  String  inboundcalltime;
    /**挂机时间 **/
    private  String  hangupcalltime;
    /**创建时间 **/
    private  String  createtime;
    /**客户名称 **/
    private String cname;
    /**客户类型名称 **/
    private String  typename;
    /**本次通话的录音文件名**/
    private String recordfile;
    /**流水号(唯一标示)**/
    private String uniqueid;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getInboundcalltime() {
		return inboundcalltime;
	}
	public void setInboundcalltime(String inboundcalltime) {
		this.inboundcalltime = inboundcalltime;
	}
	public String getHangupcalltime() {
		return hangupcalltime;
	}
	public void setHangupcalltime(String hangupcalltime) {
		this.hangupcalltime = hangupcalltime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getRecordfile() {
		return recordfile;
	}
	public void setRecordfile(String recordfile) {
		this.recordfile = recordfile;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
    
    
	
}
