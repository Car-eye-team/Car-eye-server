/**
* Description: car-eye车辆管理平台
* 文件名：Entry.java
* 版本信息：1.0
* 日期：2013-8-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.domain;

/**
 * @项目名称：car-eye
 * @类名称：Entry
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-8-13 上午09:23:47
 * @修改人：liliang
 * @修改时间：2013-8-13 上午09:23:47
 * @修改备注：
 * @version 1.0
 */
public class Entry {
	
	private String entryKey;
	private	String entryValue;
	
	public Entry(String entryKey, String entryValue) {
		super();
		this.entryKey = entryKey;
		this.entryValue = entryValue;
	}
	
	public Entry() {
		
	}
	
	public String getEntryKey() {
		return entryKey;
	}
	public String getEntryValue() {
		return entryValue;
	}

}
