/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：PositionDataUploadItems    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午04:09:57    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午04:09:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class PositionDataUploadItems {
	
	/**  位置汇报数据体长度   **/
	private int  len;
	
	/** 位置汇报数据体  **/
	private PositionInfo  position;

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public PositionInfo getPosition() {
		return position;
	}

	public void setPosition(PositionInfo position) {
		this.position = position;
	}
	
	
}
