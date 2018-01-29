/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：StoraMulMediaRetrieval    
 * 类描述：存储多媒体数据检索    
 * 创建人：zr    
 * 创建时间：2015-6-6 下午02:29:05    
 * 修改人：zr    
 * 修改时间：2015-6-6 下午02:29:05    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StoraMulMediaRetrieval extends BaseInfo{
	
	/** 多媒体类型 **/
	private  int type;
	
	/** 通道 ID **/
	private  int  id;
	
	/** 事件项编码  **/
	private  int  fmt;
	
	/**  起始时间  **/
	private  String starttime;
	
	/** 结束时间  **/
	private  String endtime;
	
	/**删除标志*/
	private int flg;
	
	/**多媒体ID*/
	private int mediaid;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFmt() {
		return fmt;
	}

	public void setFmt(int fmt) {
		this.fmt = fmt;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getFlg() {
		return flg;
	}

	public void setFlg(int flg) {
		this.flg = flg;
	}

	public int getMediaid() {
		return mediaid;
	}

	public void setMediaid(int mediaid) {
		this.mediaid = mediaid;
	}
	
	
}
