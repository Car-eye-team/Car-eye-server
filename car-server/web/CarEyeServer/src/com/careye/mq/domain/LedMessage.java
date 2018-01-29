/**
* Description: car-eye车辆管理平台
* 文件名：LedMessage.java
* 版本信息：1.0
* 日期：2015-11-4
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mq.domain;

/**
 * @项目名称：DSTAXISERVER
 * @类名称：LedMessage
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-11-4 上午09:51:12
 * @修改人：wuyongde
 * @修改时间：2015-11-4 上午09:51:12
 * @修改备注：
 * @version 1.0
 */
public class LedMessage {

	private Integer id;
	private Integer carid;
	private Integer seq;
	private Integer type1;
	private Integer type2;
	private Integer type3;
	private Integer type4;
	/**文本信息**/
	private String textmessage;
	/** 创建时间 **/
	private String createtime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarid() {
		return carid;
	}
	public void setCarid(Integer carid) {
		this.carid = carid;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	public Integer getType3() {
		return type3;
	}
	public void setType3(Integer type3) {
		this.type3 = type3;
	}
	public Integer getType4() {
		return type4;
	}
	public void setType4(Integer type4) {
		this.type4 = type4;
	}
	public String getTextmessage() {
		return textmessage;
	}
	public void setTextmessage(String textmessage) {
		this.textmessage = textmessage;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
