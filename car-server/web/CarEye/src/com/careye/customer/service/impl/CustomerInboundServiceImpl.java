/**
* Description: car-eye车辆管理平台
* 文件名：CustomerInfoServiceImpl.java
* 版本信息：1.0
* 日期：2014-3-6
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.service.SysOperateLogService;
import com.careye.customer.domain.CustomerInbound;
import com.careye.customer.service.CustomerInboundService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
/**
 * @项目名称：car-eye
 * @类名称：CustomerInboundServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-17 下午12:39:55
 * @修改人：huangqin
 * @修改时间：2015-3-17 下午12:39:55
 * @修改备注：
 * @version 1.0
 */
public class CustomerInboundServiceImpl extends GenericService implements CustomerInboundService{

	private  SysOperateLogService logService;
    
	
	public Map<Object, Object> getAllCustomerInbound(int currentPage, int everyPage, CustomerInbound customerInbound) {
		return this.baseDao.findPageList("oracle-customerInboundSQL.getAllCustomerInbound",
				"oracle-customerInboundSQL.getAllCustomerInboundCount", customerInbound, currentPage,
				everyPage);
	}

	/**
	 *Excel导出
	 */
	@Override
	public List<CustomerInbound> findCustomerInboundPageList(CustomerInbound customerInbound) {
		logService.addLogInfo(SessionUtils.getUserId(), "Excel导出客户来电信息", 4);
		return this.baseDao.queryForList("oracle-customerInboundSQL.getAllCustomerInbound",customerInbound);
	}
	
	
	/**
	 * 删除客户来电记录
	 * @param id
	 * @return
	 */
	//@Log(operationType="delete操作:",operationName="删除客户来电记录")
	public int delCustomerInbound(List <String> list) {
		
		for(int i=0;i<list.size();i++){
			//根据id得到对象
			CustomerInbound customerInbound=this.getCustomerInboundById(Integer.parseInt(list.get(i)));
			
			logService.addLogInfo(customerInbound.getUserid(), "删除客户来电记录:客户名称:"+customerInbound.getCname()
					+";创建时间:"+
					customerInbound.getCreatetime()+";影响数据ID:"+customerInbound.getId(),3);
		}
		
		return  this.baseDao.delete("oracle-customerInboundSQL.delCustomerInbound", list);
	}

	/**
	 * 根据id得到客户来电信息
	 * @param id
	 * @return
	 */
	private CustomerInbound getCustomerInboundById(int id){
		
	   return 	 (CustomerInbound) this.baseDao.queryForObject("oracle-customerInboundSQL.getCustomerInboundById",id);
	}
	
	/**
	 * 添加客户来电记录信息
	 * 
	 * @param petFence
	 * @return
	 */
	public int addCustomerInbound(CustomerInbound customerInbound){
		return this.baseDao.save("oracle-customerInboundSQL.addCustomerInbound", customerInbound);
	}
	
	/**
	 * 更新坐席最后一条记录挂机时间
	 * @param agentid
	 */
	public int updateHangupcalltime(String agentid){
		Map map = new HashMap<String, String>();
		map.put("agentid", agentid);
		map.put("hangupcalltime", DateUtil.getSQLDate());
		return this.baseDao.update("oracle-customerInboundSQL.updateHangupcalltime", map);
	}
	

	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}
	
	
	
}
