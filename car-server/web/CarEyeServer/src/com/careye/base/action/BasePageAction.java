/**    
 * Description: car-eye车辆管理平台    
 * 文件名：BasePageAction.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.base.action;

/**    
 *     
 * car-eye车辆管理平台业务处理    
 * 类名称：BasePageAction    
 * 类描述：分页基础类
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class BasePageAction extends BaseAction {

	private static final long serialVersionUID = -4282798875090903923L;

	public Page<?> pageObject;

	private int currentPage = 1;/**默认第一页*/

	private int everyPage = 10; /**默认显示10条*/

	private int totalPage = 0; /**总页数*/

	/**
	 * Extjs分页传递过来的参数
	 */
	private String _dc;

	private String query;

	private Integer start;

	private Integer limit;

	private Integer page;

	private String sort;

	public int getCurrentPage() {
		try {
			currentPage = Integer.parseInt(String.valueOf(currentPage));
			if (totalPage != 0) {
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
			}
		} catch (Exception e) {
			currentPage = 1;
			LOG.debug("当前页传入非法字符！", e);
		}
		return currentPage;
	}

	public Page<?> getPageObject() {
		return pageObject;
	}


	public void setPageObject(Page<?> pageObject) {
		this.pageObject = pageObject;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String get_dc() {
		return _dc;
	}

	public void set_dc(String dc) {
		_dc = dc;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		if(start == 0){
			return 1;
		}else{
			return page;
		}
	}

	public void setPage(Integer page) {
		this.page = page;
	}


	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}



}
