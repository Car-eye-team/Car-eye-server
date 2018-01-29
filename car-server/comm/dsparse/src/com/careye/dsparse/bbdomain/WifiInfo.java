/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：WifiInfo    
 * 类描述：WIFI信息    
 * 创建人：zr    
 * 创建时间：2015-5-15 下午02:17:13    
 * 修改人：zr    
 * 修改时间：2015-5-15 下午02:17:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class WifiInfo extends BaseInfo{
	
	/**
	 * 关闭开启	1 打开 2 关闭
	 */
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
