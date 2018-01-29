/**
 * 
 */
package com.careye.advert.domain;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class AdvertCon extends BaseDomain{

	private Integer id;
	private Integer versionid;  	//版本id
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
	
	private Integer typeid;  	//类型id
	private String typename; 		//类型名称
	private String adname; 		//广告名称
	private String linkpath;	//链接地址
	private String path;		//图片
	
	
	private Integer connumber;
	private String con1;
	private String con2;
	private String con3;
	private String con4;
	private String con5;
	
	private String versionidStr;
	
	public String getVersionidStr() {
		return versionidStr;
	}
	public void setVersionidStr(String versionidStr) {
		this.versionidStr = versionidStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersionid() {
		return versionid;
	}
	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
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
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getLinkpath() {
		return linkpath;
	}
	public void setLinkpath(String linkpath) {
		this.linkpath = linkpath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getConnumber() {
		return connumber;
	}
	public void setConnumber(Integer connumber) {
		this.connumber = connumber;
	}
	public String getCon1() {
		return con1;
	}
	public void setCon1(String con1) {
		this.con1 = con1;
	}
	public String getCon2() {
		return con2;
	}
	public void setCon2(String con2) {
		this.con2 = con2;
	}
	public String getCon3() {
		return con3;
	}
	public void setCon3(String con3) {
		this.con3 = con3;
	}
	public String getCon4() {
		return con4;
	}
	public void setCon4(String con4) {
		this.con4 = con4;
	}
	public String getCon5() {
		return con5;
	}
	public void setCon5(String con5) {
		this.con5 = con5;
	}
	
	
	
}
