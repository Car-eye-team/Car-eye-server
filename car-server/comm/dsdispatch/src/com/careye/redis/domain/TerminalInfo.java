/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.redis.domain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：TerminalInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-9 上午08:58:34    
 * 修改人：zr    
 * 修改时间：2015-6-9 上午08:58:34    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TerminalInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**终端设备号*/
	private String terminal;
	
	/**缓存车辆信息mapkey*/
	private String mapkey;
	
	/**加入缓存时间*/
	private String addtime;

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
}
