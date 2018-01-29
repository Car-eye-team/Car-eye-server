package com.careye.api.domain;
/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-3-25 下午02:55:55
 * @修改人：ll
 * @修改时间：2016-3-25 下午02:55:55
 * @修改备注：
 * @version 1.0
 */
public class WeatherInfo {
	
	/****/
	private Integer id;
	
	/**天气城市表ID**/
	private Integer cityid;
	
	/**城市**/
	private String cityname;
	
	/**城市代码**/
	private String citycode;
	
	/**最高气温**/
	private String hightemp;
	
	/**风向**/
	private String fengxiang;
	
	/**当前温度**/
	private String curTemp;
	
	/**最低气温**/
	private String lowtemp;
	
	/**空气质量指数**/
	private String aqi;
	
	/**风力**/
	private String fengli;
	
	/**日期(格式：yy-MM-dd)**/
	private String datetime;
	
	/**天气类型**/
	private String type;
	
	/**天气类型图片编码**/
	private Integer typeimg;
	
	/**创建时间**/
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getHightemp() {
		return hightemp;
	}

	public void setHightemp(String hightemp) {
		this.hightemp = hightemp;
	}

	public String getFengxiang() {
		return fengxiang;
	}

	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}

	public String getCurTemp() {
		return curTemp;
	}

	public void setCurTemp(String curTemp) {
		this.curTemp = curTemp;
	}

	public String getLowtemp() {
		return lowtemp;
	}

	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getFengli() {
		return fengli;
	}

	public void setFengli(String fengli) {
		this.fengli = fengli;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeimg() {
		return typeimg;
	}

	public void setTypeimg(Integer typeimg) {
		this.typeimg = typeimg;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
