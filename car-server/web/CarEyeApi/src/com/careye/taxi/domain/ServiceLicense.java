package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-23 下午02:19:45
 * @修改人：ll
 * @修改时间：2016-5-23 下午02:19:45
 * @修改备注：
 * @version 1.0
 */
public class ServiceLicense {
	
	private Integer id;
	
	/**驾驶员id**/
	private Integer driverid;
	
	/**创建时间**/
	private String createtime;
	
	/**姓名**/
	private String name;
	
	/**上次照片路径**/
	private String picturepath;
	
	/**服务证号**/
	private String servicenumber;
	
	/**车牌号**/
	private String carnumber;
	private String terminal;
	
	/**证件状态(1正常2注销)**/
	private Integer zjstatus;
	
	/**登记日期**/
	private String registtime;
	
	/**发证日期**/
	private String fztime;
	
	/**星级**/
	private String starlevel;
	
	/**版本(YYMMDDhhmmss)**/
	private String version;
	
	/**有效期(年)**/
	private String validity;
	
	/**车辆id**/
	private Integer carid;
	
	/**组织机构*/
	private Integer blocid;
	/**组织机构*/
	private String blocname;
	
	/**用户ID*/
	private Integer userid;
	
	/**姓名*/
	private String drivername;
	
	/**手机号*/
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverid() {
		return driverid;
	}

	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getServicenumber() {
		return servicenumber;
	}

	public void setServicenumber(String servicenumber) {
		this.servicenumber = servicenumber;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Integer getZjstatus() {
		return zjstatus;
	}

	public void setZjstatus(Integer zjstatus) {
		this.zjstatus = zjstatus;
	}

	public String getRegisttime() {
		return registtime;
	}

	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}

	public String getFztime() {
		return fztime;
	}

	public void setFztime(String fztime) {
		this.fztime = fztime;
	}

	public String getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public Integer getBlocid() {
		return blocid;
	}

	public void setBlocid(Integer blocid) {
		this.blocid = blocid;
	}

	public String getBlocname() {
		return blocname;
	}

	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
