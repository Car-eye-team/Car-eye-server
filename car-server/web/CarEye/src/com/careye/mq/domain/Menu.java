/**
* Description: 多森商用车平台
* 文件名：event.java
* 版本信息：1.0
* 日期：2014-5-27
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：FMS
 * @类名称：Menu
 * @类描述：信息点播菜单设置
 * @创建人：zr
 * @创建时间：2014-5-27 下午05:37:20
 * @修改人：zr
 * @修改时间：2014-5-27 下午05:37:20
 * @修改备注：
 * @version 1.0
 */
public class Menu {

	/**ID*/
	private int id;
	
	/**长度*/
	private int len;
	
	/**内容*/
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
