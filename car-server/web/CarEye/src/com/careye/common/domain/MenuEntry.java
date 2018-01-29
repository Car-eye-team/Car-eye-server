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
 * @类名称：MenuEntry
 * @类描述：下拉菜单
 * @创建人：zhangrong
 * @创建时间：2014-2-26 上午10:49:10
 * @修改人：zhangrong
 * @修改时间：2014-2-26 上午10:49:10
 * @修改备注：
 * @version 1.0
 */
public class MenuEntry implements Serializable{

	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
	private static final long serialVersionUID = 1L;
	private String id;
    private String text;
    private String hrefTarget;
    private String parentId;
    private List<MenuEntry> items;
    private List<MenuEntry> menu;
	
	public MenuEntry() {
		super();
	}

	public MenuEntry(String id, String text, String hrefTarget,
			String parentId, List<MenuEntry> items) {
		super();
		this.id = id;
		this.text = text;
		this.hrefTarget = hrefTarget;
		this.parentId = parentId;
		this.items = items;
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

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<MenuEntry> getItems() {
		return items;
	}

	public void setItems(List<MenuEntry> items) {
		this.items = items;
	}

	public List<MenuEntry> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuEntry> menu) {
		this.menu = menu;
	}
	
	

	
}






