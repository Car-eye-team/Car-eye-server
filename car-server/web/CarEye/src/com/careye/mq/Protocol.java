/**
* Description: 多森商用车平台
* 文件名：Protocol.java
* 版本信息：1.0
* 日期：2013-7-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.mq;

import java.util.List;

/**
 * @项目名称：car-eye
 * @类名称：Protocol
 * @类描述：通讯平台消息解析实体
 * @创建人：zr
 * @创建时间：2013-7-22 下午02:53:39
 * @修改人：zr
 * @修改时间：2013-7-22 下午02:53:39
 * @修改备注：
 * @version 1.0
 */
public class Protocol implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /******************** 常用参数 *************************/
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

    /** 结果 */
    private int result;

    /** 附加状态字 */
    private int status;

    /** 标示某条协议的附加信息 */
    private String id;
    
    private int answer;

    /** Protocol加入缓存的时间  */
    private String localTime;

    /********************* 注册参数 ****************************/
    /** 物流园区对应 */
    private int pro;
    
    /** 省份编号 */
    private int province;

    /** 城市编号 */
    private int city;
    
    /** 省份编号 */
    private int prvnSrc;

    /** 城市编号 */
    private int citySrc;

    /** 终端制造商编码 */
    private String oem;

    /** 终端型号 ***/
    private String type;

    /** 终端ID */
    private String mid;

    /** 车牌颜色 */
    private int plateColor;

    /** 车牌号码 */
    private String plateCode;
    
    private int flag;
    
    private String gpstime;

    /******************** 报警参数 *************************/

    /** 紧急报警 */
    private int a0;

    /** 超速报警 */
    private int a1;

    /** 疲劳报警 */
    private int a2;

    /** 疲劳预警 */
    private int a3;

    /** GNSS模块发生故障报警 */
    private int a4;

    /** GNSS天线未接或被剪断报警 */
    private int a5;

    /** GNSS天线短路报警 */
    private int a6;

    /** 终端主电源欠压报警 */
    private int a7;

    /** 终端主电源掉电报警 */
    private int a8;

    /** 终端LCD或显示屏报警 */
    private int a9;

    /** TTS模块故障报警 */
    private int a10;

    /** 摄像头故障报警 */
    private int a11;

    /** 12 -17 保留 */

    /** 当天累计驾驶超时报警 */
    private int a18;

    /** 超时停车报警 */
    private int a19;

    /** 进出区域报警 */
    private int a20;

    /** 进出路线报警 */
    private int a21;

    /** 路段行驶时间不足/过长报警 */
    private int a22;

    /** 路线偏离报警 */
    private int a23;

    /** 车辆VSS故障报警 */
    private int a24;

    /** 车辆油量异常报警 */
    private int a25;

    /** 车辆被盗(通过车辆防盗器) */
    private int a26;

    /** 车辆非法点火报警 */
    private int a27;

    /** 车辆非法移位报警 */
    private int a28;

    /** 车辆碰撞侧翻报警 */
    private int a29;

    /** 30 -31 保留 */
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

    /** 0:车辆油路正常:1:车辆油路断开 */
    private int s10;

    /** 0:车辆电路正常:1:车辆电路断开 */
    private int s11;

    /** 0:车门解锁；1：车门加锁 */
    private int s12;

    /******************** 车辆状态 ************************/

    /** 里程 */
    private int mileage;

    /** 油量 */
    private float oil;

    /** 行驶速度 */
    private int speedEx;

    /** 故障码 */
    private String code;

    /** OBD数据流 */
    private String data;

    /** 行程总油耗 */
    private float oilSum;

    /** 行程平均油耗 */
    private float oilAvg;
    
    /**瞬时油耗*/
    private float oilAt;

    /** 行程时间 */
    private String timeT;

    /** 行程里程 */
    private String mile;

    /** 行程总油耗 */
    private String oilT;

    /** 行程平均油耗 */
    private String oilAvgT;

    /** 行程怠时间 */
    private String linger;

    /** 最高行驶速度 */
    private String speedTop;

    /** 平均行驶速度 */
    private String speedAvg;

    private String acce;

    private String dece;
    
    private String content;
    
    private Integer gpsflag;
    
    private int demand;
    
    

    /******************** OBD ************************/
    
    /******************** 货源信息   ***************************/
    
    public int getStatus() {
        return status;
    }

    public int getPrvnSrc() {
        return prvnSrc;
    }

    public void setPrvnSrc(int prvnSrc) {
        this.prvnSrc = prvnSrc;
    }

    public int getCitySrc() {
        return citySrc;
    }

    public void setCitySrc(int citySrc) {
        this.citySrc = citySrc;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    /** 结果 */
    private int stayTag;

    /** 路段行驶时间 */
    private int staySeconds;

    /** 路段ID */
    private int stayAreaId;

    /** 区域方向 */
    private int areaHeading;

    /** 区域或线路ID */
    private int areaId;

    /** 位置类型 */
    private int areaType;

    /** 超速区域ID */
    private int speedAreaId;

    /** 区域类型 */
    private int siteType;

    private List items;

    /******************** 区域报警 ************************/

    /** 货物处理状态 */
    private int oper;

    /** 货物订单号 **/
    private String ordId;
    
    
    /******************** 采集驾驶员身份信息数据************************/
    /** 驾驶员姓名长度 **/
    private int nameLen;
    
    /** 驾驶员姓名 **/
    private String name;
    
    /** 从业资格证编码 **/
    private String QC;
    
    /** 发证机构名称长度**/
    private int orgLen;
    
    /** 发证机构名称 **/
    private String org;
    
    /************************多媒体事件信息上传*****************************/
    /**多媒体数据ID**/
    private int dataId;
    
    /**多媒体类型 0：图像；1：音频；2：视频**/
    private int mediaType;
    
    /**多媒体格式编码 0：JPEG；1：TIF；2：MP3；3：WAV；4：WMV；其他保留**/
    private int format;
    
    /**事件项编码 0：平台下发指令；1：定时动作；2：抢劫报警触发；3：碰撞侧翻报警触发；其他保留**/
    private int eventId;
    
    /**通道ID**/
    private int wayId;
    
    /**数据总包数**/
    private int packetSum;
    
    /**包ID**/
    private int packetId;
    
    /**多媒体数据包**/
    private String mediaData;

    
    
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

	public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public int getOper() {
        return oper;
    }

    public void setOper(int oper) {
        this.oper = oper;
    }

    public int getMsgid() {
        return msgid;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
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



    public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getGpstime() {
		return gpstime;
	}

	public void setGpstime(String gpstime) {
		this.gpstime = gpstime;
	}

	public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
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

    public int getA28() {
        return a28;
    }

    public void setA28(int a28) {
        this.a28 = a28;
    }

    public int getA29() {
        return a29;
    }

    public void setA29(int a29) {
        this.a29 = a29;
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getOil() {
        return oil;
    }

    public void setOil(float oil) {
        this.oil = oil;
    }

    public int getSpeedEx() {
        return speedEx;
    }

    public void setSpeedEx(int speedEx) {
        this.speedEx = speedEx;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getOilSum() {
        return oilSum;
    }

    public void setOilSum(float oilSum) {
        this.oilSum = oilSum;
    }

    public float getOilAvg() {
        return oilAvg;
    }

    public void setOilAvg(float oilAvg) {
        this.oilAvg = oilAvg;
    }

    public String getTimeT() {
        return timeT;
    }

    public void setTimeT(String timeT) {
        this.timeT = timeT;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getOilT() {
        return oilT;
    }

    public void setOilT(String oilT) {
        this.oilT = oilT;
    }

    public String getOilAvgT() {
        return oilAvgT;
    }

    public void setOilAvgT(String oilAvgT) {
        this.oilAvgT = oilAvgT;
    }

    public String getLinger() {
        return linger;
    }

    public void setLinger(String linger) {
        this.linger = linger;
    }

    public String getSpeedTop() {
        return speedTop;
    }

    public void setSpeedTop(String speedTop) {
        this.speedTop = speedTop;
    }

    public String getSpeedAvg() {
        return speedAvg;
    }

    public void setSpeedAvg(String speedAvg) {
        this.speedAvg = speedAvg;
    }

    public String getAcce() {
        return acce;
    }

    public void setAcce(String acce) {
        this.acce = acce;
    }

    public String getDece() {
        return dece;
    }

    public void setDece(String dece) {
        this.dece = dece;
    }


    public int getStayTag() {
		return stayTag;
	}

	public void setStayTag(int stayTag) {
		this.stayTag = stayTag;
	}

	public int getStaySeconds() {
		return staySeconds;
	}

	public void setStaySeconds(int staySeconds) {
		this.staySeconds = staySeconds;
	}

	public int getStayAreaId() {
		return stayAreaId;
	}

	public void setStayAreaId(int stayAreaId) {
		this.stayAreaId = stayAreaId;
	}

	public int getAreaHeading() {
		return areaHeading;
	}

	public void setAreaHeading(int areaHeading) {
		this.areaHeading = areaHeading;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getAreaType() {
		return areaType;
	}

	public void setAreaType(int areaType) {
		this.areaType = areaType;
	}

	public int getSpeedAreaId() {
		return speedAreaId;
	}

	public void setSpeedAreaId(int speedAreaId) {
		this.speedAreaId = speedAreaId;
	}

	public int getSiteType() {
		return siteType;
	}

	public void setSiteType(int siteType) {
		this.siteType = siteType;
	}

	public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }


    public int getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(int plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public int getPro() {
        return pro;
    }

    public void setPro(int pro) {
        this.pro = pro;
    }

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Integer getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(Integer gpsflag) {
		this.gpsflag = gpsflag;
	}

	public int getNameLen() {
		return nameLen;
	}

	public void setNameLen(int nameLen) {
		this.nameLen = nameLen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQC() {
		return QC;
	}

	public void setQC(String qC) {
		QC = qC;
	}

	public int getOrgLen() {
		return orgLen;
	}

	public void setOrgLen(int orgLen) {
		this.orgLen = orgLen;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public float getOilAt() {
		return oilAt;
	}

	public void setOilAt(float oilAt) {
		this.oilAt = oilAt;
	}
	
	
    
}
