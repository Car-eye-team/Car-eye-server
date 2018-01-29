/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：DataCompressReport    
 * 类描述： 数据压缩上报   
 * 创建人：zr    
 * 创建时间：2015-6-6 下午03:18:02    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午03:18:02    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DataCompressReport {

	/**  压缩消息长度 **/
	private int len;
	
	/**  压缩消息体**/
	private String data;

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
