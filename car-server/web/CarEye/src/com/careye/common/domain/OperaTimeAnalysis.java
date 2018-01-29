/**
 * 
 */
package com.careye.common.domain;

import java.io.Serializable;

import com.careye.base.action.BaseDomain;

/**
 * @author Administrator
 *
 */
public class OperaTimeAnalysis extends BaseDomain implements Serializable{
	
	private Integer id;				//id
	private Integer carid;			//车辆id
	private String carnumber;		//车牌号码
	private Double inlinetime;		//在线时长
	private Double offlinetime;		//空车时长
	private Double passengertime;	//载客时长
	private Double drivertime;		//行驶时长
	private String drivermile;		//行驶里程
	private String emptymile;		//空车里程
	private String passengermile;	//载客里程
	private Integer alarmcount;		//报警次数
	private String sumdrivermila;	//行驶总里程
	private String income;			//营业收入
	private String waittime;		//等待时间
	private String feetime;			//计费时间
	private Integer passengercount;	//载客次数
	private String createtime;		//创建时间
	private Double passengertime1;  //7:30至21：30载客时长
	private Double drivertime1;     //7:30至21：30行驶时长
	private String drivermile1;     //7:30至21：30行驶里程
	private String passengermile1;  //7:30至21：30载客里程
	private String drivermilepercent;//日均7:30-21:30行驶总比例
	private String passengermilepercent;//日均7:30-21:30载客里程比例
	private String inlinetimepercent;//日均7:30-21:30在线时长比例
	private String passengertimepercent;//日均7:30-21:30载客时长比例
	
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
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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
	public Double getDrivertime() {
		return drivertime;
	}
	public void setDrivertime(Double drivertime) {
		this.drivertime = drivertime;
	}
	public String getDrivermile() {
		return drivermile;
	}
	public void setDrivermile(String drivermile) {
		this.drivermile = drivermile;
	}
	public String getEmptymile() {
		return emptymile;
	}
	public void setEmptymile(String emptymile) {
		this.emptymile = emptymile;
	}
	public String getPassengermile() {
		return passengermile;
	}
	public void setPassengermile(String passengermile) {
		this.passengermile = passengermile;
	}
	public Integer getAlarmcount() {
		return alarmcount;
	}
	public void setAlarmcount(Integer alarmcount) {
		this.alarmcount = alarmcount;
	}
	public String getSumdrivermila() {
		return sumdrivermila;
	}
	public void setSumdrivermila(String sumdrivermila) {
		this.sumdrivermila = sumdrivermila;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getWaittime() {
		return waittime;
	}
	public void setWaittime(String waittime) {
		this.waittime = waittime;
	}
	public String getFeetime() {
		return feetime;
	}
	public void setFeetime(String feetime) {
		this.feetime = feetime;
	}
	public Integer getPassengercount() {
		return passengercount;
	}
	public void setPassengercount(Integer passengercount) {
		this.passengercount = passengercount;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	public String getDrivermile1() {
		return drivermile1;
	}
	public void setDrivermile1(String drivermile1) {
		this.drivermile1 = drivermile1;
	}
	public String getPassengermile1() {
		return passengermile1;
	}
	public void setPassengermile1(String passengermile1) {
		this.passengermile1 = passengermile1;
	}
	public String getDrivermilepercent() {
		return drivermilepercent;
	}
	public void setDrivermilepercent(String drivermilepercent) {
		this.drivermilepercent = drivermilepercent;
	}
	public String getPassengermilepercent() {
		return passengermilepercent;
	}
	public void setPassengermilepercent(String passengermilepercent) {
		this.passengermilepercent = passengermilepercent;
	}
	public String getInlinetimepercent() {
		return inlinetimepercent;
	}
	public void setInlinetimepercent(String inlinetimepercent) {
		this.inlinetimepercent = inlinetimepercent;
	}
	public String getPassengertimepercent() {
		return passengertimepercent;
	}
	public void setPassengertimepercent(String passengertimepercent) {
		this.passengertimepercent = passengertimepercent;
	}


}
