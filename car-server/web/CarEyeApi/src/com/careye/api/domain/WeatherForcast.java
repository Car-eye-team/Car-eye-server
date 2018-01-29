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
public class WeatherForcast {
	
	/**最高气温**/
	private String hightemp;
	
	/**风向**/
	private String fengxiang;
	
	/**最低气温**/
	private String lowtemp;
	
	/**风力**/
	private String fengli;
	
	/**日期(格式：yy-MM-dd)**/
	private String datetime;
	
	/**天气类型**/
	private String type;
	
	/**天气类型图片编码**/
	private Integer typeimg;
	
	/**星期**/
	private String week;

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

	public String getLowtemp() {
		return lowtemp;
	}

	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
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

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Integer getTypeimg() {
		return typeimg;
	}

	public void setTypeimg(Integer typeimg) {
		this.typeimg = typeimg;
	}
	
}
