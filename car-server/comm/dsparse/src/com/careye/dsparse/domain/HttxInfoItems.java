/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.domain;


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
public class HttxInfoItems{
	
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
	
	/**发动机转速*/
	private Integer rpm;
	
	/**x轴加速度*/
	private Integer x;
	
	/**y轴加速度*/
	private Integer y;
	
	/**z轴加速度*/
	private Integer z;
	
	private Integer pid;
	
	/**新警情标志*/
	private Integer flag;
	
	/**警情类型*/
	private Integer type;
	
	private String value;
	
	private String threshold;
	
	private String faulttype;
	
	/**指令序号*/
	private Integer seq;
	
	/**数据包个数*/
	private Integer count;
	
	
	private Integer tag;
	
	private Integer length;

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

	public Integer getRpm() {
		return rpm;
	}

	public void setRpm(Integer rpm) {
		this.rpm = rpm;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getFaulttype() {
		return faulttype;
	}

	public void setFaulttype(String faulttype) {
		this.faulttype = faulttype;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	

}
