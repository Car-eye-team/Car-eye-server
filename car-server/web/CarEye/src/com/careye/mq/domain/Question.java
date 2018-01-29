/**
* Description: 多森商用车平台
* 文件名：Question.java
* 版本信息：1.0
* 日期：2014-5-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：FMS
 * @类名称：Question
 * @类描述：提问下发
 * @创建人：zr
 * @创建时间：2014-5-29 下午02:23:05
 * @修改人：zr
 * @修改时间：2014-5-29 下午02:23:05
 * @修改备注：
 * @version 1.0
 */
public class Question {
	
	/**ID*/
	private int id;
	
	/**内容长度*/
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
