package com.careye.car.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-27 上午09:34:38
 * @修改人：ll
 * @修改时间：2015-10-27 上午09:34:38
 * @修改备注：
 * @version 1.0
 */
public class OperateCertificate {
	
	/**编号**/
	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	/**营运证号**/
	private String operatenumber;
	
	/**营运状态**/
	private Integer operatestatus;
	
	/**营运性质**/
	private String operateproperty;
	
	/**发证日期**/
	private String licensetime;
	
	/**办证类别**/
	private String certificatetype;
	
	/**首次登记日期**/
	private String firstregisttime;
	
	/**录入人**/
	private String entryperson;
	
	/**录入日期**/
	private String entrytime;
	
	/**备注**/
	private String remark;
	
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperatenumber() {
		return operatenumber;
	}

	public void setOperatenumber(String operatenumber) {
		this.operatenumber = operatenumber;
	}

	public Integer getOperatestatus() {
		return operatestatus;
	}

	public void setOperatestatus(Integer operatestatus) {
		this.operatestatus = operatestatus;
	}

	public String getOperateproperty() {
		return operateproperty;
	}

	public void setOperateproperty(String operateproperty) {
		this.operateproperty = operateproperty;
	}

	public String getLicensetime() {
		return licensetime;
	}

	public void setLicensetime(String licensetime) {
		this.licensetime = licensetime;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getFirstregisttime() {
		return firstregisttime;
	}

	public void setFirstregisttime(String firstregisttime) {
		this.firstregisttime = firstregisttime;
	}

	public String getEntryperson() {
		return entryperson;
	}

	public void setEntryperson(String entryperson) {
		this.entryperson = entryperson;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
