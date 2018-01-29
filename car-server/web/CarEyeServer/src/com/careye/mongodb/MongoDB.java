/**
* Description: car-eye车辆管理平台
* 文件名：MongoDB.java
* 版本信息：1.0
* 日期：2015-6-10
* Copyright car-eye车辆管理平台 Copyright (c) 2015
* 版权所有
*/
package com.careye.mongodb;

import java.net.UnknownHostException;
import java.util.List;


import org.apache.log4j.Logger;

import com.careye.car.domain.Alarm;
import com.careye.car.domain.PositionInfo;
import com.careye.constant.ConfigProperties;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

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
public class MongoDB {
	
	protected static final Logger logger = Logger.getLogger(MongoDB.class);

	private Mongo mg = null;
	private DB db;
    private DBCollection postiontable;
    private DBCollection alarmtable;
    
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
        postiontable = db.getCollection(ConfigProperties.MONGO_POSITION_COLLECTION);
        alarmtable = db.getCollection(ConfigProperties.MONGO_ALARM_COLLECTION);

    }
	
	/**
	 * 插入位置信息至mongo
	 * @param positionInfo
	 */
	public void addPosition(PositionInfo positionInfo) {
		MongoBusiness.addPosition(postiontable, positionInfo);
    }
	
	/**
	 * 插入报警信息至mongo
	 * @param alarmInfo
	 */
	public void addAlarm(Alarm alarmInfo) {
		MongoBusiness.addAlarm(alarmtable, alarmInfo);
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
	public List<PositionInfo> queryPosition(int carid,String stime,String etime) {
		return MongoBusiness.queryPosition(postiontable, carid, stime, etime);
    }
	
	/**
	 * 查询报警次数
	 */
	public int queryYesterdayRemind(int carid,String stime,String etime) {
		return MongoBusiness.queryYesterdayRemind(alarmtable, carid, stime, etime);
	}
	
	
	
}
 