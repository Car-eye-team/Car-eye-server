/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsparse.bbdomain;

import java.util.ArrayList;
import java.util.List;

import com.careye.dsparse.constant.BaseInfo;

/**    
 *     
 * 项目名称：dsparse    
 * 类名称：TaxiInfo    
 * 类描述：    
 * 创建人：Administrator    
 * 创建时间：2016-5-4 上午11:24:37    
 * 修改人：Administrator    
 * 修改时间：2016-5-4 上午11:24:37    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class TaxiInfo extends BaseInfo{
	
	/**组ID*/
	private int groupid;
	
	/**车辆ID*/
	private int carid;
	
	/**组名称*/
	private String groupname;
	
	/**车牌号*/
	private String carnumber;
	
	/**操作结果*/
	private int result;
	
	private int id;
	
	/**类型*/
	private int type;
	
	/**开始时间*/
	private String stime;
	
	/**结束时间*/
	private String etime;
	
	/**文件名*/
	private String filename;
	
	/**播放开始时间*/
	private int splaysec;
	
	/**播放结束时间*/
	private int eplaysec;
	
	/**总数*/
	private int count;
	
	private List<TaxiInfoItems> items = new ArrayList<TaxiInfoItems>();
	
	
	public List<TaxiInfoItems> getItems() {
		return items;
	}

	public void setItems(List<TaxiInfoItems> items) {
		this.items = items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getSplaysec() {
		return splaysec;
	}

	public void setSplaysec(int splaysec) {
		this.splaysec = splaysec;
	}

	public int getEplaysec() {
		return eplaysec;
	}

	public void setEplaysec(int eplaysec) {
		this.eplaysec = eplaysec;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	

}
