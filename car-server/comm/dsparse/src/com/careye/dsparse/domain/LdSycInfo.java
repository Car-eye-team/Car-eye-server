/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：LdSycInfo    
 * 类描述：蓝度商用车实体    
 * 创建人：zr    
 * 创建时间：2015-7-23 下午07:28:31    
 * 修改人：zr    
 * 修改时间：2015-7-23 下午07:28:31    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LdSycInfo extends BaseInfo{
	
	
	/**产品序号*/
	private String pserialnumber;
	
	/**系统编号*/
	private Integer  systemnumber;
	
	/**信息类型**/
	private Integer infotype;
	
	/**数量*/
	private Integer count;
	
	/**执行结果*/
	private Integer result;
	
	/**电控系统数量*/
	private Integer number;
	
	/**发动机制造商ID*/
	private Integer engineid;
	
	/**ECU 制造商ID*/
	private Integer ecuid;
	
	/**车辆制造商ID*/
	private Integer vehicleid;
	
	/**通讯协议ID*/
	private Integer protocolid;
	
	/**库文件版本信息*/
	private String versioninfo;
	
	/**传输标识*/
	private Integer tranident;
	
	/**点火上传标识*/
	private Integer ignitionuploadident; 
	
	/**熄火上传标识*/
	private Integer stalluploadident;
	
	/**点火后上传时间间隔*/
	private Integer ignitionuploadinterval;
	
	/**熄火后上传时间间隔*/
	private Integer stalluploadinterval;
	
	/**串口波特率*/
	private Integer baudrate;
	
	/**发动机排量*/
	private Integer enginedis;
	
	/**燃油修正系数1*/
	private Integer coefficient1;
	
	/**燃油修正系数2*/
	private Integer coefficient2;
	
	/**里程*/
	private Integer mileage;
	
	/**油耗*/
	private Integer oilwear;
	
	private String bit;
	
	/**数据流掩码值1*/
	private String value1;
	
	/**数据流掩码值2*/
	private String value2;
	
	/**取得检测数据时间戮*/
	private String timestamp;
	
	
	private Map<Integer, String> map = new HashMap<Integer, String>(); 
	
	private List<LdSycInfoItems> items = new ArrayList<LdSycInfoItems>();



	public String getPserialnumber() {
		return pserialnumber;
	}

	public void setPserialnumber(String pserialnumber) {
		this.pserialnumber = pserialnumber;
	}


	public Integer getSystemnumber() {
		return systemnumber;
	}

	public void setSystemnumber(Integer systemnumber) {
		this.systemnumber = systemnumber;
	}


	public Integer getInfotype() {
		return infotype;
	}

	public void setInfotype(Integer infotype) {
		this.infotype = infotype;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getEngineid() {
		return engineid;
	}

	public void setEngineid(Integer engineid) {
		this.engineid = engineid;
	}

	public Integer getEcuid() {
		return ecuid;
	}

	public void setEcuid(Integer ecuid) {
		this.ecuid = ecuid;
	}

	public Integer getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(Integer vehicleid) {
		this.vehicleid = vehicleid;
	}

	public Integer getProtocolid() {
		return protocolid;
	}

	public void setProtocolid(Integer protocolid) {
		this.protocolid = protocolid;
	}

	public String getVersioninfo() {
		return versioninfo;
	}

	public void setVersioninfo(String versioninfo) {
		this.versioninfo = versioninfo;
	}

	public Integer getTranident() {
		return tranident;
	}

	public void setTranident(Integer tranident) {
		this.tranident = tranident;
	}

	public Integer getIgnitionuploadident() {
		return ignitionuploadident;
	}

	public void setIgnitionuploadident(Integer ignitionuploadident) {
		this.ignitionuploadident = ignitionuploadident;
	}

	public Integer getStalluploadident() {
		return stalluploadident;
	}

	public void setStalluploadident(Integer stalluploadident) {
		this.stalluploadident = stalluploadident;
	}

	public Integer getIgnitionuploadinterval() {
		return ignitionuploadinterval;
	}

	public void setIgnitionuploadinterval(Integer ignitionuploadinterval) {
		this.ignitionuploadinterval = ignitionuploadinterval;
	}

	public Integer getStalluploadinterval() {
		return stalluploadinterval;
	}

	public void setStalluploadinterval(Integer stalluploadinterval) {
		this.stalluploadinterval = stalluploadinterval;
	}

	public String getBit() {
		return bit;
	}

	public void setBit(String bit) {
		this.bit = bit;
	}

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	public List<LdSycInfoItems> getItems() {
		return items;
	}

	public void setItems(List<LdSycInfoItems> items) {
		this.items = items;
	}

	public Integer getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(Integer baudrate) {
		this.baudrate = baudrate;
	}

	public Integer getEnginedis() {
		return enginedis;
	}

	public void setEnginedis(Integer enginedis) {
		this.enginedis = enginedis;
	}

	public Integer getCoefficient1() {
		return coefficient1;
	}

	public void setCoefficient1(Integer coefficient1) {
		this.coefficient1 = coefficient1;
	}

	public Integer getCoefficient2() {
		return coefficient2;
	}

	public void setCoefficient2(Integer coefficient2) {
		this.coefficient2 = coefficient2;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Integer getOilwear() {
		return oilwear;
	}

	public void setOilwear(Integer oilwear) {
		this.oilwear = oilwear;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
