/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.utlis;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：Gps    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-1 下午02:16:44    
 * 修改人：zr    
 * 修改时间：2015-6-1 下午02:16:44    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Gps {

	private double wgLat;
	private double wgLon;

	public Gps(double wgLat, double wgLon) {
		setWgLat(wgLat);
		setWgLon(wgLon);
	}

	public double getWgLat() {
		return wgLat;
	}

	public void setWgLat(double wgLat) {
		this.wgLat = wgLat;
	}

	public double getWgLon() {
		return wgLon;
	}

	public void setWgLon(double wgLon) {
		this.wgLon = wgLon;
	}

	@Override
	public String toString() {
		return wgLat + "," + wgLon;
	}
}
