/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：HttxInfo    
 * 类描述：深圳市航天无线通信技术有限公司213GD协议实体    
 * 创建人：Administrator    
 * 创建时间：2015-12-23 下午06:02:14    
 * 修改人：Administrator    
 * 修改时间：2015-12-23 下午06:02:14    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HttxInfo extends BaseInfo{
	
	private String value;
	
	private int size;
	
	private int typecount;
	
	private int seq;
	
	private int dstPos;
	
	private int respcount;
	
	private int number;
	
	private int failcount;	
	
	private int successcount;	
	
	/**GPS 数据标志*/
	private int flag;
	
	/**软件版本号*/
	private String softwarever;
	
	/**硬件版本号*/
	private String hardwardver;
	
	private HttxStatData httxStatData;
	
	/**GPS 数组个数*/
	private int gpscount;
	
	/**RPM个数*/
	private int rpmcount;
	
	private int count;
	
	private int interval;
	
	private String utctime;
	
	/**时间*/
	private String time;
	
	/**纬度*/
	private Integer lat;
	
	/**经度*/
	private Integer lng;
	
	/**速度*/
	private Integer speed;
	
	/**方向*/
	private Integer direction;
	
	/**定位标志*/
	private Integer gpsflag;
	
	/**报警编号*/
	private Integer alarmnum;
	
	/**升级ID*/
	private Integer updateid;
	
	/**软件编号*/
	private String softnum;
	
	/**总包数*/
	private Integer totolpackage;
	
	/**Crc个数*/
	private Integer crccount;
	
	/**升级确认*/
	private Integer updateconfirm;
	
	/**包标志*/
	private Integer packageflag;
	
	/**包号*/
	private Integer packagenum;
	
	/**包长度*/
	private Integer packagelen;
	
	/**包内容*/
	private String packagecontent;
	
	
	private List<HttxInfoItems> gpsitmes = new ArrayList<HttxInfoItems>();
	
	private List<HttxInfoItems> rpmitmes = new ArrayList<HttxInfoItems>();
	
	private List<HttxInfoItems> items = new ArrayList<HttxInfoItems>();
	
	private List<HttxInfoItems> typeitems = new ArrayList<HttxInfoItems>();
	
	private List<HttxInfoItems> tagitems = new ArrayList<HttxInfoItems>();
	
	

	public int getRespcount() {
		return respcount;
	}

	public void setRespcount(int respcount) {
		this.respcount = respcount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFailcount() {
		return failcount;
	}

	public void setFailcount(int failcount) {
		this.failcount = failcount;
	}

	public int getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(int successcount) {
		this.successcount = successcount;
	}

	public List<HttxInfoItems> getTagitems() {
		return tagitems;
	}

	public void setTagitems(List<HttxInfoItems> tagitems) {
		this.tagitems = tagitems;
	}

	public Integer getPackageflag() {
		return packageflag;
	}

	public void setPackageflag(Integer packageflag) {
		this.packageflag = packageflag;
	}

	public Integer getPackagenum() {
		return packagenum;
	}

	public void setPackagenum(Integer packagenum) {
		this.packagenum = packagenum;
	}

	public Integer getPackagelen() {
		return packagelen;
	}

	public void setPackagelen(Integer packagelen) {
		this.packagelen = packagelen;
	}

	public String getPackagecontent() {
		return packagecontent;
	}

	public void setPackagecontent(String packagecontent) {
		this.packagecontent = packagecontent;
	}

	public Integer getUpdateconfirm() {
		return updateconfirm;
	}

	public void setUpdateconfirm(Integer updateconfirm) {
		this.updateconfirm = updateconfirm;
	}

	public Integer getUpdateid() {
		return updateid;
	}

	public void setUpdateid(Integer updateid) {
		this.updateid = updateid;
	}

	public String getSoftnum() {
		return softnum;
	}

	public void setSoftnum(String softnum) {
		this.softnum = softnum;
	}

	public Integer getTotolpackage() {
		return totolpackage;
	}

	public void setTotolpackage(Integer totolpackage) {
		this.totolpackage = totolpackage;
	}

	public Integer getCrccount() {
		return crccount;
	}

	public void setCrccount(Integer crccount) {
		this.crccount = crccount;
	}

	public Integer getAlarmnum() {
		return alarmnum;
	}

	public void setAlarmnum(Integer alarmnum) {
		this.alarmnum = alarmnum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<HttxInfoItems> getItems() {
		return items;
	}

	public void setItems(List<HttxInfoItems> items) {
		this.items = items;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}



	public int getRpmcount() {
		return rpmcount;
	}

	public void setRpmcount(int rpmcount) {
		this.rpmcount = rpmcount;
	}


	public List<HttxInfoItems> getRpmitmes() {
		return rpmitmes;
	}

	public void setRpmitmes(List<HttxInfoItems> rpmitmes) {
		this.rpmitmes = rpmitmes;
	}

	public int getGpscount() {
		return gpscount;
	}

	public void setGpscount(int gpscount) {
		this.gpscount = gpscount;
	}

	public List<HttxInfoItems> getGpsitmes() {
		return gpsitmes;
	}

	public void setGpsitmes(List<HttxInfoItems> gpsitmes) {
		this.gpsitmes = gpsitmes;
	}

	public int getDstPos() {
		return dstPos;
	}

	public void setDstPos(int dstPos) {
		this.dstPos = dstPos;
	}

	public String getSoftwarever() {
		return softwarever;
	}

	public void setSoftwarever(String softwarever) {
		this.softwarever = softwarever;
	}

	public HttxStatData getHttxStatData() {
		return httxStatData;
	}

	public void setHttxStatData(HttxStatData httxStatData) {
		this.httxStatData = httxStatData;
	}

	public String getHardwardver() {
		return hardwardver;
	}

	public void setHardwardver(String hardwardver) {
		this.hardwardver = hardwardver;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getUtctime() {
		return utctime;
	}

	public void setUtctime(String utctime) {
		this.utctime = utctime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getLat() {
		return lat;
	}

	public void setLat(Integer lat) {
		this.lat = lat;
	}

	public Integer getLng() {
		return lng;
	}

	public void setLng(Integer lng) {
		this.lng = lng;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(Integer gpsflag) {
		this.gpsflag = gpsflag;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getTypecount() {
		return typecount;
	}

	public void setTypecount(int typecount) {
		this.typecount = typecount;
	}

	public List<HttxInfoItems> getTypeitems() {
		return typeitems;
	}

	public void setTypeitems(List<HttxInfoItems> typeitems) {
		this.typeitems = typeitems;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
