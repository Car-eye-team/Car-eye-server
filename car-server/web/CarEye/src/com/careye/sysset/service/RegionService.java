/**
 * Description: car-eye车辆管理平台
 * 文件名：RegionService.java
 * 版本信息：1.0
 * 日期：2015-4-7
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.service;

import java.util.List;
import java.util.Map;

import com.careye.sysset.domain.Region;

/**
 * @项目名称：car-eye
 * @类名称：RegionService
 * @类描述：
 * @创建人：Yuqk
 * @创建时间：2015-4-7 上午11:04:14
 * @修改人：Yuqk
 * @修改时间：2015-4-7 上午11:04:14
 * @修改备注：
 * @version 1.0
 */
public interface RegionService {
	/**
	 * 查询行政区域列表
	 * 
	 * @param region
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map findPageRegionList(Region region, int page, int limit);

	/**
	 * 保存新增行政区域
	 * 
	 * @param region
	 * @return
	 */
	public int saveRegion(Region region);

	/**
	 * 更新行政区域
	 * 
	 * @param reigon
	 * @return
	 */
	public int updateRegion(Region reigon);

	/**
	 * 删除行政区域
	 * 
	 * @param region
	 * @return
	 */
	public int deleteRegion(List<String> list);

	/**
	 * 依据行政级别查询所有行政区划
	 * 
	 * @param clevel
	 */
	public List<Region> findRegionListByClevel(int clevel);

}
