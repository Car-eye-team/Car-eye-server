package com.careye.mq.business;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PositionInfo;
import com.careye.constant.ServiceConfig;
import com.careye.http.domain.BaiDuInfo;
import com.careye.mongodb.MongoDB;
import com.careye.mq.coder.ActivePush;
import com.careye.mq.domain.Protocol;
import com.careye.utils.DateUtil;

/**
 * @项目名称：TAXISERVER
 * @类名称：PositionInfoPro
 * @类描述：位置信息处理
 * @创建人：zr
 * @创建时间：2015-3-13 下午12:40:31
 * @修改人：zr
 * @修改时间：2015-3-13 下午12:40:31
 * @修改备注：
 * @version 1.0
 */
public class PositionInfoBusiness {

	private static Logger logger = Logger.getLogger(PositionInfoBusiness.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 操作最后信息
	 * @param gaoDeInfo
	 * @param protocol
	 * @param carInfo
	 * @param message
	 * @param dlng
	 * @param dlat
	 */
	public static void operPositionInfor(BaiDuInfo baiDuInfo,Protocol protocol,CarInfo carInfo,String message,double dlng,double dlat){

		try {
			PositionInfo positionInfo = new PositionInfo();
			//经纬度位置保留6位处理
			try {
				BigDecimal bglng = new BigDecimal(dlng);
				dlng = bglng.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

				BigDecimal bglat = new BigDecimal(dlat);
				dlat = bglat.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
			positionInfo.setSeq(protocol.getSeq());
			positionInfo.setDatapacket(message);
			positionInfo.setCarstatus(carInfo.getCarstatus());
			if(protocol.getS9() == 0){
				positionInfo.setZkstate(0);
			}else if(protocol.getS9() == 3){
				positionInfo.setZkstate(1);
			}
			
			positionInfo.setCarid(carInfo.getId());
			positionInfo.setBlocid(carInfo.getBlocid());
			positionInfo.setBlocname(carInfo.getBlocname());
			positionInfo.setTerminal(protocol.getTerminal());
			positionInfo.setCarnumber(carInfo.getCarnumber());
			positionInfo.setMsgid(protocol.getMsgid());
			positionInfo.setAcc(protocol.getAcc());
			positionInfo.setLng(dlng);
			positionInfo.setLat(dlat);
			positionInfo.setAltitude(protocol.getAltitude());
			//处理速度，保留两位小数点
			try {
				if(protocol.getSpeed()!=null){
					Double speed = Double.parseDouble(protocol.getSpeed());
					String speedstr = String.format("%.1f",speed);
					positionInfo.setSpeed(speedstr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String direction = "0";
			if(protocol.getDirection() == null || "".equals(protocol.getDirection())){
				direction = "0";
			}else{
				direction = protocol.getDirection();
			}
			positionInfo.setDirection(direction);

			//将GPS时间转换成时间格式
			try {
				positionInfo.setGpstime(DateUtil.numToDate(protocol.getTime()));
			} catch (Exception e) {
				e.printStackTrace();
				positionInfo.setGpstime(DateUtil.getSQLDate());
			}
			positionInfo.setCreatetime(DateUtil.getSQLDate());
			positionInfo.setGpsflag(protocol.getGpsflg());
			if(baiDuInfo != null){
				positionInfo.setProvince(baiDuInfo.getProvince());
				positionInfo.setCity(baiDuInfo.getCity());
				positionInfo.setDistrict(baiDuInfo.getDistrict());
				positionInfo.setBlng(baiDuInfo.getBlng());
				positionInfo.setBlat(baiDuInfo.getBlat());
				positionInfo.setAddress(baiDuInfo.getAddress());
			}

			float lastmileage = 0;
			//处理里程
			positionInfo.setMileage(protocol.getMileage());
			try {
				//获取车辆上一次里程
//				RedisManager redisManager = RedisManager.getInstance();
//				CarInfo nextCarInfo =  (CarInfo)redisManager.getObject("dstaxi_"+protocol.getTerminal());
				CarInfo nextCarInfo =  null;
				
				if(nextCarInfo!=null){
					if(nextCarInfo.getMileage() == null || "".equals(nextCarInfo.getMileage())){
						Float sm = ServiceConfig.positionInfoService.getTerminalmileage(protocol.getTerminal());
						if(sm == 0.0){
							lastmileage = protocol.getMileage();
						}else{
							lastmileage = sm;
						}
					}else{
						lastmileage = nextCarInfo.getMileage();
					}
				}else{
					Float sm = ServiceConfig.positionInfoService.getTerminalmileage(protocol.getTerminal());
					if(sm == 0.0){
						lastmileage = protocol.getMileage();
					}else{
						lastmileage = sm;
					}
				}

				if(nextCarInfo != null){
					nextCarInfo.setMileage(protocol.getMileage());
					//更新缓存中的里程为最新里程
//					redisManager.setObject("dstaxi_"+protocol.getTerminal(), nextCarInfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			positionInfo.setSummileage(protocol.getMileage());

			//当前日里程
			Float daymileage = 0f;
			try {
				daymileage = protocol.getMileage() - lastmileage;
				positionInfo.setDaymileage(daymileage);
				//logger.info("============当前里程==========="+daymileage);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//==============================推送实时位置给客户端======================================
			ActivePush.realTimePosition(positionInfo,carInfo);

			//根据设备号更新车辆最后位置信息表
			try {
				if(dlng ==0){
					positionInfo.setLng(null);
					positionInfo.setLat(null);
					positionInfo.setBlng(null);
					positionInfo.setBlat(null);
					positionInfo.setGlng(null);
					positionInfo.setGlat(null);
				}
				ServiceConfig.positionInfoService.updateTerminalPositionInfo(positionInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//操作历史位置表
			try {
				positionInfo.setMileage(daymileage);
				positionInfo.settName("TO_VEHICLE_POSITION_"+carInfo.getId());
				if(protocol.getLat() != 0 && protocol.getLng() != 0){
					ServiceConfig.positionInfoService.insertTerminalPositionInfo(positionInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//位置信息插入mongodb 
			try {
				if(positionInfo.getLng() == null || positionInfo.getLng() == 0){
					logger.info("==没有经纬度不插入mongo==");
				}else{
					logger.info("==插入位置信息至mongo开始==");
					MongoDB.getInstance().addPosition(positionInfo);
					logger.info("==插入位置信息至mongo结束==");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
