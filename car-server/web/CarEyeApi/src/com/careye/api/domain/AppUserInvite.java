package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-1-12 上午11:21:32
 * @修改人：ll
 * @修改时间：2016-1-12 上午11:21:32
 * @修改备注：
 * @version 1.0
 */
public class AppUserInvite {
	
	private Integer id;
	/**被邀请者用户ID**/
	private Integer userid;
	/**邀请者用户ID**/
	private Integer vid;
	/**邀请时间**/
	private String invitetime;
	/**同意时间**/
	private String agreedtime;
	/**是否同意**/
	private Integer isagree;
	/**备注信息**/
	private String remark;
	
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
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getInvitetime() {
		return invitetime;
	}
	public void setInvitetime(String invitetime) {
		this.invitetime = invitetime;
	}
	public String getAgreedtime() {
		return agreedtime;
	}
	public void setAgreedtime(String agreedtime) {
		this.agreedtime = agreedtime;
	}
	public Integer getIsagree() {
		return isagree;
	}
	public void setIsagree(Integer isagree) {
		this.isagree = isagree;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
