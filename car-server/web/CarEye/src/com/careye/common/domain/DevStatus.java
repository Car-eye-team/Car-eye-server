package com.careye.common.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-27 下午04:15:12
 * @修改人：ll
 * @修改时间：2016-5-27 下午04:15:12
 * @修改备注：
 * @version 1.0
 */
public class DevStatus {
	
	private Integer id;
	
	private String devidno;
	
	private String updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevidno() {
		return devidno;
	}

	public void setDevidno(String devidno) {
		this.devidno = devidno;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
}
