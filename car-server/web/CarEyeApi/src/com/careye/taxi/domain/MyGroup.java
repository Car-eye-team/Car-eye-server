package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午02:03:36
 * @修改人：ll
 * @修改时间：2016-5-5 下午02:03:36
 * @修改备注：
 * @version 1.0
 */
public class MyGroup {
	
	private Integer gid;
	
	/**组名称**/
	private String name;
	
	/**描述**/
	private String remark;
	
	/**加入时间**/
	private String createtime;
	

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
