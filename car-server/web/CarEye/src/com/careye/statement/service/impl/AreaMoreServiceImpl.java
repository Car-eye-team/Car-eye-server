/**
* Description: car-eye车辆管理平台
* 文件名：AreaSetServiceImpl.java
* 版本信息：1.0
* 日期：2014-6-3
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.statement.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.AreaCar;
import com.careye.car.domain.AreaSet;
import com.careye.car.domain.CarDriver;
import com.careye.car.service.AreaSetService;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.SysOperateLogService;
import com.careye.statement.domain.AreaMore;
import com.careye.statement.service.AreaMoreService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

/**
 * @项目名称：FMS
 * @类名称：AreaSetServiceImpl
 * @类描述：
 * @创建人：Administrator
 * @创建时间：2014-6-3 下午03:41:56
 * @修改人：Administrator
 * @修改时间：2014-6-3 下午03:41:56
 * @修改备注：
 * @version 1.0
 */
public class AreaMoreServiceImpl extends GenericService implements AreaMoreService {
	
	private SysOperateLogService logService;
	/**
	 * 条件查询系统区域信息分页
	 * @param areaSet
	 * @return
	 */
	public Map queryPageAreaMoreList(final int currentPage, final int everyPage,AreaMore areaMore){
		return this.baseDao.findPageList("oracle-areaMoreSQL.getAreaMoreList",
				"oracle-areaMoreSQL.getAreaMoreListCount", areaMore,currentPage,everyPage);
	}
	
	
	@Override
	public List<TerminalPositionInfo> queryCarPositionInfoList(TerminalPositionInfo terminalPositionInfo,TerminalPositionInfo te) {
		
		List<TerminalPositionInfo>  okList=new ArrayList<TerminalPositionInfo> ();
		
		//
		List<TerminalPositionInfo> operList=null;
		for(int i=0;i<terminalPositionInfo.getIds().size();i++){
				  operList=new ArrayList<TerminalPositionInfo> ();
			  //根据id得到List集合
				TerminalPositionInfo info=(TerminalPositionInfo) this.baseDao.queryForObject("oracle-areaMoreSQL.getMoreAreaById",terminalPositionInfo.getIds().get(i));
				if(StringUtils.isNotEmty(te.getCarnumber())){
					info.setCarnumber(te.getCarnumber());
				}
				if(te.getBlocid()!=null){
					info.setBlocid(te.getBlocid());
				}
				
				if(info.getAreatype()==1){
					operList= this.baseDao.queryForList("oracle-areaMoreSQL.queryCarPositionInfoList", info);
				}else if(info.getAreatype()==2){
					operList= this.baseDao.queryForList("oracle-areaMoreSQL.queryCarPositionInfoTwoList", info);
				}
				
				for(int k=0;k<operList.size();k++){
					TerminalPositionInfo tpi=operList.get(k);
					if(!okList.contains(tpi)){
						okList.add(tpi);
					}
				}
				
				operList=null;
			}
		
		return okList;
	}
	
	@Override
	public List<TerminalPositionInfo> queryCarPositionInfoTwoList(TerminalPositionInfo terminalPositionInfo) {
		List<TerminalPositionInfo>  okList=new ArrayList<TerminalPositionInfo> ();
		
		if(terminalPositionInfo.getAreatype()==1){
			okList= this.baseDao.queryForList("oracle-areaMoreSQL.queryCarPositionInfoList", terminalPositionInfo);
		}else if(terminalPositionInfo.getAreatype()==2){
			okList= this.baseDao.queryForList("oracle-areaMoreSQL.queryCarPositionInfoTwoList", terminalPositionInfo);
		}
		
		return okList;
	}

	
	/**
	 * 添加系统区域信息
	 * @param areaMore
	 * @return
	 */
	public int insertAreaMore(AreaMore areaMore){
		 int areaName = queryAreaMoreNameIsExits(areaMore);
		 int result=0;
		 if(areaName>0){
			result=-3;
		 }else{
			    areaMore.setCreatetime(DateUtil.getSQLDate());
			    result=this.baseDao.save("oracle-areaMoreSQL.insertAreaMore", areaMore);
				if(result>0){
					int type=areaMore.getAreatype();
					String typeString=null;
					if(type==1){
						typeString="圆形区域";
					}else if(type==2){
						typeString="矩形区域";
					}else if(type==3){
						typeString="多边形区域";
					}
					logService.addLogInfo(areaMore.getUserid(), "添加多区域信息记录:区域名称:"+areaMore.getAreaname()
							+";区域类型:"+typeString
							+";影响数据ID:"+areaMore.getId(), 1);
				}
		 }
		 return result;
	}
	
	/**
	 * 删除系统区域信息
	 * @param id
	 * @return
	 */
	public int deleteAreaMore(int id){
		AreaMore areaMore=this.getAreaMoreById(id);
		int type=areaMore.getAreatype();
		String typeString=null;
		if(type==1){
			typeString="圆形区域";
		}else if(type==2){
			typeString="矩形区域";
		}else if(type==3){
			typeString="多边形区域";
		}
		logService.addLogInfo(SessionUtils.getUserId(), "删除多区域信息记录:区域名称:"+areaMore.getAreaname()
				+";区域类型:"+typeString
				+";影响数据ID:"+id, 3);
			
		return this.baseDao.delete("oracle-areaMoreSQL.deleteAreaMore", id);
	}
	
	
	/**
	 * 根据id得到系统区域信息对象
	 * @param id
	 * @return
	 */
	private AreaMore getAreaMoreById(int id){
		return  (AreaMore) this.baseDao.queryForObject("oracle-areaMoreSQL.getAreaMoreById",id);
	}
	
	/**
	 * 更新系统区域信息
	 * @param areaMore
	 * @return
	 */
	public int updateAreaMore(AreaMore areaMore){
		 int areaName = queryAreaMoreNameIsExits(areaMore);
		 int result=0;
		 if(areaName>0){
			result=-3;
		 }else{
			 result=this.baseDao.update("oracle-areaMoreSQL.updateAreaMore", areaMore);
				if(result>0){
					int type=areaMore.getAreatype();
					String typeString=null;
					if(type==1){
						typeString="圆形区域";
					}else if(type==2){
						typeString="矩形区域";
					}else if(type==3){
						typeString="多边形区域";
					}
					logService.addLogInfo(SessionUtils.getUserId(), "更新多区域信息记录:区域名称:"+areaMore.getAreaname()
							+";区域类型:"+typeString
							+";影响数据ID:"+areaMore.getId(), 2);
				}
		   
		 }
		 return result;
	}

	/**
	 * 查询是否相同的名字存在
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public Integer queryAreaMoreNameIsExits(AreaMore areaMore) {
		
		return (Integer) this.baseDao.queryForObject("oracle-areaMoreSQL.queryAreaMoreNameIsExits",areaMore);
	}

	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}

	
	
}
