/**
* Description: 多森商用车平台
* 文件名：TelephoneBook.java
* 版本信息：1.0
* 日期：2014-5-29
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：FMS
 * @类名称：TelephoneBook
 * @类描述：电话本实体
 * @创建人：zr
 * @创建时间：2014-5-29 下午03:20:51
 * @修改人：zr
 * @修改时间：2014-5-29 下午03:20:51
 * @修改备注：
 * @version 1.0
 */
public class TelephoneBook {

	/**标志*/
	private int tag;
	
	/**号码长度*/
	private int telLen;
	
	/**电话号码*/
	private String tel;
	
	/**联系人长度*/
	private int ctcLen;
	
	/**联系人*/
	private String contact;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getTelLen() {
		return telLen;
	}

	public void setTelLen(int telLen) {
		this.telLen = telLen;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCtcLen() {
		return ctcLen;
	}

	public void setCtcLen(int ctcLen) {
		this.ctcLen = ctcLen;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
