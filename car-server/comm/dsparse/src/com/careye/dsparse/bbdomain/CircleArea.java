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
 * 类名称：CircleArea    
 * 类描述：圆形区域    
 * 创建人：zr    
 * 创建时间：2015-6-5 上午11:40:12    
 * 修改人：zr    
 * 修改时间：2015-6-5 上午11:40:12    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CircleArea extends BaseInfo{
	
	/**设置属性*/
	private int update;
	
	/**信息项总数*/
	private int count;
	
	/**信息项列表*/
	private  List<CircleAreaItems> items = new ArrayList<CircleAreaItems>();

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<CircleAreaItems> getItems() {
		return items;
	}

	public void setItems(List<CircleAreaItems> items) {
		this.items = items;
	}
	
	
}
