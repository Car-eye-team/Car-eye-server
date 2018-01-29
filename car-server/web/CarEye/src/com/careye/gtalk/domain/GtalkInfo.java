/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：GtalkInfo.java   
 * 版本信息：    
 * 日期：2016-1-7  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2016     
 * 版权所有    
 *    
 */
package com.careye.gtalk.domain;

/**    
 *     
 * 项目名称：CVPAPI    
 * 类名称：GtalkInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-1-7 下午03:32:43    
 * 修改人：Administrator    
 * 修改时间：2016-1-7 下午03:32:43    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GtalkInfo {
	
	/**用户名*/
	private String username;
	
	/**加密的密码数据*/
	private String encryptedPassword;
	
	/**名字*/
	private String name;
	
	/**电邮地址*/
	private String email;
	
	/**创建日期*/
	private String creationDate;
	
	/**最后更新日期*/
	private String modificationDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	
}
