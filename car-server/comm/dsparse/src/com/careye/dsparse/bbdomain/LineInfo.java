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
 * 类名称：LineInfo    
 * 类描述：线路信息实体    
 * 创建人：zr    
 * 创建时间：2015-6-5 下午02:41:11    
 * 修改人：zr    
 * 修改时间：2015-6-5 下午02:41:11    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class LineInfo extends BaseInfo{

	/**路线ID*/
	private int rid;
	
	/**起始时间*/
	private String sdate;
	
	/**结束时间*/
	private String edate;
	
	/**路线总拐点数*/
	private int count;
	
	/**拐点项*/
	private  List<LineInfoItems> items = new ArrayList<LineInfoItems>();
	
	/**1：根据时间*/
	private int rattr0;
	
	/**保留*/
	private int rattr1;
	
	/**1：进路线报警给驾驶员*/
	private int rattr2;
	
	/**1：进路线报警给平台*/
	private int rattr3;
	
	/**1：出路线报警给驾驶员*/
	private int rattr4;
	
	/**1：出路线报警给平台*/
	private int rattr5;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<LineInfoItems> getItems() {
		return items;
	}

	public void setItems(List<LineInfoItems> items) {
		this.items = items;
	}

	public int getRattr0() {
		return rattr0;
	}

	public void setRattr0(int rattr0) {
		this.rattr0 = rattr0;
	}

	public int getRattr1() {
		return rattr1;
	}

	public void setRattr1(int rattr1) {
		this.rattr1 = rattr1;
	}

	public int getRattr2() {
		return rattr2;
	}

	public void setRattr2(int rattr2) {
		this.rattr2 = rattr2;
	}

	public int getRattr3() {
		return rattr3;
	}

	public void setRattr3(int rattr3) {
		this.rattr3 = rattr3;
	}

	public int getRattr4() {
		return rattr4;
	}

	public void setRattr4(int rattr4) {
		this.rattr4 = rattr4;
	}

	public int getRattr5() {
		return rattr5;
	}

	public void setRattr5(int rattr5) {
		this.rattr5 = rattr5;
	}
	
	
}
