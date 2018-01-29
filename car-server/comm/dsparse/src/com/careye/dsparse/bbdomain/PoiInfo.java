/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PoiInfo    
 * 类描述： 一键导航 
 * 创建人：zr    
 * 创建时间：2015-5-15 上午11:32:05    
 * 修改人：zr    
 * 修改时间：2015-5-15 上午11:32:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PoiInfo extends BaseInfo{

	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/**目的地*/
	private String addr;

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
