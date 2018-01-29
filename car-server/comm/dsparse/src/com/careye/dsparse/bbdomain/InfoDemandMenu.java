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
 * 类名称：InfoDemandMenu    
 * 类描述： 信息点播菜单   
 * 创建人：zr    
 * 创建时间：2015-6-4 下午07:40:43    
 * 修改人：zr    
 * 修改时间：2015-6-4 下午07:40:43    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class InfoDemandMenu extends BaseInfo{
	
	/**信息类型*/
	private int type;
	
	/**信息项总数*/
	private int count;
	
	/**信息项列表*/
	private  List<InfoDemandMenuItems> items = new ArrayList<InfoDemandMenuItems>();
	
	/**点播/取消标志*/
	private int demand;
	
	/**信息总长度*/
	private int len;
	
	/**信息内容*/
	private String content;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<InfoDemandMenuItems> getItems() {
		return items;
	}

	public void setItems(List<InfoDemandMenuItems> items) {
		this.items = items;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
