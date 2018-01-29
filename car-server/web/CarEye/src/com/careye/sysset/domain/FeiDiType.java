package com.careye.sysset.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @项目名称：IMS
 * @类名称：NoticeType
 * @类描述：公告类型表
 * @创建人：huangqin
 * @创建时间：2014-12-19 上午09:19:56
 * @修改人：huangqin
 * @修改时间：2014-12-19 上午09:19:56
 * @修改备注：
 * @version 1.0
 */
public class FeiDiType  implements Serializable{
	/**  主键 **/
	private Integer id;
	/**  公告类型名称 **/
	private String typename;
	/**  用户id **/
	private Integer userid;
	/**  创建时间**/
	private String createtime;
	/**  开始时间 **/
	private String stime;
	/**  结束时间**/
	private String etime;
	/**  用户名称 **/
	private String username;
	/** 备注 **/
	private String remark;
	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	private int typeid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}