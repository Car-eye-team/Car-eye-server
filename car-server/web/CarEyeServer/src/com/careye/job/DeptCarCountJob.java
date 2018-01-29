/**
 * Description: 车队管理系统
 * 文件名：AutoTaskSystem.java
 * 版本信息：1.0
 * 日期：2013-9-6
 * Copyright car-eye车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.constant.ServiceConfig;
import com.careye.utils.DateUtil;

/**
 * @car-eye车辆管理平台业务处理
 * @类名称：AutoTaskSystem
 * @类描述：系统自动处理任务
 * @创建人：zr
 * @创建时间：2013-9-6 下午01:52:50
 * @修改人：zr
 * @修改时间：2013-9-6 下午01:52:50
 * @修改备注：
 * @version 1.0
 */
public class DeptCarCountJob {
	protected static final Logger logger = Logger.getLogger(DeptCarCountJob.class);

	public void startJob(){
		try {
			logger.info("自动任务更新组织机构下面车辆在线离线数量任务启动......");
			logger.info(DateUtil.getSQLDate());

			startUpdateDept();

			logger.info("自动任务更新组织机构下面车辆在线离线数量任务处理完成......");
			logger.info(DateUtil.getSQLDate());
		} catch (Exception e) {
			logger.error("自动任务更新组织机构下面车辆在线离线数量任务启动异常"+e);
			e.printStackTrace();
		}
	}

	/**
	 * 更新组织机构下面车辆在线离线
	 */
	private void startUpdateDept(){
		try {
			List<Integer> list = ServiceConfig.carService.selectAllDeptidList();
			if(list != null){
				logger.info("操作机构总数："+list.size());
				for (Integer deptid : list) {
					
					//根据deptid获车辆总数
					int total = ServiceConfig.carService.getCarInfoSum(deptid);
					//根据deptid获取离线总数
					int caroffline = ServiceConfig.carService.getOffLineCarInfoSum(deptid);
					//根据deptid获取长离总数
					int longoffline = ServiceConfig.carService.getLongOffLineCarInfoSum(deptid);
					
					//更新
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("total", total);
					map.put("caroffline", caroffline);
					map.put("longoffline", longoffline);
					map.put("deptid", deptid);
					
					logger.info("操作机构："+deptid+",map:"+map.toString());
					ServiceConfig.carService.updateDeptCarNum(map);
					
				}
			}
		} catch (Exception e) {
			logger.error("更新组织机构下面车辆在线离线异常"+e);
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		new DeptCarCountJob().startJob();
	}

}
