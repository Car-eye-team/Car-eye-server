package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：wyd
 * @创建时间：2016-5-5 下午02:18:19
 * @修改人：wyd
 * @修改时间：2016-5-5 下午02:18:19
 * @修改备注：
 * @version 1.0
 */
public class IntercomGroupInvite {
	
	private Integer id;
	/**组名称**/
	private String name;
	/**描述**/
	private String remark;
	/**对讲组id**/
	private Integer gid;
	
	/**车辆id**/
	private Integer carid;
	
	/**是否同意	1同意2拒绝**/
	private Integer isagree;
	
	/**申请时间**/
	private String invitetime;
	/**同意时间**/
	private String agreedtime;
	
	private String carnumber;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getIsagree() {
		return isagree;
	}

	public void setIsagree(Integer isagree) {
		this.isagree = isagree;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	
	
}
