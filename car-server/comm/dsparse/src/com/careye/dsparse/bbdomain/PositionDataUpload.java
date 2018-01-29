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
 * 类名称：PositionDataUpload    
 * 类描述：定位数据上传实体    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午04:08:22    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午04:08:22    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PositionDataUpload {

	/** 数据项个数  **/
	private int  count;
	
	/**  位置数据类型  **/
	private int  type;
	
	/** 位置汇报数据项 **/
	private List<PositionDataUploadItems> items = new ArrayList<PositionDataUploadItems>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<PositionDataUploadItems> getItems() {
		return items;
	}

	public void setItems(List<PositionDataUploadItems> items) {
		this.items = items;
	}
	
	
}
