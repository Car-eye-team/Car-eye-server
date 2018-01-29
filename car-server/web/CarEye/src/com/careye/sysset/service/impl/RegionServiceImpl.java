/**
 * Description: car-eye车辆管理平台
 * 文件名：RegionServiceImpl.java
 * 版本信息：1.0
 * 日期：2015-4-7
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.Region;
import com.careye.sysset.service.RegionService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

/**
 * @项目名称：car-eye
 * @类名称：RegionServiceImpl
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-7 上午11:04:59
 * @修改人：Yuqk
 * @修改时间：2015-4-7 上午11:04:59
 * @修改备注：
 * @version 1.0
 */
public class RegionServiceImpl extends GenericService implements RegionService {
	private SysOperateLogService logService;

	/**
	 * 批量删除行政区划记录
	 */
	@Override
	public int deleteRegion(List<String> list) {
		int result=0;
		for (int i = 0; i < list.size(); i++) {
			Region region = getRegionById(Integer.parseInt(list.get(i)));
			int childCount = findChildIsExist(region);
			if (childCount > 0) {
				result = -3;
				return result;
			} 
//			else  {
//				logService.addLogInfo(SessionUtils.getUser().getId(),
//						"删除行政区划记录:区划名称:" + region.getCnname() + ";行政编码:"
//								+ region.getSzcode() + ";创建时间:"
//								+ region.getCreatetime() + ";影响数据ID:"
//								+ region.getId(), 3);
//				result=this.baseDao.delete("oracle-regionSQL.deleteRegion", list);
//			}
			
		}
		return this.baseDao.delete("oracle-regionSQL.deleteRegion", list);
	}

	/**
	 * 分页查询行政区划列表
	 */
	@Override
	public Map findPageRegionList(Region region, int page, int limit) {
		return this.baseDao
				.findPageList("oracle-regionSQL.findPageRegionList",
						"oracle-regionSQL.findPageRegionListCount", region,
						page, limit);
	}

	/**
	 * 保存行政区划
	 */
	@Override
	public int saveRegion(Region region) {
		int szcode = findSzcodeIsExist(region);
		int cnName = findCnnameIsExist(region);
		int result = 0;
		if (szcode > 0) {
			result = -4;
		} else if (cnName > 0) {
			result = -3;
		} else {
			region.setIsdelete(0);
			region.setCreatetime(DateUtil.getSQLDate());
			if (region.getParentid()==null) {
//				if (StringUtils.isNotEmpty(region.getParentid())) {
				result = this.baseDao.save(
						"oracle-regionSQL.saveProvinceRegion", region);
				this.baseDao.update("oracle-regionSQL.updateProvinceRegion",
						region);
			} else {
				result = this.baseDao.save("oracle-regionSQL.saveRegion",
						region);
			}
			if (result > 0) {
				logService.addLogInfo(SessionUtils.getUserId(),
						"添加行政区划记录:区划名称:" + region.getCnname() + ";创建时间:"
								+ region.getCreatetime() + ";影响数据ID:"
								+ region.getId(), 1);
			}
		}
		return result;
	}

	/**
	 * 更新行政区划
	 */
	@Override
	public int updateRegion(Region region) {
		int cnName = findCnnameIsExist(region);
		int szcode = findSzcodeIsExist(region);
		int result = 0;
		if (szcode > 0) {
			result = -4;
		} else if (cnName > 0) {
			result = -3;
		} else {
			region.setUpdatetime(DateUtil.getSQLDate());
			result = this.baseDao.update("oracle-regionSQL.updateRegion",
					region);
			if (result > 0) {
				logService.addLogInfo(SessionUtils.getUserId(), "更新行政区划:区划名称:"
						+ region.getCnname() + ";创建时间:"
						+ region.getCreatetime() + ";影响数据ID:" + region.getId(),
						2);
			}
			return result;
		}
		return result;
	}

	/**
	 * 依据id查询行政区划
	 * 
	 * @param id
	 * @return
	 */
	private Region getRegionById(Integer id) {
		return (Region) this.baseDao.queryForObject(
				"oracle-regionSQL.findRegionById", id);
	}

	/**
	 *判断区划名称是否存在
	 * 
	 * @param region
	 * @return
	 */
	private int findCnnameIsExist(Region region) {
		return (Integer) this.baseDao.queryForObject(
				"oracle-regionSQL.findCnnameIsExist", region);
	}

	/**
	 *判断区划编码是否存在
	 * 
	 * @param region
	 * @return
	 */
	private int findSzcodeIsExist(Region region) {
		return (Integer) this.baseDao.queryForObject(
				"oracle-regionSQL.findSzcodeIsExist", region);
	}
	/**
	 *判断下级区划是否存在
	 * 
	 * @param region
	 * @return
	 */
	private int findChildIsExist(Region region) {
		return (Integer) this.baseDao.queryForObject(
				"oracle-regionSQL.findChildIsExist", region);
	}
	/*
	 * getter setter
	 */
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	/**
	 * 依据行政级别查询所有行政区划
	 */
	@Override
	public List<Region> findRegionListByClevel(int clevel) {
		return this.baseDao.queryForList("oracle-regionSQL.findRegionByClevel",
				clevel);

	}

}
