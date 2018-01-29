/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：ReturnRecord
 * @类描述：回城状态记录
 * @创建人：zhangrong
 * @创建时间：2015-7-8 上午09:51:39
 * @修改人：zhangrong
 * @修改时间：2015-7-8 上午09:51:39
 * @修改备注：
 * @version 1.0
 */
public class ReturnRecord {
	
	/**状态 1 回程 2 装货 3 卸货 4 到达*/
	private int status;
	/**装货、卸货重量*/
	private float weight;
	/**纬度*/
	private int lat;
	/**经度*/
	private int lng;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
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

}
