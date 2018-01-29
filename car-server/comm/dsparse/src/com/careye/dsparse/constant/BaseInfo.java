/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */


package com.careye.dsparse.constant;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：BaseInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-5-14 上午09:30:13    
 * 修改人：zr    
 * 修改时间：2015-5-14 上午09:30:13    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BaseInfo {

	/**协议消息ID*/
	private int msgid;
	
	/**总包数*/
	private int totalpacket;

	/**分包数*/
	private int subpacket;

	/**加密*/
	private int encryption;

	/**包长度*/
	private int bodysize;

	/**终端设备号*/
	private String terminal;

	/**消息流水号*/
	private int seq;
	
	/**预留字段*/
	private String reserve;
	

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	public int getSubpacket() {
		return subpacket;
	}

	public void setSubpacket(int subpacket) {
		this.subpacket = subpacket;
	}

	public int getEncryption() {
		return encryption;
	}

	public void setEncryption(int encryption) {
		this.encryption = encryption;
	}

	public int getBodysize() {
		return bodysize;
	}

	public void setBodysize(int bodysize) {
		this.bodysize = bodysize;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getTotalpacket() {
		return totalpacket;
	}

	public void setTotalpacket(int totalpacket) {
		this.totalpacket = totalpacket;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	

}
