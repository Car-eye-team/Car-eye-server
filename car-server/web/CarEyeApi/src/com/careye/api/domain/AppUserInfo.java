package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-1-11 上午10:53:33
 * @修改人：ll
 * @修改时间：2016-1-11 上午10:53:33
 * @修改备注：
 * @version 1.0
 */
public class AppUserInfo {
	
	private Integer id;
	
	/**登录名*/
	private String loginname;
	
	/**密码*/
	private String password;
	
	/**昵称**/
	private String nickname;
	
	/**姓名**/
	private String name;
	
	/**性别(0男1女)**/
	private Integer gender;
	
	/**地区**/
	private String region;
	
	/**生日**/
	private String birthday;
	
	/**QQ**/
	private String qq;
	
	/**邮箱**/
	private String email;
	
	/**联系电话1**/
	private String phone;
	
	/**联系电话2**/
	private String phone1;
	
	/**创建时间*/
	private String createtime;
	
	/**头像地址**/
	private String picpath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
}
