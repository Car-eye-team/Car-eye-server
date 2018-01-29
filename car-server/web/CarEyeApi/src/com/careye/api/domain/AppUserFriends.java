package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-1-12 上午11:19:30
 * @修改人：ll
 * @修改时间：2016-1-12 上午11:19:30
 * @修改备注：
 * @version 1.0
 */
public class AppUserFriends {
	
	private Integer id;
	/**用户id**/
	private Integer userid;
	/**车友id**/
	private Integer vid;
	/**创建时间**/
	private String createtime;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
