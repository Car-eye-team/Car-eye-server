/**
* Description: car-eye车辆管理平台
* 文件名：Log.java
* 版本信息：1.0
* 日期：2014-7-28
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;



/**
 * @项目名称：car-eyeTms
 * @类名称：Log
 * @类描述：类的方法描述注解
 * @创建人：lxh
 * @创建时间：2014-7-28 下午05:49:40
 * @修改人：lxh
 * @修改时间：2014-7-28 下午05:49:40
 * @修改备注：
 * @version 1.0
 */
public class  SysOperateLog {
	
	private Integer logid;
	
	/**操作用户**/
	private String loginname;
	private Integer userid;
	
	/**日志内容**/
	private String content;
	
	/**操作类型  1添加 2修改 3删除 4导出**/
	private Integer operattype;
	
	/**操作时间**/
	private String createtime;

	private String stime;
	
	private String etime;

	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLogid() {
		return logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public Integer getOperattype() {
		return operattype;
	}

	public void setOperattype(Integer operattype) {
		this.operattype = operattype;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	
	



}
