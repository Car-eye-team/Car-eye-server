package com.careye.mq.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @项目名称：TAXISERVER
 * @类名称：Protocol
 * @类描述：协议实体
 * @创建人：zr
 * @创建时间：2015-3-11 下午07:28:24
 * @修改人：zr
 * @修改时间：2015-3-11 下午07:28:24
 * @修改备注：
 * @version 1.0
 */
public class Protocol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 协议编号 */
	private int msgid;

	/** 终端号码 */
	private String terminal;

	/** 应答序列编号 */
	private int respseq;

	/** 应答序列编号 */
	private int seqR;

	/** 应答协议编号 */
	private int respmsgid;

	/** 序列号 */
	private int seq;

	/** 方向 */
	private String direction;

	/** 时间 */
	private String time;

	/** 经度 */
	private int lng;

	/** 纬度 */
	private int lat;

	/** 速度 */
	private String speed;

	/** 海拔 */
	private String altitude;

	/** 里程 */
	private float mileage;

	/**acc点火	0 无 1 点火*/
	private int acc;

	/**GPS是否有效0为无效,1为有效,2为上次信号有效时的位置*/
	private int gpsflg;

	/***设备类型**/
	private int devicetype;

	/**
	 * result结果 0：成功/确认；1：失败；2：消息有误；3：不支持
	 */
	private int result;
	
	/**
	 * 车牌号
	 */
	private String plateCode;
	
	private String number;

	/******************** 报警参数 *************************/

	/** 防劫报警 */
	private int a0;

	/** 预警 */
	private int a1;

	/** 卫星定位模块发生故障 */
	private int a2;

	/** 卫星定位天线未接或被剪断 */
	private int a3;

	/** 卫星定位天线短路 */
	private int a4;

	/** 终端主电源欠压 */
	private int a5;

	/** 终端主电源掉电 */
	private int a6;

	/** 液晶（LCD)显示终端故障 */
	private int a7;

	/** 语音合成（TTS)模块故障 */
	private int a8;

	/** 摄像头故障 */
	private int a9;

	/** 计价器故障*/
	private int a10;

	/**服务评价器故障 */
	private int a11;

	/**LED广告屏故障*/
	private int a12;

	/**液晶（LCD)显示屏故障*/
	private int a13;

	/**安全访问模块故障*/
	private int a14;

	/** 当天累计驾驶超时 */
	private int a18;

	/** 超时停车 */
	private int a19;

	/** 进出区域/路线 */
	private int a20;

	/** 路段行驶时间不足/过长 */
	private int a21;

	/** 禁行路段行驶 */
	private int a22;

	/** 车速传感器故障 */
	private int a23;

	/** 车辆非法点火 */
	private int a24;

	/** 车辆非法位移 */
	private int a25;

	/** 智能终端存储异常 */
	private int a26;

	/** 刷卡模块故障 */
	private int a27;

	/** LED顶灯故障 */
	private int a15;

	/** 超速报警 */
	private int a16;

	/** 连续驾驶超时*/
	private int a17;

	/******************** 报警参数 *************************/

	/** 0: ACC关;1:ACC开 */
	private int s0;

	/** 0:未定位;1:定位 */
	private int s1;

	/** 0:北纬:1:南纬 */
	private int s2;

	/** 0:东经;1:西经 */
	private int s3;

	/** 0:运营状态:1:停运状态 */
	private int s4;

	/** 0:经纬度未经保密插件加密;l:经纬度已经保密插件加密 */
	private int s5;

	/** 00：等待新行程 01：行程开始 10：正在行驶 11：行程结束 (6,7组合) */
	private int s6;

	/** 00：等待新行程 01：行程开始 10：正在行驶 11：行程结束 (6,7组合) */
	private int s7;

	/** 0空车 1 重车 */
	private int s9;

	/** 0:车辆油路正常:1:车辆油路断开 */
	private int s10;

	/** 0:车辆电路正常:1:车辆电路断开 */
	private int s11;

	/** 0:车门解锁；1：车门加锁 */
	private int s12;

	
	private int s18;

	/******************** 电召参数 *************************/

	/**订单号*/
	private String orderid;
	
	/**订单状态*/
	private Integer orderstatus;
	
	/**设备号字符串*/
	private String ters;
	
	/**完成时间*/
	private String completiontime;
	
	/**评价星级**/
	private Integer level;
	
	/**评价内容**/
	private String content;

	
	/******************** 计价器开钥匙门信息 *************************/
	
	/**司机标识号(服务监督卡号)*/
	private String driverno;
	
	/**计价器设备号*/
	private String carnum;
	
	/**司机标识号*/
	private String psam;
	
	/**卡类型生成日期*/
	private int taximeterno;
	
	/**黑名单版本号*/
	private int blacklistver;
	
	/**软件版本号*/
	private int softever;
	
	/****************** 服务评价 *********************/
	
	/**1．绕路  2.有异味  3.服务态度不好 如果选择非常满意、满意、一般时 该值填0*/
	private int reason;
	
	/******************日使用上报 *********************/
	
	/**功能类型1 考拉FM*/
	private int type;
	
	/**使用时长 秒*/
	private int usinglen;
	
	/**使用流量 Kb / 默认为0*/
	private String flow;
	
	
	private int totalpacket;
	private int subpacket;
	
	private int encryption;
	
	private int bodysize;
	
	private List items;
	
	private int id;
	
	/*****************多媒体数据***********************/
	/**多媒体数据*/
	private String mediaData;
	
	/**多媒体ID*/
	private int dataId;
	
	/**多媒体类型*/
	private int mediaType;
	
	/**多媒体格式编码*/
	private int format;
	
	/**事件项编码*/
	private int eventId;
	
	/**通道ID*/
	private int wayId;
	
	/**数据总包数*/
	private int packetSum;
	
	/**包ID*/
	private int packetId;
	
	
	/***************上下签***********************/
	/**总营运次数**/
	private Integer count;
	/**脉冲数**/
	private Integer mcs;
	/**卡次**/
	private Integer cardnum;
	/**总营运次数**/
	private Integer totalnumber;
	/**单位代码**/
	private String companycode;
	/**司机代码**/
	private String drivercode;
	/**车辆自编号**/
	private String vehicleid;
	/**时间**/
	private String signintime;
	/**驾驶员唯一编号**/
	private String driverid;
	/**上班时间**/
	private String stime;
	/**下班时间**/
	private String etime;
	/**当班公里**/
	private String dbmileage;
	/**当班营运公里**/
	private String dbyymileage;
	/**计时时间	秒**/
	private String jstmie;
	/**总计金额**/
	private String totalamount;
	/**卡收金额**/
	private String cardamount;
	/**班间公里	上一班签退到本班签到的公里数**/
	private String bjmileage;
	/**总计公里	计价器安装后累积的里程**/
	private String totalmileage;
	/**总营运公里	计价器安装后累积的里程**/
	private String totalyymileage;
	/**单价	格式XX.XX元**/
	private String price;
	/**总等待时间	秒**/
	private String totalwaittime;
	/**车次**/
	private Integer vehicletrips;
	
	
	

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getWayId() {
		return wayId;
	}

	public void setWayId(int wayId) {
		this.wayId = wayId;
	}

	public int getPacketSum() {
		return packetSum;
	}

	public void setPacketSum(int packetSum) {
		this.packetSum = packetSum;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
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

	public int getRespseq() {
		return respseq;
	}

	public void setRespseq(int respseq) {
		this.respseq = respseq;
	}

	public int getSeqR() {
		return seqR;
	}

	public void setSeqR(int seqR) {
		this.seqR = seqR;
	}

	public int getRespmsgid() {
		return respmsgid;
	}

	public void setRespmsgid(int respmsgid) {
		this.respmsgid = respmsgid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public int getGpsflg() {
		return gpsflg;
	}

	public void setGpsflg(int gpsflg) {
		this.gpsflg = gpsflg;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getPlateCode() {
		return plateCode;
	}

	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode;
	}

	public int getA0() {
		return a0;
	}

	public void setA0(int a0) {
		this.a0 = a0;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getA3() {
		return a3;
	}

	public void setA3(int a3) {
		this.a3 = a3;
	}

	public int getA4() {
		return a4;
	}

	public void setA4(int a4) {
		this.a4 = a4;
	}

	public int getA5() {
		return a5;
	}

	public void setA5(int a5) {
		this.a5 = a5;
	}

	public int getA6() {
		return a6;
	}

	public void setA6(int a6) {
		this.a6 = a6;
	}

	public int getA7() {
		return a7;
	}

	public void setA7(int a7) {
		this.a7 = a7;
	}

	public int getA8() {
		return a8;
	}

	public void setA8(int a8) {
		this.a8 = a8;
	}

	public int getA9() {
		return a9;
	}

	public void setA9(int a9) {
		this.a9 = a9;
	}

	public int getA10() {
		return a10;
	}

	public void setA10(int a10) {
		this.a10 = a10;
	}

	public int getA11() {
		return a11;
	}

	public void setA11(int a11) {
		this.a11 = a11;
	}

	public int getA18() {
		return a18;
	}

	public void setA18(int a18) {
		this.a18 = a18;
	}

	public int getA19() {
		return a19;
	}

	public void setA19(int a19) {
		this.a19 = a19;
	}

	public int getA20() {
		return a20;
	}

	public void setA20(int a20) {
		this.a20 = a20;
	}

	public int getA21() {
		return a21;
	}

	public void setA21(int a21) {
		this.a21 = a21;
	}

	public int getA22() {
		return a22;
	}

	public void setA22(int a22) {
		this.a22 = a22;
	}

	public int getA23() {
		return a23;
	}

	public void setA23(int a23) {
		this.a23 = a23;
	}

	public int getA24() {
		return a24;
	}

	public void setA24(int a24) {
		this.a24 = a24;
	}

	public int getA25() {
		return a25;
	}

	public void setA25(int a25) {
		this.a25 = a25;
	}

	public int getA26() {
		return a26;
	}

	public void setA26(int a26) {
		this.a26 = a26;
	}

	public int getA27() {
		return a27;
	}

	public void setA27(int a27) {
		this.a27 = a27;
	}

	public int getS0() {
		return s0;
	}

	public void setS0(int s0) {
		this.s0 = s0;
	}

	public int getS1() {
		return s1;
	}

	public void setS1(int s1) {
		this.s1 = s1;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}

	public int getS3() {
		return s3;
	}

	public void setS3(int s3) {
		this.s3 = s3;
	}

	public int getS4() {
		return s4;
	}

	public void setS4(int s4) {
		this.s4 = s4;
	}

	public int getS5() {
		return s5;
	}

	public void setS5(int s5) {
		this.s5 = s5;
	}

	public int getS6() {
		return s6;
	}

	public void setS6(int s6) {
		this.s6 = s6;
	}

	public int getS7() {
		return s7;
	}

	public void setS7(int s7) {
		this.s7 = s7;
	}

	public int getS9() {
		return s9;
	}

	public void setS9(int s9) {
		this.s9 = s9;
	}

	public int getS10() {
		return s10;
	}

	public void setS10(int s10) {
		this.s10 = s10;
	}

	public int getS11() {
		return s11;
	}

	public void setS11(int s11) {
		this.s11 = s11;
	}

	public int getS12() {
		return s12;
	}

	public void setS12(int s12) {
		this.s12 = s12;
	}

	public int getA12() {
		return a12;
	}

	public void setA12(int a12) {
		this.a12 = a12;
	}

	public int getA13() {
		return a13;
	}

	public void setA13(int a13) {
		this.a13 = a13;
	}

	public int getA14() {
		return a14;
	}

	public void setA14(int a14) {
		this.a14 = a14;
	}


	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDriverno() {
		return driverno;
	}

	public void setDriverno(String driverno) {
		this.driverno = driverno;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public String getPsam() {
		return psam;
	}

	public void setPsam(String psam) {
		this.psam = psam;
	}

	public int getTaximeterno() {
		return taximeterno;
	}

	public void setTaximeterno(int taximeterno) {
		this.taximeterno = taximeterno;
	}

	public int getBlacklistver() {
		return blacklistver;
	}

	public void setBlacklistver(int blacklistver) {
		this.blacklistver = blacklistver;
	}

	public int getSoftever() {
		return softever;
	}

	public void setSoftever(int softever) {
		this.softever = softever;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUsinglen() {
		return usinglen;
	}

	public void setUsinglen(int usinglen) {
		this.usinglen = usinglen;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getTers() {
		return ters;
	}

	public void setTers(String ters) {
		this.ters = ters;
	}

	public int getS18() {
		return s18;
	}

	public void setS18(int s18) {
		this.s18 = s18;
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

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public String getCompletiontime() {
		return completiontime;
	}

	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}

	public int getTotalpacket() {
		return totalpacket;
	}

	public void setTotalpacket(int totalpacket) {
		this.totalpacket = totalpacket;
	}

	public int getA15() {
		return a15;
	}

	public void setA15(int a15) {
		this.a15 = a15;
	}

	public int getA16() {
		return a16;
	}

	public void setA16(int a16) {
		this.a16 = a16;
	}

	public int getA17() {
		return a17;
	}

	public void setA17(int a17) {
		this.a17 = a17;
	}

	public String getMediaData() {
		return mediaData;
	}

	public void setMediaData(String mediaData) {
		this.mediaData = mediaData;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getMcs() {
		return mcs;
	}

	public void setMcs(Integer mcs) {
		this.mcs = mcs;
	}

	public Integer getCardnum() {
		return cardnum;
	}

	public void setCardnum(Integer cardnum) {
		this.cardnum = cardnum;
	}

	public Integer getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(Integer totalnumber) {
		this.totalnumber = totalnumber;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getSignintime() {
		return signintime;
	}

	public void setSignintime(String signintime) {
		this.signintime = signintime;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getDbmileage() {
		return dbmileage;
	}

	public void setDbmileage(String dbmileage) {
		this.dbmileage = dbmileage;
	}

	public String getDbyymileage() {
		return dbyymileage;
	}

	public void setDbyymileage(String dbyymileage) {
		this.dbyymileage = dbyymileage;
	}

	public String getJstmie() {
		return jstmie;
	}

	public void setJstmie(String jstmie) {
		this.jstmie = jstmie;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getCardamount() {
		return cardamount;
	}

	public void setCardamount(String cardamount) {
		this.cardamount = cardamount;
	}

	public String getBjmileage() {
		return bjmileage;
	}

	public void setBjmileage(String bjmileage) {
		this.bjmileage = bjmileage;
	}

	public String getTotalmileage() {
		return totalmileage;
	}

	public void setTotalmileage(String totalmileage) {
		this.totalmileage = totalmileage;
	}

	public String getTotalyymileage() {
		return totalyymileage;
	}

	public void setTotalyymileage(String totalyymileage) {
		this.totalyymileage = totalyymileage;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalwaittime() {
		return totalwaittime;
	}

	public void setTotalwaittime(String totalwaittime) {
		this.totalwaittime = totalwaittime;
	}

	public Integer getVehicletrips() {
		return vehicletrips;
	}

	public void setVehicletrips(Integer vehicletrips) {
		this.vehicletrips = vehicletrips;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
