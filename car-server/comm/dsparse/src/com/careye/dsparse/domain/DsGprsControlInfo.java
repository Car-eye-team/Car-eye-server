/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：DsGprsControlInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-12-7 上午10:40:18    
 * 修改人：Administrator    
 * 修改时间：2015-12-7 上午10:40:18    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class DsGprsControlInfo extends BaseInfo{
	
	/**目前电机开关的状态 0x00:电机关机 0x01:电机开机*/
	private int state;
	
	/**结果 0x00:失败； 0x01:成功*/
	private int result;
	
	/**控制命令 0x00:关机；  0x01:开机*/
	private int cmd;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	
	
	
}
