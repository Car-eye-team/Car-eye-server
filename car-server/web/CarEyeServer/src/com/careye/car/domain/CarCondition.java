package com.careye.car.domain;
/**
 * 
 * @项目名称：CVP
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午01:52:33
 * @修改人：ll
 * @修改时间：2015-10-20 下午01:52:33
 * @修改备注：
 * @version 1.0
 */
public class CarCondition {
	
	private Integer id;
	/**车辆id**/
	private Integer carid;
	/**创建时间**/
	private String createtime;
	/**在线时长	小时，精确到两位小数**/
	private Double inlinetime;
	/**空车时长	小时，精确到两位小数**/
	private Double offlinetime;
	/**载客时长	小时，精确到两位小数**/
	private Double passengertime;
	/**行驶里程	KM**/
	private Double drivermile;
	/**空车里程	KM**/
	private Double emptymile;
	/**载客里程	KM**/
	private Double passengermile;
	/**报警次数**/
	private Integer alarmcount;
	/**营业收入	元**/
	private Double income;
	/**等待时间	分钟**/
	private Double waittime;
	/**计费时间	分钟**/
	private Double feetime;
	/**载客次数**/
	private Integer passengercount;
	/**7:30至21：30载客时长**/
	private Double passengertime1;
	/**7:30至21：30行驶时长**/
	private Double drivertime1;
	/**7:30至21：30行驶里程**/
	private Double drivermile1;
	/**7:30至21：30载客里程**/
	private Double passengermile1;
	
	private String stime;
	private String etime;
	
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public Double getInlinetime() {
		return inlinetime;
	}
	public void setInlinetime(Double inlinetime) {
		this.inlinetime = inlinetime;
	}
	public Double getOfflinetime() {
		return offlinetime;
	}
	public void setOfflinetime(Double offlinetime) {
		this.offlinetime = offlinetime;
	}
	public Double getPassengertime() {
		return passengertime;
	}
	public void setPassengertime(Double passengertime) {
		this.passengertime = passengertime;
	}
	public Double getDrivermile() {
		return drivermile;
	}
	public void setDrivermile(Double drivermile) {
		this.drivermile = drivermile;
	}
	public Double getEmptymile() {
		return emptymile;
	}
	public void setEmptymile(Double emptymile) {
		this.emptymile = emptymile;
	}
	public Double getPassengermile() {
		return passengermile;
	}
	public void setPassengermile(Double passengermile) {
		this.passengermile = passengermile;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getWaittime() {
		return waittime;
	}
	public void setWaittime(Double waittime) {
		this.waittime = waittime;
	}
	public Double getFeetime() {
		return feetime;
	}
	public void setFeetime(Double feetime) {
		this.feetime = feetime;
	}
	public Double getPassengertime1() {
		return passengertime1;
	}
	public void setPassengertime1(Double passengertime1) {
		this.passengertime1 = passengertime1;
	}
	public Double getDrivertime1() {
		return drivertime1;
	}
	public void setDrivertime1(Double drivertime1) {
		this.drivertime1 = drivertime1;
	}
	public Double getDrivermile1() {
		return drivermile1;
	}
	public void setDrivermile1(Double drivermile1) {
		this.drivermile1 = drivermile1;
	}
	public Double getPassengermile1() {
		return passengermile1;
	}
	public void setPassengermile1(Double passengermile1) {
		this.passengermile1 = passengermile1;
	}
	public Integer getAlarmcount() {
		return alarmcount;
	}
	public void setAlarmcount(Integer alarmcount) {
		this.alarmcount = alarmcount;
	}
	public Integer getPassengercount() {
		return passengercount;
	}
	public void setPassengercount(Integer passengercount) {
		this.passengercount = passengercount;
	}
	
}
