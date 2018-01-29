/**
* Description: car-eye车辆管理平台
* 文件名：PowerSet.java
* 版本信息：1.0
* 日期：2014-9-18
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.sysset.domain;

/**
 * @项目名称：car-eye
 * @类名称：PageSet
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午09:30:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午09:30:07
 * @修改备注：
 * @version 1.0
 */
public class PageSet {

	/**id**/
	private Integer id;
	
	/**userid**/
	private Integer userid;
	
	/**左侧展开关闭状态	1 展开 2 关闭**/
	private Integer leftpage;
	
	/**底部展开关闭状态	1 展开 2 关闭**/
	private Integer bottompage;
	
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLeftpage() {
		return leftpage;
	}

	public void setLeftpage(Integer leftpage) {
		this.leftpage = leftpage;
	}

	public Integer getBottompage() {
		return bottompage;
	}

	public void setBottompage(Integer bottompage) {
		this.bottompage = bottompage;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}





