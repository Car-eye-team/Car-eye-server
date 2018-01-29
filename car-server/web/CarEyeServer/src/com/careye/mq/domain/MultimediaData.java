/**    
 * Description: 多森软件物联网车联网平台    
 * 文件名：MultimediaData.java   
 * 版本信息：    
 * 日期：2015-10-27  
 * Copyright car-eye车辆管理平台 Copyright (c) 2015     
 * 版权所有    
 *    
 */
package com.careye.mq.domain;

/**    
 *     
 * 项目名称：DSTAXISERVER    
 * 类名称：MultimediaData    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2015-10-27 下午02:57:38    
 * 修改人：Administrator    
 * 修改时间：2015-10-27 下午02:57:38    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class MultimediaData {
	
	private String terminal;
	
	private int dataId;
	
	/** 经度 */
	private int lng;

	/** 纬度 */
	private int lat;

	/** 速度 */
	private String speed;

	/** 海拔 */
	private String altitude;

	/**方向*/
	private String direction;
	
	/**时间*/
	private String time;
	
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
	
	/**多媒体数据包*/
	private String mediaData;
	
	/**媒体数据缓存*/
	private String[] mediaDataArray;
	
	/**加入时间*/
	private String dateTime;
	
	/***
	 * 次数
	 */
	private int count;

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
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

	public String getMediaData() {
		return mediaData;
	}

	public void setMediaData(String mediaData) {
		this.mediaData = mediaData;
	}

	public String[] getMediaDataArray() {
		return mediaDataArray;
	}

	public void setMediaDataArray(String[] mediaDataArray) {
		this.mediaDataArray = mediaDataArray;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
