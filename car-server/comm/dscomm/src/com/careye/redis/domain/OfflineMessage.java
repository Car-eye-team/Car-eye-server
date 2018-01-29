/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.redis.domain;

import java.io.Serializable;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：OfflineMessage    
 * 类描述：终端设备离线消息    
 * 创建人：zr    
 * 创建时间：2015-5-19 上午09:19:21    
 * 修改人：zr    
 * 修改时间：2015-5-19 上午09:19:21    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class OfflineMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**消息ID*/
	private int msgid;
	
	/**终端设备号*/
	private String terminal;
	
	/**离线消息加入时间*/
	private String addtime;
	
	/**消息十六进制*/
	private String msghex;
	

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
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

	public String getMsghex() {
		return msghex;
	}

	public void setMsghex(String msghex) {
		this.msghex = msghex;
	}
	
	
}
