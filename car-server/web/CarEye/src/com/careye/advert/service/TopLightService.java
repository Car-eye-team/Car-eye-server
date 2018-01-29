/**
 * 
 */
package com.careye.advert.service;

import java.util.List;
import java.util.Map;

import com.careye.advert.domain.AdvertCon;
import com.careye.advert.domain.AdvertPos;
import com.careye.advert.domain.AdvertType;
import com.careye.advert.domain.AdvertVer;

/**
 * @author Administrator
 *
 */
public interface TopLightService {
	
	
	
	
	/**
	 * getPositionList   位置下拉列表
	 */
	public List<AdvertVer> getPositionList();
	/**
	 * getTypeList   类型下拉列表
	 */
	public List<AdvertType> getTypeList();
	/**
	 * getVersionList  版本下拉列表
	 */
	public List<AdvertVer> getVersionList();
	/**
	 * 获取已存在的广告序号   
	 */
	public int selconnumber(int pid);
	/**
	 * 获取广告类型id
	 */
	public int selTypeid(String typename);
	
	
	
	/**
	 * getAdvertVerList
	 * 广告版本分页查询列表
	 */
	public Map getAdvertVerList(Integer currentPage, Integer everyPage,AdvertVer advertVer);
	/**
	 * 版本名称是否存在
	 * @returnveridIsExist
	 */
	public Integer verIsExist(AdvertVer advertVer);
	/**
	 * 版本号是否存在
	 * @returnveridIsExist
	 */
	public Integer veridIsExist(AdvertVer advertVer);

	/**
	 * 修改版本
	 */
	public int updateVer(AdvertVer advertVer);
	/**
	 * 增加版本
	 */
	public int addVer(AdvertVer advertVer);
	/**
	 * 是否被使用
	 */
	public int queryVer(int id);
	/**
	 * 删除版本
	 */
	public int deleteVer(int id);
	
	/**
	 * exportVer
	 * 导出版本
	 */
	public List<AdvertVer> exportVer(AdvertVer advertVer);
	
	/**
	 * saveAuditVer
	 * 审核版本
	 */
	public int saveAuditVer(AdvertVer advertVer);
	
	/**
	 * 上架版本
	 */
	public int activeVer(int id);
	/**
	 * 下架版本
	 */
	public int inactiveVer(int id);
	
	
	
	
	
	
	
	/**
	 * getAdvertConList
	 * 广告内容分页显示查询列表
	 */
	public Map getAdvertConList(Integer currentPage, Integer everyPage,AdvertCon advertCon);
	/**
	 * 内容是否存在
	 * @return
	 */
	public Integer conIsExist(AdvertCon advertCon);

	/**
	 * 修改内容
	 */
	public int updateCon(AdvertCon advertCon);
	/**
	 * 增加内容
	 */
	public int addCon(AdvertCon advertCon);
	/**
	 * deleteCon   删除内容
	 */
	public int deleteCon(int id);
}
