/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dscomm.domain;

/**    
 *     
 * 项目名称：dscomm    
 * 类名称：Protocol    
 * 类描述：协议实体    
 * 创建人：zr    
 * 创建时间：2015-5-13 上午11:59:53    
 * 修改人：zr    
 * 修改时间：2015-5-13 上午11:59:53    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Protocol {
	
	/**备用1*/
	private String reserve1;
	
	/**备用2*/
	private String reserve2;
	
	/**消息长度*/
	private int msglen;
	
	/**消息ID*/
	private int msgid;
	
	/**终端设备号*/
	private String terminal;
	
	/**消息流水号*/
	private int seq;
	
	/**消息体*/
	private String msgbody;
	
	/**包总数*/
	private int totalnum;
	
	/**包序号*/
	private int serialnum;
	
	/**0：成功/确认；1：失败；2：消息有误；3：不支持*/
	private int result;
	
	/**协议类型 22 部标协议 23 雅迅协议**/
	private int devicetype;
	
	/**是否处理终端发送过来的消息*/
	private boolean flag;
	
	/**版本号*/
	private int version;
	
	/**优先级*/
	private int priority;
	
	
	public int getMsglen() {
		return msglen;
	}

	public void setMsglen(int msglen) {
		this.msglen = msglen;
	}

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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getMsgbody() {
		return msgbody;
	}

	public void setMsgbody(String msgbody) {
		this.msgbody = msgbody;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public int getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(int serialnum) {
		this.serialnum = serialnum;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}


	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
