/**
* Description: car-eye车辆管理平台
* 文件名：OrgazicationDeptServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.service.CarService;
import com.careye.system.domain.Bloc;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：FMS
 * @类名称：OrgazicationDeptServiceImpl
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-20 下午02:31:58
 * @修改人：zhangrong
 * @修改时间：2014-5-20 下午02:31:58
 * @修改备注：
 * @version 1.0
 */
public class OrgazicationDeptServiceImpl extends GenericService implements OrgazicationDeptService {
	
	private CarService carService;
	
	/**
	 * 根据当前级别ID获取上一级别ID
	 * @param id
	 * @return
	 */
	@Override
	public List<Bloc> getNextHigherLevel(int id) {
		return this.baseDao.queryForList("oracle-OrgazicationDeptSQL.getNextHigherLevel", id);
	}
	
	/**
	 * 根据当前级别ID获取下一级别ID
	 * @param id
	 * @return
	 */
	public List<Integer> getNextDowmLevel(int id){
		return this.baseDao.queryForList("oracle-OrgazicationDeptSQL.getNextDowmLevel", id);
	}
	/**
	 * 根据部门id得到部门详细信息
	 */
	public Bloc quertDeptById(int id){
		return (Bloc)this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.quertDeptById", id);
	}
	
	/**
	 * 组织机构列表不分页
	 * @return
	 */
	public List<Bloc> selectOrgazicationDeptList(Bloc orgazicationDept){
		return this.baseDao.queryForList("oracle-OrgazicationDeptSQL.selectOrgazicationDeptList",orgazicationDept);
	}
	
	/**
	 * 分页组织机构列表并统计车辆树
	 * @return
	 */
	public Map findPageOrgazicationDeptList(int currentPage, int everyPage,Bloc orgazicationDept) {
		return this.baseDao.findPageList("oracle-OrgazicationDeptSQL.findPageOrgazicationDeptList", "oracle-OrgazicationDeptSQL.findPageOrgazicationDeptListCount", orgazicationDept, currentPage, everyPage);
	}
	
	/**
	 * 添加组织机构
	 * @param Bloc
	 */
	public int addOrgazicationDept(Bloc orgazicationDept) {
		String current_time = DateUtil.getSQLDate();
		orgazicationDept.setCreatetime(current_time);
		return this.baseDao.save("oracle-OrgazicationDeptSQL.addOrgazicationDept", orgazicationDept);
	}
	/**
	 * 更新组织机构
	 * @param Bloc
	 */
	public int updateOrgazicationDept(Bloc orgazicationDept){
		int count = 0;
		if(orgazicationDept.getParentid() != null && 
				(orgazicationDept.getId().intValue() == orgazicationDept.getParentid().intValue())){ //只修改组织机构信息
			orgazicationDept.setParentid(null);
			count = this.baseDao.update("oracle-OrgazicationDeptSQL.updateOrgazicationDept", orgazicationDept);
		}else{
			Integer predeptid = null;
			
			//如果上级A转移到它的下级B(转移之前B的parentid为A的id)，则把B的parentid设置为A的parentid
			Integer bid = this.quertDeptById(orgazicationDept.getParentid()).getParentid();
			if(bid.intValue() == orgazicationDept.getId()){
				Bloc org = new Bloc();
				org.setId(orgazicationDept.getParentid()); 
				predeptid = this.quertDeptById(orgazicationDept.getId()).getParentid();
				org.setParentid(predeptid); 
				this.updateOrgazicationDept(org);
				predeptid = orgazicationDept.getId();
			}else{
				predeptid = this.quertDeptById(orgazicationDept.getId()).getParentid();
			}
			Integer parentid = orgazicationDept.getParentid();
		    count = this.baseDao.update("oracle-OrgazicationDeptSQL.updateOrgazicationDeptByMove", orgazicationDept);
		    //转移之后重新统计组织机构下面车辆数
		    if(parentid != null){
		    	carService.updateDeptTotal(parentid);
		    }
		    carService.updateDeptTotal(predeptid);
		}
	    return count;
	}
	/**
	 * 组织机构名是否存在
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer deptNameIsExist(Bloc orgazicationDept){
		return (Integer)this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.deptNameIsExist",orgazicationDept);
	}
	
	/**
	 * 组织机构名下是否有用户组
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer containUserGroupNum(int id){
		return (Integer)this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.containUserGroupNum",id);
	}
	
	/**
	 * 组织机构名下是否有车辆
	 * @param orgazicationDeptInfo
	 * @return
	 */
	public Integer containCarNum(int id){
		return (Integer)this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.containCarNum",id);
	}
	
	/**
	 * 删除组织机构信息
	 * @param id
	 * @return
	 */
	public int deleteOrgazicationDept(int id){
		return this.baseDao.delete("oracle-OrgazicationDeptSQL.deleteOrgazicationDept", id);
	}
	/**
	 * 检测组织机构下面是否还有组织
	 * @param id
	 * @return
	 */
	public int queryDeptCount(int id){
		return (Integer)this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.queryDeptCount", id);
	}
	
	/**
	 * 得到组织机构下面车辆列表，不分页
	 */
	public List<Bloc> getCarCountList(Bloc orgazicationDept){
		return this.baseDao.queryForList("oracle-OrgazicationDeptSQL.getCarCountList", orgazicationDept);
	}
	/**
	 * 导出组织机构下面车辆列表
	 */
	public List<Bloc> exportCarCountList(Map<String, String> paramsMap){
		return this.baseDao.queryForList("oracle-OrgazicationDeptSQL.exportCarCountList", paramsMap);
	}
	
	/**
	 * 得到组织机构下面车辆列表，分页
	 */
	public Map findPageCarCountList (int currentPage, int everyPage,Bloc orgazicationDept){
		return this.baseDao.findPageList("oracle-OrgazicationDeptSQL.findPageCarCountList", "oracle-OrgazicationDeptSQL.findPageCarCountListCount", orgazicationDept, currentPage, everyPage);
	}
	
	/**
     * 更新机构表中的车辆总数、离线数、长离数
     */
	@Override
	public int updateDeptCarNum(Map map) {
		return this.baseDao.update("oracle-OrgazicationDeptSQL.updateDeptCarNum", map);
	}
	
	public CarService getCarService() {
		return carService;
	}
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@Override
	public Integer getDeptIdByDeptName(String blocname) {
		return (Integer) this.baseDao.queryForObject("oracle-OrgazicationDeptSQL.getDeptIdByDeptName", blocname);
	}
	
}




