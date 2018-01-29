/**
 * 
 */
package com.careye.advert.domain;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class AdvertPos extends BaseDomain{
	
	private Integer id;
	private String createtime;
	private String position;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
