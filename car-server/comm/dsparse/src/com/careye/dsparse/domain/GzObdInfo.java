/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：GzObdInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-6-30 下午07:32:28    
 * 修改人：Administrator    
 * 修改时间：2016-6-30 下午07:32:28    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class GzObdInfo {
	
	/**故障码数*/
	private int faultcode;
	
	/**发动机负荷*/
	private int cvol;
	
	/**水温*/
	private int temp;
	
	/**发动机转速*/
	private int rpm;
	
	/**车速*/
	private String spd;
	
	/**发动机运行时间*/
	private int runtime;
	
	/**蓄电池电压*/
	private String vbat;
	
	/**空燃比系数*/
	private String arc;
	
	/**车外环境温度*/
	private int atemp;
	
	/**节气门开度*/
	private int tp;
	
	/**每小时油耗*/
	private String houroil;
	
	/**平均油耗*/
	private String oilwear;
	
	/**里程*/
	private String mileages;
	
	/**剩余油量*/
	private String fli;

	public int getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(int faultcode) {
		this.faultcode = faultcode;
	}

	public int getCvol() {
		return cvol;
	}

	public void setCvol(int cvol) {
		this.cvol = cvol;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getRpm() {
		return rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}

	public String getSpd() {
		return spd;
	}

	public void setSpd(String spd) {
		this.spd = spd;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getVbat() {
		return vbat;
	}

	public void setVbat(String vbat) {
		this.vbat = vbat;
	}

	public String getArc() {
		return arc;
	}

	public void setArc(String arc) {
		this.arc = arc;
	}

	public int getAtemp() {
		return atemp;
	}

	public void setAtemp(int atemp) {
		this.atemp = atemp;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public String getHouroil() {
		return houroil;
	}

	public void setHouroil(String houroil) {
		this.houroil = houroil;
	}

	public String getOilwear() {
		return oilwear;
	}

	public void setOilwear(String oilwear) {
		this.oilwear = oilwear;
	}

	public String getMileages() {
		return mileages;
	}

	public void setMileages(String mileages) {
		this.mileages = mileages;
	}

	public String getFli() {
		return fli;
	}

	public void setFli(String fli) {
		this.fli = fli;
	}
	
	
	
	
}
