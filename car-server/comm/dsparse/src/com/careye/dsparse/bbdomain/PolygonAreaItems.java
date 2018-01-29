/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PolygonAreaItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午02:13:31    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午02:13:31    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PolygonAreaItems {
	
	/**区域ID1*/
	private int areaId;

	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;

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

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
}
