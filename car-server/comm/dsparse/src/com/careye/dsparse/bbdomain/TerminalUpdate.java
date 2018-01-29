/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

/**
 * @项目名称：dsparse
 * @类名称：TerminalUpdate
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-6-1 上午10:35:32
 * @修改人：zhangrong
 * @修改时间：2015-6-1 上午10:35:32
 * @修改备注：
 * @version 1.0
 */
public class TerminalUpdate {
	
	/**升级类型0：终端，12：道路运输证 IC 卡读卡器，52：北斗卫星定位模块*/
	private int type;

	/**制造商ID*/
	private int oem;
	
	/**版本号长度*/
	private int versionlen;
	
	/**版本号*/
	private String version;
	
	/**升级数据包长度*/
	private int datalen;
	
	/**升级数据包*/
	private String data;
	
	/**设备类型*/
	private int devicetype;
	
	/**厂商标识*/
	private String dversion;
	
	/**硬件版本号*/
	private String sversion;
	
	/**软件版本号*/
	private int result;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public int getOem() {
		return oem;
	}

	public void setOem(int oem) {
		this.oem = oem;
	}

	public int getVersionlen() {
		return versionlen;
	}

	public void setVersionlen(int versionlen) {
		this.versionlen = versionlen;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getDatalen() {
		return datalen;
	}

	public void setDatalen(int datalen) {
		this.datalen = datalen;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public String getDversion() {
		return dversion;
	}

	public void setDversion(String dversion) {
		this.dversion = dversion;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
	
}
