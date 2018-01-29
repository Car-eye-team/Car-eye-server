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
 * 类名称：TextInfo    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-4 上午10:26:41    
 * 修改人：zr    
 * 修改时间：2015-6-4 上午10:26:41    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TextInfo extends BaseInfo{

	/**文本信息*/
	private String content;

	/**纬度*/
	private int lat;

	/**经度*/
	private int lng;

	/***/
	private String ordid;

	/**1：紧急*/
	private int emergency;

	/**1：终端显示器显示*/
	private int lcd;

	/**1：终端TTS播读*/
	private int tts;

	/**1：广告屏显示*/
	private int adv;

	/**0：中心导航信息，1：CAN 故障码信息*/
	private int action;


	private int dist;

	private int type;

	private int len;

	private String tel;

	private String phone;

	private int bid;

	/**发送人*/
	private String sender;

	/**发送时间*/
	private String sendtime;

	/**设备存储地址*/
	private String path;
	
	/**文本序号*/
	private int textseq;
	
	/**显示时长*/
	private int time;
	
	/**标志*/
	private int flag;
	
	/**文本信息*/
	private String textdata;
	

	public int getTextseq() {
		return textseq;
	}

	public void setTextseq(int textseq) {
		this.textseq = textseq;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

	public int getEmergency() {
		return emergency;
	}

	public void setEmergency(int emergency) {
		this.emergency = emergency;
	}

	public int getLcd() {
		return lcd;
	}

	public void setLcd(int lcd) {
		this.lcd = lcd;
	}

	public int getTts() {
		return tts;
	}

	public void setTts(int tts) {
		this.tts = tts;
	}

	public int getAdv() {
		return adv;
	}

	public void setAdv(int adv) {
		this.adv = adv;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTextdata() {
		return textdata;
	}

	public void setTextdata(String textdata) {
		this.textdata = textdata;
	}
	
	

}
