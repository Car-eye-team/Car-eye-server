package com.careye.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarCondition;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.car.service.CarConditionService;
import com.careye.car.service.CarService;
import com.careye.constant.ServiceConfig;
import com.careye.mq.domain.OperateData;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;
import com.careye.mongodb.MongoDB;

/**
 * 
 * @项目名称：CVP
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-10-20 下午01:42:21
 * @修改人：ll
 * @修改时间：2015-10-20 下午01:42:21
 * @修改备注：
 * @version 1.0
 */
public class CarConditionJob {
	
	protected static final Logger logger = Logger.getLogger(CarConditionJob.class);
	
	@SuppressWarnings("unchecked")
	public void startJob(){
		try{
			logger.info("车辆信息统计脚本启动.....时间："+DateUtil.getSQLDate());
			CarService carService = ServiceConfig.carService;
			CarConditionService carConditionService = ServiceConfig.carConditionService;
			List<CarInfo> list = carService.findCarInfo();
			if(list != null && list.size() > 0){
				for(CarInfo info : list){
//					if(info.getId() == 1425){
					logger.info("单个车辆信息统计开始——车牌号："+info.getCarnumber());
					CarCondition carCondition = new CarCondition();
					carCondition.setCarid(info.getId());
					//在线时长
					int carid = info.getId();
					String time = DateUtil.getYesterday();
//					String time = "2016-05-03";
					String stime = time+" 00:00:00";
					String etime = time+" 23:59:59";
					Map map = new HashMap<String, String>();
					map.put("stime", stime);
					map.put("etime", etime);
					map.put("carid", carid);
			
					List<PositionInfo> pInfoList = MongoDB.getInstance().queryPosition(carid,stime,etime);
					
					String comparestime = time +" 07:30:00";
					String compareetime = time +" 21:30:00";
					
					//在线时长
					Double inlinetime = 0.0;
					//载客时长
					Double passengertime = 0.0;
					//行驶里程
					Float drivermile = 0.0f;
					//载客里程
					Float passengermile = 0.0f;
					//7:30至21:30在线时长
					Double inlinetime1 = 0.0;
					//7:30至21:30载客时长
					Double passengertime1 = 0.0;
					//7:30至21:30行驶里程
					Float drivermile1 = 0.0f;
					//7:30至21:30载客里程
					Float passengermile1 = 0.0f;
					
					if(pInfoList != null && pInfoList.size() >= 2){
						for(int i=0; i<pInfoList.size()-1; i++){
							
							PositionInfo pInfo1 = pInfoList.get(i);
							PositionInfo pInfo2 = pInfoList.get(i+1);
							if(pInfo1.getCarstatus() != 1 && pInfo1.getCarstatus() !=2 
									&& pInfo2.getCarstatus() != 1 && pInfo2.getCarstatus() != 2){
								int ontime = (int)(DateUtil.dateDiff(pInfo2.getCreatetime(), pInfo1.getCreatetime())/1000);
								if(ontime <= 300){//两次上传数据的时间差超过300秒则表示离线，不统计其时长
									//在线时长
									inlinetime += ontime;
									Float drivermilediff = pInfo2.getSummileage()-pInfo1.getSummileage();
									//行驶里程
									drivermile += drivermilediff;
									//统计7:30至21:30数据
									if(DateUtil.dateDiff(pInfo1.getCreatetime(), comparestime)>0 &&
											DateUtil.dateDiff(pInfo1.getCreatetime(), compareetime)<0){
										inlinetime1 += ontime;
										//行驶里程
										drivermile1 += drivermilediff;
									}
									
									//载客时长
									if(pInfo1.getZkstate() != null && pInfo1.getZkstate() ==2 &&
											pInfo2.getZkstate() != null && pInfo2.getZkstate() ==2){
										//载客时长
										passengertime += ontime;
										//行驶里程
										passengermile += drivermilediff;
										
										//统计7:30至21:30数据
										if(DateUtil.dateDiff(pInfo1.getCreatetime(), comparestime)>0 &&
												DateUtil.dateDiff(pInfo1.getCreatetime(), compareetime)<0){
											//载客时长
											passengertime1 += ontime;
											//行驶里程
											passengermile1 += drivermilediff;
										}
									}
									
								}
							}
							
						}
					}
				   //空车时长
				   Double offlinetime = inlinetime - passengertime;
				   //空车里程
				   Float emptymile = drivermile - passengermile;
				   
				   carCondition.setInlinetime(StringUtils.getTwoDouble(inlinetime/3600));					
				   carCondition.setOfflinetime(StringUtils.getTwoDouble(offlinetime/3600));					
				   carCondition.setPassengertime(StringUtils.getTwoDouble(passengertime/3600));					
				   carCondition.setDrivermile(StringUtils.getTwoDouble(drivermile));
				   carCondition.setPassengermile(StringUtils.getTwoDouble(passengermile));
				   carCondition.setEmptymile(StringUtils.getTwoDouble(emptymile));
				   
				   carCondition.setPassengertime1(StringUtils.getTwoDouble(passengertime1/3600));					
				   carCondition.setPassengermile1(StringUtils.getTwoDouble(passengermile1/3600));					
				   carCondition.setDrivermile1(StringUtils.getTwoDouble(drivermile1));
				   carCondition.setDrivertime1(StringUtils.getTwoDouble(inlinetime1/3600));
				   
				   carCondition.setCreatetime(time+"23:59:00");
				   
				   //报警数
				   int alarmnumber = MongoDB.getInstance().queryYesterdayRemind(carid, stime, etime);
				   carCondition.setAlarmcount(alarmnumber);
				   
				   CarCondition cdata = ServiceConfig.carConditionService.queryCarConditionData(map);
				   carCondition.setIncome(cdata.getIncome());
				   carCondition.setWaittime(cdata.getWaittime());
				   carCondition.setFeetime(cdata.getFeetime());
				   carCondition.setPassengercount(cdata.getPassengercount());
				   
				   CarCondition oCarCondition = new CarCondition();
				   oCarCondition.setCarid(carid);
				   oCarCondition.setStime(stime);
				   oCarCondition.setEtime(etime);
				   CarCondition oldCarCondition = carConditionService.findCarCondition(oCarCondition);
				   //将车辆状况信息保存进入数据库
				   if(oldCarCondition == null){
					   carConditionService.saveCarCondition(carCondition);
				   }else{
					   carCondition.setId(oldCarCondition.getId());
					   carConditionService.updateCarCondition(carCondition);
				   }
				   
				   logger.info("单个车辆信息统计结束.....");
				}
			}
//			}
			logger.info("车辆信息统计脚本停止.....时间："+DateUtil.getSQLDate());
		}catch (Exception e) {
			logger.info("车辆信息统计脚本出错"+e);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new CarConditionJob().startJob();
	}
	
}
