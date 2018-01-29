/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：Taximeter    
 * 类描述：计价器信息    
 * 创建人：zr    
 * 创建时间：2015-5-15 下午01:43:08    
 * 修改人：zr    
 * 修改时间：2015-5-15 下午01:43:08    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TaximeterInfo {
	
	
	/**车牌号*/
	private String carnum;
	
	/**PSAM卡号*/
	private String psam;
	
	/**计价器设备号*/
	private int taximeterno;
	
	/**司机标识号*/
	private int driverno;
	
	/**卡类型生成日期*/
	private String time;
	
	/**黑名单版本号*/
	private int blacklistver;
	
	/**软件版本号*/
	private int softever;

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public String getPsam() {
		return psam;
	}

	public void setPsam(String psam) {
		this.psam = psam;
	}

	public int getTaximeterno() {
		return taximeterno;
	}

	public void setTaximeterno(int taximeterno) {
		this.taximeterno = taximeterno;
	}


	public int getDriverno() {
		return driverno;
	}

	public void setDriverno(int driverno) {
		this.driverno = driverno;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getBlacklistver() {
		return blacklistver;
	}

	public void setBlacklistver(int blacklistver) {
		this.blacklistver = blacklistver;
	}

	public int getSoftever() {
		return softever;
	}

	public void setSoftever(int softever) {
		this.softever = softever;
	}
	
	
}
