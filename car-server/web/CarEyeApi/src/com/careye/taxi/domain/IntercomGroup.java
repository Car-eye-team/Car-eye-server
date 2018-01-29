package com.careye.taxi.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午02:03:36
 * @修改人：ll
 * @修改时间：2016-5-5 下午02:03:36
 * @修改备注：
 * @version 1.0
 */
public class IntercomGroup {
	
	/****/
	private Integer id;
	
	/**车辆id**/
	private Integer carid;
	
	/**设备号**/
	private String terminal;
	/**申请者**/
	private String carnumber;
	
	/**组名称**/
	private String name;
	
	/**描述**/
	private String remark;
	
	/**创建时间**/
	private String createtime;
	
	/**经度**/
	private Double lng;
	
	/**纬度**/
	private Double lat;
	
	/**百度经度**/
	private Double blng;
	
	/**百度纬度**/
	private Double blat;
	
	/**百度地址**/
	private String baddress;
	
	/**省**/
	private String province;
	
	/**市**/
	private String city;
	
	/**县**/
	private String district;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getBlng() {
		return blng;
	}

	public void setBlng(Double blng) {
		this.blng = blng;
	}

	public Double getBlat() {
		return blat;
	}

	public void setBlat(Double blat) {
		this.blat = blat;
	}

	public String getBaddress() {
		return baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	
}
