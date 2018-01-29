/**
* Description: car-eye车辆管理平台
* 文件名：Suburb.java
* 版本信息：1.0
* 日期：2015-4-14
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：OCS
 * @类名称：Suburb
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-14 下午05:40:12
 * @修改人：Yuqk
 * @修改时间：2015-4-14 下午05:40:12
 * @修改备注：
 * @version 1.0
 */
public class Suburb {
	/**
	 * 主键
	 */
private Integer id;
/**
 * 父级郊区主键
 */
private Integer pid;
/**
 * 等级（1为父级，2为子级）
 */
private Integer slevel;
/**
 * 郊区名称
 */
private String suburbname;
/**
 * 电话
 */
private String phone;
/**
 * 创建时间
 */
private String createtime;

/*getter setter
 * 
 */
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public Integer getSlevel() {
	return slevel;
}
public void setSlevel(Integer slevel) {
	this.slevel = slevel;
}
public String getSuburbname() {
	return suburbname;
}
public void setSuburbname(String suburbname) {
	this.suburbname = suburbname;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
}

}
