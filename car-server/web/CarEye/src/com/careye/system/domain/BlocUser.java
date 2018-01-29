/**
* Description: car-eye车辆管理平台
* 文件名：User.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.domain;

import java.io.Serializable;
import java.util.List;

import com.careye.base.action.BaseDomain;

/**
 * @项目名称：car-eye
 * @类名称：BlocUser
 * @类描述：集团用户表
 * @创建人：huangqin
 * @创建时间：2015-3-5 上午10:25:37
 * @修改人：huangqin
 * @修改时间：2015-3-5 上午10:25:37
 * @修改备注：
 * @version 1.0
 */
public class BlocUser extends BaseDomain implements Serializable{
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	/**集团用户ID**/
	private Integer id;
	/**所属集团**/
	private Integer blocid;
	/**登录名**/
	private String loginname;
	/**登录密码**/
	private String password;
	/**姓名**/
	private String username;
	/**性别 1 男 2 女**/
	private Integer usersex;
	/**手机号**/
	private String mobile;
	/**固定电话**/
	private String telphone;
	/**EMAIL**/
	private String email;
	/**集团组ID**/
	private Integer blocgroupid;
	/**证件号**/
	private String cardnumber;
	/**状态（停用/激活状态）1 激活 2 停用 默认为：1**/
	private Integer state;
	/**创建时间**/
	private String createtime;
	/**报警开关	1 打开 2 关闭 默认为1**/
	private Integer warnswitch;
	
	private String veryCode;
	private String newpassword;
	private String renewpassword;
	private Integer iseditpw;
	
	private Integer userid;
	
	private String blocgroupname;
	private String blocname;
	
	private Integer leftpage;
	private Integer parentid;
	
	private Integer type;
	
	private Integer deptcount;
	private Integer bottompage;
	private Integer alarmcount;
	private String lastlogin;
	
	//坐席分机号
    private String runnumber;
	
	
	public Integer getWarnswitch() {
		return warnswitch;
	}
	public void setWarnswitch(Integer warnswitch) {
		this.warnswitch = warnswitch;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlocid() {
		return blocid;
	}
	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUsersex() {
		return usersex;
	}
	public void setUsersex(Integer usersex) {
		this.usersex = usersex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getBlocgroupid() {
		return blocgroupid;
	}
	public void setBlocgroupid(Integer blocgroupid) {
		this.blocgroupid = blocgroupid;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getVeryCode() {
		return veryCode;
	}
	public void setVeryCode(String veryCode) {
		this.veryCode = veryCode;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRenewpassword() {
		return renewpassword;
	}
	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
	}
	public Integer getIseditpw() {
		return iseditpw;
	}
	public void setIseditpw(Integer iseditpw) {
		this.iseditpw = iseditpw;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getBlocgroupname() {
		return blocgroupname;
	}
	public void setBlocgroupname(String blocgroupname) {
		this.blocgroupname = blocgroupname;
	}
	public String getBlocname() {
		return blocname;
	}
	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}
	public Integer getLeftpage() {
		return leftpage;
	}
	public void setLeftpage(Integer leftpage) {
		this.leftpage = leftpage;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Integer getDeptcount() {
		return deptcount;
	}
	public void setDeptcount(Integer deptcount) {
		this.deptcount = deptcount;
	}
	public Integer getBottompage() {
		return bottompage;
	}
	public void setBottompage(Integer bottompage) {
		this.bottompage = bottompage;
	}
	public Integer getAlarmcount() {
		return alarmcount;
	}
	public void setAlarmcount(Integer alarmcount) {
		this.alarmcount = alarmcount;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getRunnumber() {
		return runnumber;
	}
	public void setRunnumber(String runnumber) {
		this.runnumber = runnumber;
	}
	
	
	
}
