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
 * 类名称：VehicleControl    
 * 类描述： 车辆控制   
 * 创建人：zr    
 * 创建时间：2015-6-5 上午11:10:11    
 * 修改人：zr    
 * 修改时间：2015-6-5 上午11:10:11    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class VehicleControl extends BaseInfo{
	
	/**0：车门解锁；1：车门加锁*/
	private int ctrl;

	public int getCtrl() {
		return ctrl;
	}

	public void setCtrl(int ctrl) {
		this.ctrl = ctrl;
	}
	
	
	
}
