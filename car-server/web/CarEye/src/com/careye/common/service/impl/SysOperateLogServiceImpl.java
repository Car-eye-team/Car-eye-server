/**
* Description: car-eye车辆管理平台
* 文件名：CityInfoServiceImpl.java
* 版本信息：1.0
* 日期：2013-8-5
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.common.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.common.domain.CommondLog;
import com.careye.common.domain.SysOperateLog;
import com.careye.common.service.SysOperateLogService;
import com.careye.utils.DateUtil;

/**
 * @项目名称：car-eye
 * @类名称：CityInfoServiceImpl
 * @类描述：
 * @创建人：liliang
 * @创建时间：2013-8-5 上午10:26:07
 * @修改人：liliang
 * @修改时间：2013-8-5 上午10:26:07
 * @修改备注：
 * @version 1.0
 */
public class SysOperateLogServiceImpl extends GenericService implements SysOperateLogService{

	@Override
	public Integer addLogInfo(Integer userid,String content,Integer operattype) {
		SysOperateLog log=new SysOperateLog();
		log.setUserid(userid);
		log.setContent(content);
		log.setOperattype(operattype);
		log.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-logSQL.addLogInfo", log);
	}

	@Override
	public Map findLogInfoPageList(int currentPage, int everyPage, SysOperateLog log){
		return this.baseDao.findPageList("oracle-logSQL.findLogInfoPageList",
				"oracle-logSQL.findLogInfoPageListCount",log,currentPage,everyPage);
	}
	
	/**
	 * 添加指令下发日志记录
	 * @param carid
	 * @param userid
	 * @param mgsid
	 * @param msgtype
	 * @param seq
	 * @param data
	 * @return
	 */
	public Integer addCommondLog(Integer carid,Integer userid,Integer msgid,String msgtype,Integer seq,String data){
		
		CommondLog commondLog=new CommondLog();
		commondLog.setCarid(carid);
		commondLog.setCreatetime(DateUtil.getSQLDate());
		commondLog.setData(data);
		commondLog.setMsgid(msgid);
		commondLog.setMsgtype(msgtype);
		commondLog.setSeq(seq);
		commondLog.setUserid(userid);
		
		return this.baseDao.save("oracle-logSQL.addCommondLog", commondLog);
		
	}
	
	@Override
	public int deleteLogInfo(int id) {
		// TODO Auto-generated method stub
		return this.baseDao.delete("oracle-logSQL.deleteLogInfo", id);
	}

	@Override
	public List<SysOperateLog> exportLogInfo(SysOperateLog logInfo) {
		return this.baseDao.queryForList("oracle-logSQL.findLogInfoPageList", logInfo);
	}
	

}
