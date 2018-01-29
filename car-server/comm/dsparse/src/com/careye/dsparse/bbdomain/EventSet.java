/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：EventSet    
 * 类描述：事件设置    
 * 创建人：zr    
 * 创建时间：2015-6-4 下午05:55:58    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午05:55:58    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class EventSet extends BaseInfo{
	
	/**事件ID*/
	private int id;
	
	/** 设置类型   **/
	private  int  type;
	
	/** 设置总数 **/
	private int  count;
	
	/**事件类型*/
	private int eventtype;
	
	/**发生原因*/
	private int reason;
	
	/**发生时间*/
	private String time;
	
	/** 事件项列表  **/
	private  List<EventTerm> items = new ArrayList<EventTerm>();

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<EventTerm> getItems() {
		return items;
	}

	public void setItems(List<EventTerm> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventtype() {
		return eventtype;
	}

	public void setEventtype(int eventtype) {
		this.eventtype = eventtype;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
