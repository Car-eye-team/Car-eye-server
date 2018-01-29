package com.careye.customer.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-11 下午01:50:31
 * @修改人：ll
 * @修改时间：2016-5-11 下午01:50:31
 * @修改备注：
 * @version 1.0
 */
public class ComplaintType {
	
	private Integer id;
	
	/**投诉类型**/
	private Integer type;
	
	/**投诉类型名称**/
	private String typename;
	
	private String createtime;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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
	
}
