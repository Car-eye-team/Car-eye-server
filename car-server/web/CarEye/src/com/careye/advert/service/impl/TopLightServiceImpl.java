/**
 * 
 */
package com.careye.advert.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.advert.domain.AdvertCon;
import com.careye.advert.domain.AdvertType;
import com.careye.advert.domain.AdvertVer;
import com.careye.advert.service.TopLightService;
import com.careye.base.service.GenericService;
import com.careye.constant.Constant;
import com.careye.utils.DateUtil;

/**
 * @author Administrator
 *
 */
public class TopLightServiceImpl extends GenericService implements TopLightService{
	

	/**
	 * getAdvertPosList
	 * 广告版本分页查询列表
	 */
	public Map getAdvertVerList(Integer currentPage, Integer everyPage,
			AdvertVer advertVer) {
		// TODO Auto-generated method stub
		advertVer.setPositionid(Constant.TOPLIGHT);
		return this.baseDao.findPageList("oracle-topLightSQL.getAdvertVerList",
				"oracle-topLightSQL.getAdvertVerListCount", advertVer,currentPage,everyPage);
	}

	/**
	 * getPositionList   位置下拉列表
	 */
	public List<AdvertVer> getPositionList() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-topLightSQL.getPositionList");
	}
	
	/**
	 * getTypeList   类型下拉列表
	 */
	public List<AdvertType> getTypeList() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-topLightSQL.getTypeList");
	}
	/**
	 * getVersionList  版本下拉列表
	 */
	public List<AdvertVer> getVersionList() {
		// TODO Auto-generated method stub
		return this.baseDao.queryForList("oracle-topLightSQL.getVersionList");
	}
	
	
	
	/**
	* 版本是否存在
	*/
	public Integer verIsExist(AdvertVer advertVer) {
		// TODO Auto-generated method stub
		advertVer.setPositionid(Constant.TOPLIGHT);
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.verIsExist",advertVer);
	}
	/**
	* 版本是否存在
	*/
	public Integer veridIsExist(AdvertVer advertVer) {
		// TODO Auto-generated method stub
		advertVer.setPositionid(Constant.TOPLIGHT);
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.veridIsExist",advertVer);
	}

	/**
	* 修改版本
	*/
	public int updateVer(AdvertVer advertVer) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-topLightSQL.updateVer",advertVer);
	}
	/**
	* 增加版本
	*/
	public int addVer(AdvertVer advertVer) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		advertVer.setCreatetime(current_time);
		advertVer.setReltime(current_time);
		advertVer.setAuditstatus(0);
		advertVer.setOnstatus(1);
		advertVer.setPositionid(Constant.TOPLIGHT);
		return this.baseDao.save("oracle-topLightSQL.saveVer", advertVer);
	}
	
	/**
	 * 是否被使用
	 */
	public int queryVer(int id) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.queryVer",id);
	}
	
	/**
	 * 删除版本
	 */
	public int deleteVer(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-topLightSQL.deleteVer", id);
	}

	/**
	 * exportVer
	 * 导出版本
	 */
	public List<AdvertVer> exportVer(AdvertVer advertVer) {
		// TODO Auto-generated method stub
		advertVer.setPositionid(Constant.TOPLIGHT);
		return this.baseDao.queryForList("oracle-topLightSQL.getAdvertVerList", advertVer);
	}

	/**
	 * saveAuditVer
	 * 审核版本
	 */
	public int saveAuditVer(AdvertVer advertVer) {
		String current_time=DateUtil.getSQLDate();
		advertVer.setAudittime(current_time);
		return this.baseDao.update("oracle-topLightSQL.saveAuditVer",advertVer);
	}
	/**
	 * 上架版本
	 */
	public int activeVer(int id){
		return this.baseDao.update("oracle-topLightSQL.activeVer", id);
	}
	
	/**
	 * 下架版本
	 */
	public int inactiveVer(int id){
		return this.baseDao.update("oracle-topLightSQL.inactiveVer", id);
	}

	
	
	
	
	
	
	
	/**
	 * getAdvertConList
	 * 广告内容分页显示查询列表
	 */
	public Map getAdvertConList(Integer currentPage, Integer everyPage,
			AdvertCon advertCon) {
		// TODO Auto-generated method stub
		advertCon.setPositionid(Constant.TOPLIGHT);
		return this.baseDao.findPageList("oracle-topLightSQL.getAdvertConList",
				"oracle-topLightSQL.getAdvertConListCount", advertCon,currentPage,everyPage);
	}
	
	/**
	* 内容是否存在
	*/
	public Integer conIsExist(AdvertCon advertCon) {
		// TODO Auto-generated method stub
		advertCon.setPositionid(Constant.TOPLIGHT);
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.conIsExist",advertCon);
	}

	/**
	* 修改内容
	*/
	public int updateCon(AdvertCon advertCon) {
		// TODO Auto-generated method stub
		return this.baseDao.update("oracle-topLightSQL.updateCon",advertCon);
	}
	/**
	* 增加内容
	*/
	public int addCon(AdvertCon advertCon) {
		// TODO Auto-generated method stub
		String current_time=DateUtil.getSQLDate();
		advertCon.setCreatetime(current_time);
		advertCon.setReltime(current_time);
		advertCon.setPositionid(Constant.TOPLIGHT);
		return this.baseDao.save("oracle-topLightSQL.saveCon", advertCon);
	}
	/**
	 * deleteCon删除内容
	 */
	public int deleteCon(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-topLightSQL.deleteCon", id);
	}

	/**
	 * 获取已存在的广告序号   
	 */
	public int selconnumber(int pid) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.selconnumber",pid);
	}

	/**
	 * 获取广告类型id
	 */
	public int selTypeid(String typename) {
		// TODO Auto-generated method stub
		return (Integer)this.baseDao.queryForObject("oracle-topLightSQL.selTypeid",typename);
	}
	
}
