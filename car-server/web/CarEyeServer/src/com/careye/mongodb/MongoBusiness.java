/**
* Description: car-eye车辆管理平台
* 文件名：MongoDB.java
* 版本信息：1.0
* 日期：2015-6-10
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mongodb;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.PositionInfo;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @项目名称：TAXISERVER
 * @类名称：MongoDB
 * @类描述：
 * @创建人：wuyongde
 * @创建时间：2015-6-10 上午10:44:22
 * @修改人：wuyongde
 * @修改时间：2015-6-10 上午10:44:22
 * @修改备注：
 * @version 1.0
 */
public class MongoBusiness {
	
	protected static final Logger logger = Logger.getLogger(MongoBusiness.class);

	/**
	 * 插入位置信息至mongo
	 * @param positionInfo
	 */
	public static void addPosition(DBCollection collection,PositionInfo positionInfo) {
		
        DBObject position = new BasicDBObject();
        position.put("timestamp", DateUtil.getTimestamp());
        position.put("tName", positionInfo.gettName());
        position.put("blocid", positionInfo.getBlocid());
        position.put("blocname", positionInfo.getBlocname());
        position.put("carid", positionInfo.getCarid());
        position.put("terminal", positionInfo.getTerminal());
        position.put("carnumber", positionInfo.getCarnumber());
        position.put("msgid", positionInfo.getMsgid());
        position.put("acc", positionInfo.getAcc());
        position.put("lng", positionInfo.getLng());
        position.put("lat", positionInfo.getLat());
        position.put("altitude", positionInfo.getAltitude());
        position.put("speed", positionInfo.getSpeed());
        position.put("direction", positionInfo.getDirection());
        position.put("gpstime", positionInfo.getGpstime());
        position.put("gpsflag", positionInfo.getGpsflag());
        position.put("province", positionInfo.getProvince());
        position.put("city", positionInfo.getCity());
        position.put("district", positionInfo.getDistrict());
        position.put("blng", positionInfo.getBlng());
        position.put("blat", positionInfo.getBlat());
        position.put("address", positionInfo.getAddress());
        position.put("glng", positionInfo.getBlng());
        position.put("glat", positionInfo.getBlat());
        position.put("gaddress", positionInfo.getGaddress());
        position.put("mileage", positionInfo.getMileage());
        position.put("summileage", positionInfo.getSummileage());
        position.put("daymileage", positionInfo.getDaymileage());
        position.put("createtime", positionInfo.getCreatetime());
        position.put("seq", positionInfo.getSeq());
        position.put("sn", positionInfo.getSn());
        position.put("snvalue", positionInfo.getSnvalue());
        position.put("an", positionInfo.getAn());
        position.put("anvalue", positionInfo.getAnvalue());
        position.put("carstatus", positionInfo.getCarstatus());
        position.put("zkstate", positionInfo.getZkstate());
        
        collection.save(position);

    }
	
	/**
	 * 插入报警信息至mongo
	 * @param alarmInfo
	 */
	public static void addAlarm(DBCollection collection,Alarm alarmInfo) {
		
        DBObject alarm = new BasicDBObject();
        alarm.put("timestamp", DateUtil.getTimestamp());
        alarm.put("blocid", alarmInfo.getBlocid());
        alarm.put("blocname", alarmInfo.getBlocname());
        alarm.put("carid", alarmInfo.getCarid());
        alarm.put("terminal", alarmInfo.getTerminal());
        alarm.put("carnumber", alarmInfo.getCarnumber());
        alarm.put("lng", alarmInfo.getLng());
        alarm.put("lat", alarmInfo.getLat());
        alarm.put("lng", alarmInfo.getLng());
        alarm.put("blat", alarmInfo.getBlat());
        alarm.put("blng", alarmInfo.getBlng());
        alarm.put("altitude", alarmInfo.getAltitude());
        alarm.put("speed", alarmInfo.getSpeed());
        alarm.put("direction", alarmInfo.getDirection());
        alarm.put("gpstime", alarmInfo.getAlarmtime());
        alarm.put("province", alarmInfo.getProvince());
        alarm.put("city", alarmInfo.getCity());
        alarm.put("district", alarmInfo.getDistrict());
        alarm.put("blng", alarmInfo.getBlng());
        alarm.put("blat", alarmInfo.getBlat());
        alarm.put("address", alarmInfo.getAddress());
        alarm.put("rawdata", alarmInfo.getRawdata());
        alarm.put("alarmtime", alarmInfo.getAlarmtime());
        alarm.put("alarmkey", alarmInfo.getAlarmkey());
        alarm.put("alarmname", alarmInfo.getAlarmname());
        alarm.put("createtime", alarmInfo.getCreatetime());
        
        alarm.put("areaid", alarmInfo.getAreaid()==null?"":alarmInfo.getAreaid());
        alarm.put("alarmtype", alarmInfo.getAlarmtype());
        
        collection.save(alarm);

    }
	
	/**
	 * 分组查询车辆
	 * @param lng
	 * @param lat
	 * @param mile
	 * @param stime
	 * @param etime
	 * @return
	 */
	public static List<PositionInfo> queryPosition(DBCollection collection,int carid,String stime,String etime) {
		
		List<PositionInfo> list = new ArrayList<PositionInfo>();
		
	    BasicDBObject key = new BasicDBObject("carid",true);
		 //多条件查询
	   	BasicDBObject condobject = new BasicDBObject();
	   	condobject.put("carid",carid);
	   	condobject.put("createtime", new BasicDBObject("$gte", stime).append("$lte", etime));
	   
		logger.info("开始查询："+DateUtil.getSQLDate());
		DBCursor cur = collection.find(condobject).sort(new BasicDBObject("createtime",1));	    
		while (cur.hasNext()) {
			JSONObject jObject = JSONObject.fromObject(cur.next());
			PositionInfo positionInfo = new PositionInfo();
			positionInfo.setCarid(jObject.getInt("carid")) ;
			positionInfo.setZkstate(StringUtils.isNotEmty(jObject.getString("zkstate"))?0:1);
			positionInfo.setCarstatus(jObject.getInt("carstatus"));
			positionInfo.setSummileage(jObject.getString("summileage")==null?0.0f:Float.parseFloat(jObject.getString("summileage")));
			positionInfo.setCreatetime(jObject.getString("createtime"));
			
			list.add(positionInfo);
		}

	    logger.info(list.size());
	    logger.info("分组-结束查询："+DateUtil.getSQLDate());
	    return list;
    }
	
	/**
	 * 查询报警次数
	 * @param carid
	 * @param stime
	 * @param etime
	 * @return
	 */
	public static int queryYesterdayRemind(DBCollection collection2,Integer carid,String stime,String etime) {
		try{
			//多条件查询
			BasicDBObject qobject = new BasicDBObject();
			qobject.put("carid",carid);
			Object obj =  new BasicDBObject("$gte", stime).append("$lte", etime);
			qobject.put("createtime",obj);
			
			logger.info("开始查询："+DateUtil.getSQLDate());
			DBCursor cur = collection2.find(qobject);
			int count = cur.count();
			logger.info(count);
			logger.info("结束查询："+DateUtil.getSQLDate());
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
 