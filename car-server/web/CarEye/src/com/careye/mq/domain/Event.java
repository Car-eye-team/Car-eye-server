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
 * @类名称：Event
 * @类描述：事件设置实体
 * @创建人：zr
 * @创建时间：2014-5-27 下午05:37:20
 * @修改人：zr
 * @修改时间：2014-5-27 下午05:37:20
 * @修改备注：
 * @version 1.0
 */
public class Event {

	/**事件ID*/
	private int id;
	
	/**事件内容长度*/
	private int len;
	
	/**事件内容*/
	private String content;
	
	private int seq;

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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
	
}
