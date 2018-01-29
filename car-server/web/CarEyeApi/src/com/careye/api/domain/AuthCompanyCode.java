/**
 * 
 */
package com.careye.api.domain;

/**
 * @author Administrator
 *   鉴权公司鉴权码实体类
 */
public class AuthCompanyCode {
	
	private Integer id;
	private Integer companyid;	//鉴权公司id
	private String authcode;	//鉴权码
	private String imeinum;		//imei号
	private String iccid;		//iccid号 
	private String status;		//状态   1未使用 / 2已使用  
	private String usetime;		//使用时间
	private String createtime;	//创建时间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getImeinum() {
		return imeinum;
	}
	public void setImeinum(String imeinum) {
		this.imeinum = imeinum;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
	


}
