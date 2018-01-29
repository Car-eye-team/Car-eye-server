/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dsdispatch.server;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.careye.dsdispatch.constant.ConfigProperties;
import com.careye.dsdispatch.constant.Constant;
import com.careye.dsdispatch.db.CarInfoDao;
import com.careye.dsdispatch.db.DispatchRuleDao;
import com.careye.dsdispatch.job.JobManager;
import com.careye.dsdispatch.mq.JmsDispatchReceiver;
import com.careye.dsdispatch.mq.JmsDispatchReceiverHeartbeat;
import com.careye.dsdispatch.redis.RedisManager;
import com.careye.dsdispatch.threadpool.ThreadPoolManager;
import com.careye.dsdispatch.utlis.DispatchUtil;
import com.careye.redis.domain.CarInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：StartDsDispatch    
 * 类描述：调度服务器    
 * 创建人：zr    
 * 创建时间：2015-5-19 下午05:52:45    
 * 修改人：zr    
 * 修改时间：2015-5-19 下午05:52:45    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class StartDsDispatchServer {

	private final static Logger logger = Logger.getLogger(StartDsDispatchServer.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StartDsDispatchServer dsDispatchServer = new StartDsDispatchServer();
		dsDispatchServer.startServer();
	}

	/**
	 * 启动服务
	 */
	public void startServer(){

		try {

			//启动log4j日志
			String proPath = "." + File.separator + "conf" + File.separator + "log4j.properties";
			PropertyConfigurator.configure(proPath);

			logger.info("开始启动StartDsDispatchServer服务......");

			logger.info("启动log4j日志服务......");

			//启动系统参数读取
			ConfigProperties configProperties = new ConfigProperties();
			configProperties.start();
			logger.info("启动系统参数读取服务......");

			logger.info("初始化系统线程池......");
			ThreadPoolManager.getInstance().initThreadPool();
			
			logger.info("初始化系统Redis缓存......");
			RedisManager.getInstance();

			logger.info("启动系统定时任务......");
			JobManager jobManager = new JobManager();
			jobManager.startJob();
			
			logger.info("启动MQ下行电召相关消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsDispatchReceiver jmsDispatchReceiver = new JmsDispatchReceiver();
				jmsDispatchReceiver.start();
			}

			logger.info("启动MQ下行终端设备心跳消息读取服务......");
			for (int i = 0; i < 5; i++) {
				JmsDispatchReceiverHeartbeat jmsDispatchReceiverHeartbeat = new JmsDispatchReceiverHeartbeat();
				jmsDispatchReceiverHeartbeat.start();
			}

			//初始化参数
			logger.info("初始化参数......");
			init();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		//List<CarInfo> list = DispatchUtil.searchTerMap(113885774, 22569637, 5000);


	}

	/**
	 * 初始化参数
	 */
	public void init(){

		try {

			//初始化经度数组
			String leftlngarr[] = ConfigProperties.LEFT_LNG.split(",");
			String rightlngarr[] = ConfigProperties.RIGHT_LNG.split(",");
			int size = leftlngarr.length;
			for(int i=0;i<size;i++){
				Constant.LNG_ARRAY = DispatchUtil.cityGridArray(Double.parseDouble(leftlngarr[i]), Double.parseDouble(rightlngarr[i]),Constant.LNG_ARRAY);
			}

			//初始化纬度数组
			String leftlatarr[] = ConfigProperties.LEFT_LAT.split(",");
			String rightlatarr[] = ConfigProperties.RIGHT_LAT.split(",");
			int size1 = leftlatarr.length;
			for(int i=0;i<size1;i++){
				Constant.LAT_ARRAY = DispatchUtil.cityGridArray(Double.parseDouble(leftlatarr[i]), Double.parseDouble(rightlatarr[i]),Constant.LAT_ARRAY);
			}
			
			/*String stime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // 获取系统当前时间
			int[][] lnglatarray = DispatchUtil.cityGrid2dArray(Constant.LNG_ARRAY, Constant.LAT_ARRAY);
			String etime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calend
			ar.getInstance().getTime()); // 获取系统当前时间
			logger.info("============:"+DateUtil.dateDiffStr(stime1, etime1));*/
			
			logger.info("初始化创建系统终端设备map并存入redis缓存中......");
			
			//根据经纬度数组，初始化创建系统map并存入redis缓存中
			boolean re = DispatchUtil.initSystemTerMap(Constant.LNG_ARRAY.length,Constant.LAT_ARRAY.length);
			if(re){
				logger.info("初始化创建系统终端设备map并存入redis缓存中成功");
			}else{
				logger.info("初始化创建系统终端设备map并存入redis缓存中失败");
			}

			logger.info("初始化创建车辆信息map,加入至redis缓存中......");
			List<CarInfo> list = CarInfoDao.getCarInfoList();
			for (CarInfo carInfo : list) {
				//加入至redis缓存中
				RedisManager.getInstance().setObject(Constant.CARINFO_REDIS_NAME+carInfo.getTerminal(), carInfo);
				/*CarInfo cInfo = (CarInfo) RedisManager.getInstance().getObject("carinfo_18668171282");
				System.out.println("===================="+cInfo.getCarnumber());*/
			}	

			logger.info("初始化调度规则,加入至redis缓存中......");
			DispatchRuleDao.getNewDispatchRule();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化参数异常"+e);
		}

	}

}
