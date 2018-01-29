/**
* Description: car-eye车辆管理平台
* 文件名：MongoDB.java
* 版本信息：1.0
* 日期：2015-6-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;

import com.careye.common.domain.Alarm;
import com.careye.common.domain.HistoryPositionInfo;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.constant.ServiceConfig;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

import common.Logger;

/**
 * @项目名称：car-eyeSERVER
 * @类名称：MongoBusiness
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-6-10 上午10:44:22
 * @修改人：zhangrong
 * @修改时间：2015-6-10 上午10:44:22
 * @修改备注：
 * @version 1.0
 */
public class MongoBusiness {
	
	private static final Logger logger = Logger.getLogger(MongoBusiness.class);

	/**
	 * 指定条件查询历史报警数据-取出所有记录
	 * @param collection
	 * @param currentPage
	 * @param everyPage
	 * @param alarm
	 * @return
	 */
	public static Map getAlarmList(DBCollection collection,int currentPage,int everyPage,Alarm alarm) {
		
		Map map = new HashedMap();
		List<Alarm> list = new ArrayList<Alarm>();
		
		//多条件查询
		BasicDBObject qobject = new BasicDBObject();
		if(alarm.getBlocid() != null){
			List<Integer> deptidlist = ServiceConfig.orgazicationDeptService.getNextDowmLevel(alarm.getBlocid());
			BasicDBList values = new BasicDBList();
			for(Integer did : deptidlist){
				values.add(did);
			}
			qobject.put("blocid", new BasicDBObject("$in", values));
		}
		
		if(StringUtils.isNotEmty(alarm.getCarnumber())){
			Pattern pattern = Pattern.compile("^.*"+alarm.getCarnumber()+".*$", Pattern.CASE_INSENSITIVE);
			qobject.put("carnumber",pattern);
		}
		if(StringUtils.isNotEmty(alarm.getAlarmkey())){
			qobject.put("alarmkey",alarm.getAlarmkey());
		}
		Object obj = null;
		if(StringUtils.isNotEmty(alarm.getStime())){
			obj =  new BasicDBObject("$gte", alarm.getStime());
			if(StringUtils.isNotEmty(alarm.getEtime())){
				obj =  new BasicDBObject("$gte", alarm.getStime()).append("$lte", alarm.getEtime());
			}
			qobject.put("alarmtime", obj);
		}else{
			if(StringUtils.isNotEmty(alarm.getEtime())){
				obj =  new BasicDBObject("$lte", alarm.getEtime());
				qobject.put("alarmtime", obj);
			}
		}
		int skipResults = (currentPage - 1) * everyPage;//从这一行数据开始查询
		
		logger.info("开始查询："+DateUtil.getSQLDate());
		DBCursor cur1 = collection.find(qobject);
		int count = cur1.count();//总条数
		DBCursor cur = collection.find(qobject).sort(new BasicDBObject("alarmtime",-1)).skip(skipResults).limit(everyPage);
		int i=0;//为list集合添加唯一字段
		while (cur.hasNext()) {
			JSONObject jObject = JSONObject.fromObject(cur.next());
			try{
				Alarm alarm1 =  (Alarm)JSONObject.toBean(jObject, Alarm.class);
				alarm1.setId(i);
				list.add(alarm1);
				i++;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("list", list);
		map.put("totalCount", count);
		
		logger.info(list.size());
		logger.info("结束查询："+DateUtil.getSQLDate());
		return map;
	}
	
	/**
	 * 指定条件查询历史位置数据-取出所有记录
	 * @param collection
	 * @param currentPage
	 * @param everyPage
	 * @param alarm
	 * @return
	 */
	public static Map getCarhistoryList(DBCollection collection,int currentPage,int everyPage,HistoryPositionInfo historyPos) {
		
		Map map = new HashedMap();
		List<HistoryPositionInfo> list = new ArrayList<HistoryPositionInfo>();
		
		//多条件查询
		BasicDBObject qobject = new BasicDBObject();
		if(historyPos.getBlocid() != null){
			List<Integer> deptidlist = ServiceConfig.orgazicationDeptService.getNextDowmLevel(historyPos.getBlocid());
			BasicDBList values = new BasicDBList();
			for(Integer did : deptidlist){
				values.add(did);
			}
			qobject.put("blocid", new BasicDBObject("$in", values));
		}
		
		if(StringUtils.isNotEmty(historyPos.getCarnumber())){
			Pattern pattern = Pattern.compile("^.*"+historyPos.getCarnumber()+".*$", Pattern.CASE_INSENSITIVE);
			qobject.put("carnumber",pattern);
		}
		if(StringUtils.isNotEmty(historyPos.getTerminal())){
			Pattern pattern = Pattern.compile("^.*"+historyPos.getTerminal()+".*$", Pattern.CASE_INSENSITIVE);
			qobject.put("terminal",pattern);
		}
		if(historyPos.getCarstatus() != null){
			qobject.put("carstatus",historyPos.getCarstatus());
		}
		if(historyPos.getGpsflag() != null){
			qobject.put("gpsflag",historyPos.getGpsflag());
		}
		Object obj = null;
		if(StringUtils.isNotEmty(historyPos.getStime())){
			obj =  new BasicDBObject("$gte", historyPos.getStime());
			if(StringUtils.isNotEmty(historyPos.getEtime())){
				obj =  new BasicDBObject("$gte", historyPos.getStime()).append("$lte", historyPos.getEtime());
			}
			qobject.put("createtime", obj);
		}else{
			if(StringUtils.isNotEmty(historyPos.getEtime())){
				obj =  new BasicDBObject("$lte", historyPos.getEtime());
				qobject.put("createtime", obj);
			}
		}
		int skipResults = (currentPage - 1) * everyPage;//从这一行数据开始查询
		
		logger.info("开始查询："+DateUtil.getSQLDate());
		DBCursor cur1 = collection.find(qobject);
		int count = cur1.count();//总条数
		DBCursor cur = collection.find(qobject).sort(new BasicDBObject("createtime",-1)).skip(skipResults).limit(everyPage);
		int i=0;//为list集合添加唯一字段
		while (cur.hasNext()) {
			JSONObject jObject = JSONObject.fromObject(cur.next());
			try{
				HistoryPositionInfo tpi =  (HistoryPositionInfo)JSONObject.toBean(jObject, HistoryPositionInfo.class);
				tpi.setId(i);
				list.add(tpi);
				i++;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("list", list);
		map.put("totalCount", count);
		
		logger.info(list.size());
		logger.info("结束查询："+DateUtil.getSQLDate());
		return map;
	}
	/**
	 * 根据id删除车辆报警信息
	 * @param carid
	 */
	public static void deleteAlarmInfo(DBCollection collection,String carnumber,String createtime) {
		try{
			BasicDBObject qobject = new BasicDBObject();
			if(StringUtils.isNotEmty(carnumber)){
				qobject.put("carnumber",carnumber);
			}
			if(StringUtils.isNotEmty(createtime)){
				qobject.put("alarmtime",createtime);
			}
			logger.info("开始删除数据："+DateUtil.getSQLDate());
			collection.remove(qobject);
			logger.info("结束删除数据："+DateUtil.getSQLDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据车牌和gps时间删除车辆位置deleteCarhistory
	 */
	public static void deleteCarhistory(DBCollection collection,String carnumber,String createtime) {
		try{
			BasicDBObject qobject = new BasicDBObject();
			if(StringUtils.isNotEmty(carnumber)){
				qobject.put("carnumber",carnumber);
			}
			if(StringUtils.isNotEmty(createtime)){
				qobject.put("createtime",createtime);
			}
			logger.info("开始删除数据："+DateUtil.getSQLDate());
			collection.remove(qobject);
			logger.info("结束删除数据："+DateUtil.getSQLDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

	}


	
}
 