/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TerminalDetect    
 * 类描述： 设备检测   
 * 创建人：zr    
 * 创建时间：2015-10-16 上午10:56:35    
 * 修改人：zr    
 * 修改时间：2015-10-16 上午10:56:35    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalDetect {
	
	/**自检时间*/
	private String time;
	
	/**自检报告总数*/
	private int count;
	
	/**自检报告列表*/
	private  List<TerminalDetectItems> items = new ArrayList<TerminalDetectItems>();

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<TerminalDetectItems> getItems() {
		return items;
	}

	public void setItems(List<TerminalDetectItems> items) {
		this.items = items;
	}
	
}
