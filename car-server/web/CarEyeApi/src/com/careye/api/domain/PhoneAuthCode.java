/**
* Description: car-eye车辆管理平台
* 文件名：DriverInfo.java
* 版本信息：1.0
* 日期：2015-1-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.api.domain;

/**
 * @项目名称：OCS
 * @类名称：PhoneAuthCode
 * @类描述：1.3.3	手机验证码
 * @创建人：zhangrong
 * @创建时间：2015-1-22 下午06:17:33
 * @修改人：zhangrong
 * @修改时间：2015-1-22 下午06:17:33
 * @修改备注：
 * @version 1.0
 */
public class PhoneAuthCode {
	
	private Integer id;
	private String content;
	
	/**手机号*/
	private String phone;
	
	/**验证码*/
	private String authcode;
	
	/**创建时间*/
	private String createtime;

	
	
	public PhoneAuthCode() {
		super();
	}

	public PhoneAuthCode(String content, String phone, String code,String createtime) {
		super();
		this.content = content;
		this.phone = phone;
		this.authcode = code;
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	

	
	
}



