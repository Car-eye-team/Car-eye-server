/**    
 * Description: car-eye车辆管理平台    
 * 文件名：Page.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.action;

import java.util.Collections;
import java.util.List;

/**    
 *     
 * car-eye车辆管理平台业务处理    
 * 类名称：Page    
 * 类描述：分页实体类 
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class Page<T> {

	/**每页显示数量(everyPage)*/
	private int everyPage = 10;

	/**当前页(currentPage)*/
	private int currentPage = 1;

	/**总记录数*/
	private int totalCount = -1;

	/** 返回结果 */
	private List<T> result = Collections.emptyList();

	/**构造函数 */
	public Page() {
	}

	public Page(int everyPage) {
		this.everyPage = everyPage;
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
   
		if (currentPage < 1)
			this.currentPage = 1;
	}

	/**
	 * 获得每页的记录数量,默认为10.
	 */
	public int getEveryPage() {
		return everyPage;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;

		if (everyPage < 1)
			this.everyPage = 1;
	}

	/**
	 * 根据currentPage和everyPage计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getBeginIndex()
	{
		return ((currentPage - 1) * everyPage) + 1;
	}

	/**
	 * 根据everyPage与totalCount计算总页数, 默认值为-1.
	 */
	public int getTotalPages() {

		int count = -1;

		if (totalCount < 0) {
			return count;
		}

		if (totalCount % everyPage == 0)
		{
			count = totalCount / everyPage;
		}
		else
		{
			count = totalCount / everyPage + 1;
		}

		return count;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext()
	{
		return (currentPage + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {

		if (isHasNext())
		{
			return currentPage + 1;
		}
		else if (currentPage >= getTotalPages())
		{
			return getTotalPages();
		}
		else
		{
			return currentPage;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre()
	{
		return currentPage == 1 ? false : true;
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {

		if (isHasPre())
		{
			return currentPage - 1;
		}
		else
		{
			return currentPage;
		}
	}


}
