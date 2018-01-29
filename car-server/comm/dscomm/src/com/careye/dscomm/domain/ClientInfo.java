/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.domain;

import org.apache.mina.core.session.IoSession;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：ClientInfo    
 * 类描述： 客户端实体   
 * 创建人：zr    
 * 创建时间：2015-5-9 下午12:42:45    
 * 修改人：zr    
 * 修改时间：2015-5-9 下午12:42:45    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class ClientInfo {
	
	/**客户端链路信息*/
	private IoSession session;
	
	/**终端设备号*/
	private String terminal;
	
	/**加入时间**/
	private String addtime;
	
	/**设备类型*/
	private int devicetype;
	
	/**备用字段*/
	private String reserve1;
	
	/**备用字段*/
	private String reserve2;

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	
}
