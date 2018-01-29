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
 * 类名称：OperationNotice    
 * 类描述：操作通知    
 * 创建人：zr    
 * 创建时间：2015-5-15 下午01:30:51    
 * 修改人：zr    
 * 修改时间：2015-5-15 下午01:30:51    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class OperationNotice extends BaseInfo{
	
	/**操作类型 1 增加车辆 2 修改车辆 3 删除车辆*/
	private int type;
	
	/**车牌号*/
	private String carnumber;
	
	/**设备号*/
	private String terminal;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	
}
