package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午02:18:19
 * @修改人：ll
 * @修改时间：2016-5-5 下午02:18:19
 * @修改备注：
 * @version 1.0
 */
public class IntercomGroupMember {
	
	private Integer id;
	
	/**对讲组id**/
	private Integer gid;
	
	/**车辆id**/
	private Integer carid;
	
	/**是否为管理员**/
	private Integer isadmin;
	
	/**加入时间**/
	private String createtime;

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

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
