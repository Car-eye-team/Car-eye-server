/**
 * 
 */
package com.careye.advert.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class AdvertType extends BaseDomain{
	
	private Integer id;
	private String createtime;
	private String typename;
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
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	

}
