/**
* Description: car-eye车辆管理平台
* 文件名：DeskMenu.java
* 版本信息：1.0
* 日期：2014-2-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @项目名称：car-eyeTms
 * @类名称：MenuTree
 * @类描述：桌面菜单
 * @创建人：zhangrong
 * @创建时间：2014-2-26 上午10:49:10
 * @修改人：zhangrong
 * @修改时间：2014-2-26 上午10:49:10
 * @修改备注：
 * @version 1.0
 */
public class MenuTree implements Serializable{

	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	private String id;
    private String text;
    private boolean leaf;
    private Integer leaftype;
    private boolean expanded;
    private String hrefTarget;
    private String parentId;
    private boolean checked;
    private List<MenuTree> children;
    
    private Integer carstatus;
    private Integer travelposition;
    
    private Integer total;//总数
    private Integer inline;//在线
    private Integer longoffline;//长离
	
	public MenuTree() {
		super();
	}
	
	public MenuTree(String id, String text, boolean leaf, Integer leaftype,
			boolean expanded, String hrefTarget, String parentmenuid,
			List<MenuTree> children) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.leaftype = leaftype;
		this.expanded = expanded;
		this.hrefTarget = hrefTarget;
		this.parentId = parentmenuid;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Integer getLeaftype() {
		return leaftype;
	}

	public void setLeaftype(Integer leaftype) {
		this.leaftype = leaftype;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getCarstatus() {
		return carstatus;
	}

	public void setCarstatus(Integer carstatus) {
		this.carstatus = carstatus;
	}

	public Integer getTravelposition() {
		return travelposition;
	}

	public void setTravelposition(Integer travelposition) {
		this.travelposition = travelposition;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getInline() {
		return inline;
	}

	public void setInline(Integer inline) {
		this.inline = inline;
	}

	public Integer getLongoffline() {
		return longoffline;
	}

	public void setLongoffline(Integer longoffline) {
		this.longoffline = longoffline;
	}

	
	
}
