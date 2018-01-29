/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：SrdT6InfoItems    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-12-22 下午12:21:26    
 * 修改人：Administrator    
 * 修改时间：2015-12-22 下午12:21:26    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class SrdT6InfoItems {
	
	private int seq;
	
	/**定位类型*/
	private int gpsflag;
	
	/**经度*/
	private String lng;
	
	/**纬度*/
	private String lat;
	
	private int num;
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(int gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
	
}
