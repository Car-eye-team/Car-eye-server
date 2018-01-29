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
 * 类名称：CanTotalBusDataUpload    
 * 类描述： CAN 总线数据上传   
 * 创建人：zr    
 * 创建时间：2015-6-5 下午04:32:57    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午04:32:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CanTotalBusDataUpload {

	/**  数据项个数  **/
	private int  count;

	/**  CAN 总线数据接收时间  **/
	private String  receivedate;

	/** CAN 总线数据项 **/
	private List<CanTotalBusDataUploadItems> items = new ArrayList<CanTotalBusDataUploadItems>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	public List<CanTotalBusDataUploadItems> getItems() {
		return items;
	}

	public void setItems(List<CanTotalBusDataUploadItems> items) {
		this.items = items;
	}

}
