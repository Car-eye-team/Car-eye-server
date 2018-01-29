/**
* Description: car-eye车辆管理平台
* 文件名：MongoDB.java
* 版本信息：1.0
* 日期：2015-6-10
* Copyright car-eye 车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONObject;

import com.careye.common.domain.Alarm;
import com.careye.common.domain.HistoryPositionInfo;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.constant.ConfigProperties;
import com.careye.utils.DateUtil;
import com.careye.utils.GpsDistance;
import com.careye.utils.StringUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import common.Logger;

/**
 * @项目名称：car-eyeSERVER
 * @类名称：MongoDB
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2015-6-10 上午10:44:22
 * @修改人：zhangrong
 * @修改时间：2015-6-10 上午10:44:22
 * @修改备注：
 * @version 1.0
 */
public class MongoDB {
	
	private static final Logger logger = Logger.getLogger(MongoDB.class);

	private Mongo mg = null;
	private DB db;
    private DBCollection alarmcollection; //报警数据
    private DBCollection positioncollection;
    
	protected static MongoDB mongoDB = new MongoDB();
	
	public MongoDB(){
		init();
	}
	
	/**
	 * 获取唯一实例.
	 * @return
	 */
	public static MongoDB getInstance(){
		if (mongoDB == null) {
			mongoDB = new MongoDB();
		}
		return mongoDB;
	}
	
	/**
	 * 初始化连接mongodb
	 */
	public void init() {

        try {
            mg = new Mongo(ConfigProperties.MONGO_IP, Integer.parseInt(ConfigProperties.MONGO_PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //获取DB；如果默认没有创建，mongodb会自动创建
        db = mg.getDB(ConfigProperties.MONGO_DB);
        //获取DBCollection；如果默认没有创建，mongodb会自动创建
    	alarmcollection = db.getCollection(ConfigProperties.MONGO_ALARM_COLLECTION);
    	positioncollection = db.getCollection(ConfigProperties.MONGO_POSITION_COLLECTION);

    }
	
	/**
	 * 不分组查询返回唯一的车牌号列表
	 */
	public List<CarPosition> queryPosition(Double lng,Double lat,Double mile,String stime,String etime) {
		
		List<CarPosition> list = new ArrayList<CarPosition>();
    	CarPosition carPosition = new CarPosition();
    	Set<String> set = new TreeSet<String>();
		
		//偏移经度保留六位小数
		Double pylng = StringUtils.getSixFloat(GpsDistance.getLongt(lng, lat, mile));
		//偏移纬度保留六位小数
		Double pylat = StringUtils.getSixFloat(GpsDistance.getLat(lng, lat, mile));
		
    	//多条件查询
    	BasicDBObject qobject = new BasicDBObject();
    	qobject.put("glng", new BasicDBObject("$gte", lng-pylng).append("$lte", lng+pylng));
    	qobject.put("glat", new BasicDBObject("$gte", lat-pylat).append("$lte", lat+pylat));
    	qobject.put("gpstime", new BasicDBObject("$gte", stime).append("$lte", etime));
    	
    	//查询指定字段
    	BasicDBObject field  = new BasicDBObject();
    	field.put("carnumber", true);
    	field.put("terminal", true);
    	
    	logger.info("不分组-开始查询："+DateUtil.getSQLDate());
    	DBCursor cur = positioncollection.find(qobject,field).sort(new BasicDBObject("gpstime",-1));
    	logger.info("查询结果数量："+cur.count());
    	while (cur.hasNext()) {
        	JSONObject jObject = JSONObject.fromObject(cur.next());
        	carPosition =  (CarPosition)JSONObject.toBean(jObject, CarPosition.class);
        	boolean bl = set.contains(carPosition.getCarnumber());
        	if(!bl){
        		list.add(carPosition);
        		set.add(carPosition.getCarnumber());
        	}
        	
        }
    	logger.info(list.size());
    	logger.info("不分组-结束查询："+DateUtil.getSQLDate());
    	return list;
    	
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
	public List<Integer> queryPositionGroup(String uplng1,String uplat1,String uplng2,String uplat2
		,String downlng1,String downlat1,String downlng2,String downlat2,String stime1,String etime1,String stime2,String etime2) {
		
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		
		double duplng1 = Double.parseDouble(uplng1);
		double duplat1 = Double.parseDouble(uplat1);
		double duplng2 = Double.parseDouble(uplng2);
		double duplat2 = Double.parseDouble(uplat2);
		double ddownlng1 = Double.parseDouble(downlng1);
		double ddownlat1 = Double.parseDouble(downlat1);
		double ddownlng2 = Double.parseDouble(downlng2);
		double ddownlat2 = Double.parseDouble(downlat2);
		
	    BasicDBObject key = new BasicDBObject("carid",true);
		 //多条件查询
	   	BasicDBObject condobject = new BasicDBObject();
	   	if(duplng1 < duplng2){
	   		condobject.put("blng", new BasicDBObject("$gte", duplng1).append("$lte", duplng2));
	   	}else{
	   		condobject.put("blng", new BasicDBObject("$gte", duplng2).append("$lte", duplng1));
	   	}
	   	if(duplat1 < duplat2){
	   		condobject.put("blat", new BasicDBObject("$gte", duplat1).append("$lte", duplat2));
	   	}else{
	   		condobject.put("blat", new BasicDBObject("$gte", duplat2).append("$lte", duplat1));
	   	}
	   	condobject.put("createtime", new BasicDBObject("$gte", stime1).append("$lte", etime1));
	   
	    BasicDBObject initial = new BasicDBObject("count",0);
	    String reduce = "function(obj,pre){pre.count++}";
	    
	    logger.info("分组-开始查询："+DateUtil.getSQLDate());
	    BasicDBList returnList = (BasicDBList)positioncollection.group(key, condobject, initial, reduce);
	    ListIterator<Object> it = returnList.listIterator();
	    while(it.hasNext()){
	    	JSONObject jObject = JSONObject.fromObject(it.next());
	    	Integer carid = jObject.getInt("carid");
	    	list1.add(carid);
	    }
	    
	    //多条件查询
	   	BasicDBObject condobject2 = new BasicDBObject();
	   	if(duplng1 < duplng2){
	   		condobject2.put("blng", new BasicDBObject("$gte", ddownlng1).append("$lte", ddownlng2));
	   	}else{
	   		condobject2.put("blng", new BasicDBObject("$gte", ddownlng2).append("$lte", ddownlng1));
	   	}
	   	if(duplat1 < duplat2){
	   		condobject2.put("blat", new BasicDBObject("$gte", ddownlat1).append("$lte", ddownlat2));
	   	}else{
	   		condobject2.put("blat", new BasicDBObject("$gte", ddownlat2).append("$lte", ddownlat1));
	   	}
	   	condobject2.put("createtime", new BasicDBObject("$gte", stime2).append("$lte", etime2));
	   
	    BasicDBList returnList2 = (BasicDBList)positioncollection.group(key, condobject2, initial, reduce);
	    ListIterator<Object> it2 = returnList2.listIterator();
	    while(it2.hasNext()){
	    	JSONObject jObject = JSONObject.fromObject(it2.next());
	    	Integer carid = jObject.getInt("carid");
	    	list2.add(carid);
	    }
	    
	    for(Integer info1 : list1){
	    	for(Integer info2 : list2){
	    		if(info1.intValue() == info2.intValue()){
	    			list.add(info1);
	    		}
	    	}
	    }
	    logger.info(list.size());
	    logger.info("分组-结束查询："+DateUtil.getSQLDate());
	    return list;
    }
	
	/**
	 * 查询符号条件的位置信息
	 */
	public List<HistoryPosition> queryHistoryPosition(Double lng,Double lat,Double mile,String stime,String etime) {
		
		List<HistoryPosition> list = new ArrayList<HistoryPosition>();
		HistoryPosition historyPosition = new HistoryPosition();
		
		//偏移经度保留六位小数
		Double pylng = StringUtils.getSixFloat(GpsDistance.getLongt(lng, lat, mile));
		//偏移纬度保留六位小数
		Double pylat = StringUtils.getSixFloat(GpsDistance.getLat(lng, lat, mile));
		
		//多条件查询
		BasicDBObject qobject = new BasicDBObject();
		qobject.put("glng", new BasicDBObject("$gte", lng-pylng).append("$lte", lng+pylng));
		qobject.put("glat", new BasicDBObject("$gte", lat-pylat).append("$lte", lat+pylat));
		qobject.put("gpstime", new BasicDBObject("$gte", stime).append("$lte", etime));
		
		//查询指定字段
		BasicDBObject field  = new BasicDBObject();
		field.put("carnumber", true);
		field.put("terminal", true);
		field.put("carstatus", true);
		field.put("glng", true);
		field.put("glat", true);
		field.put("gpstime", true);
		field.put("gaddress", true);
		
		logger.info("开始查询："+DateUtil.getSQLDate());
		DBCursor cur = positioncollection.find(qobject,field).sort(new BasicDBObject("gpstime",-1));
		while (cur.hasNext()) {
			JSONObject jObject = JSONObject.fromObject(cur.next());
			try{
				historyPosition =  (HistoryPosition)JSONObject.toBean(jObject, HistoryPosition.class);
				list.add(historyPosition);
			}catch (Exception e) {
				
			}
		}
		logger.info(list.size());
		logger.info("结束查询："+DateUtil.getSQLDate());
		return list;
		
	}
	
	/**
	 * 查询历史报警信息
	 */
	public Map getAlarmList(int currentPage,int everyPage,Alarm alarm) {
		return  MongoBusiness.getAlarmList(alarmcollection,currentPage,everyPage,alarm);
	}
	/**
	 * 查询历史位置信息
	 */
	public Map getCarhistoryList(int currentPage,int everyPage,HistoryPositionInfo historyPos) {
		return  MongoBusiness.getCarhistoryList(positioncollection,currentPage,everyPage,historyPos);
	}
	
	
	public static void main(String[] args) {
//		MongoDB.getInstance().queryPosition(113.869231, 22.55327, 12000.0, "2015-06-09 10:00:00", "2015-06-09 10:00:32");
//		MongoDB.getInstance().queryPositionGroup(113.869231, 22.55327, 12000.0, "2015-06-09 10:00:00", "2015-06-09 10:00:32");
//		MongoDB.getInstance().queryHistoryPosition(113.869231, 22.55327, 120000.0, "2015-06-10 10:00:32", "2015-06-10 12:55:32");
	}
	
	/**
	 * 根据id删除车辆报警信息
	 */
	public void deleteAlarmInfo(String carnumber,String createtime) {
		
		MongoBusiness.deleteAlarmInfo(alarmcollection,carnumber,createtime);
	}
	
	/**
	 * 根据车牌和gps时间删除车辆位置deleteCarhistory
	 */
	public void deleteCarhistory(String carnumber,String createtime) {
		
		MongoBusiness.deleteCarhistory(positioncollection,carnumber,createtime);
	}
}
 