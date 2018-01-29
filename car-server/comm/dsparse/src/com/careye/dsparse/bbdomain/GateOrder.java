/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：GateOrder
 * @类描述：飞驿网订单信息
 * @创建人：zhangrong
 * @创建时间：2015-7-7 下午05:57:53
 * @修改人：zhangrong
 * @修改时间：2015-7-7 下午05:57:53
 * @修改备注：
 * @version 1.0
 */
public class GateOrder {
	
	/**货源ID*/
	private int sgid;
	
	/**车源ID*/
	private int carsourceid;
	
	/**生效状态0  失效 1 生效*/
	private int effect;
	
	/**结果   0x7102 0 确认订单，1取消订单*/
	private int result;
	
	/**纬度*/
	private int lat;
	
	/**经度*/
	private int lng;

	/**订单ID*/
	private String orderid;
	
	/**投诉类型 1未提供货物*/
	private int type;
	
	/**投诉描述*/
	private String desc;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getSgid() {
		return sgid;
	}

	public void setSgid(int sgid) {
		this.sgid = sgid;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
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

	public int getCarsourceid() {
		return carsourceid;
	}

	public void setCarsourceid(int carsourceid) {
		this.carsourceid = carsourceid;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}
	
	
	
}
