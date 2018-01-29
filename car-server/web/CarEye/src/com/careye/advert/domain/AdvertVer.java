/**
 * 
 */
package com.careye.advert.domain;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class AdvertVer extends BaseDomain{
	
	private Integer id;  			//id
	private String createtime;		//创建时间	
	private Integer positionid;		//位置id
	private String position;		//显示位置
	private String version;			//版本名称
	private String dtime;			//到期日期
	private Integer userid;			//发布人id
	private String username;		//发布人
	private String reltime;			//发布时间
	private Integer auditstatus;	//审核状态
	private Integer adminid;		//审核人id
	private String auditname;		//审核人
	private String audittime;		//审核时间
	private String auditremark;		//审核描述
	private Integer onstatus;		//在线状态
	private String remark;			//描述
	/**
	 * 版本号
	 */
	private String verid;
	
	
	public String getVerid() {
		return verid;
	}
	public void setVerid(String verid) {
		this.verid = verid;
	}
	private String positionidStr;
	
	
	
	public String getPositionidStr() {
		return positionidStr;
	}
	public void setPositionidStr(String positionidStr) {
		this.positionidStr = positionidStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getPositionid() {
		return positionid;
	}
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReltime() {
		return reltime;
	}
	public void setReltime(String reltime) {
		this.reltime = reltime;
	}
	public Integer getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getAuditname() {
		return auditname;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public String getAudittime() {
		return audittime;
	}
	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}
	public String getAuditremark() {
		return auditremark;
	}
	public void setAuditremark(String auditremark) {
		this.auditremark = auditremark;
	}
	public Integer getOnstatus() {
		return onstatus;
	}
	public void setOnstatus(Integer onstatus) {
		this.onstatus = onstatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
