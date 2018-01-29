/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

 
package com.careye.dsdispatch.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.careye.dsdispatch.utlis.ExceptionUtil;
import com.careye.redis.domain.CarInfo;

/**    
 *     
 * 项目名称：dsdispatch    
 * 类名称：CarInfoDao    
 * 类描述： 车辆信息DAO  
 * 创建人：zr    
 * 创建时间：2015-5-21 上午09:34:19    
 * 修改人：zr    
 * 修改时间：2015-5-21 上午09:34:19    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class CarInfoDao {

	private final static Logger logger = Logger.getLogger(CarInfoDao.class);

	/**
	 * 根据车牌号获取车辆信息
	 * @param terminal
	 * @return
	 */
	public static CarInfo getCarInfo(String carnumber){

		try {
			CarInfo carInfo = new CarInfo();
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;  
			try {
				conn = TaxiConnectionManager.getInstance().getConnection();
				String sql = "select t.id carid,t.terminal,t.carnumber,tdt.usertype devicetype from to_car_info t,to_car_device_type tdt where t.devicetype = tdt.typeid and t.carnumber = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, carnumber);
				rs = pst.executeQuery();

				while(rs.next()){
					try {
						carInfo.setCarid(rs.getInt("carid"));
						carInfo.setTerminal(rs.getString("terminal"));
						carInfo.setCarnumber(rs.getString("carnumber"));
						carInfo.setDevicetype(rs.getInt("devicetype"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				rs.close();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {   
				try {
					conn.close();
					// 释放
					pst = null;
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
					ExceptionUtil.getInfo(e);
				}
			}

			return carInfo;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取车辆信息
	 * @return
	 */
	public static List<CarInfo> getCarInfoList(){

		try {

			List<CarInfo> list = new ArrayList<CarInfo>();
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;  
			try {
				conn = TaxiConnectionManager.getInstance().getConnection();
				String sql = "select t.id carid,t.terminal,t.carnumber,tdt.usertype devicetype from to_car_info t,to_car_device_type tdt where t.devicetype = tdt.typeid";
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();

				while(rs.next()){
					try {
						CarInfo carInfo = new CarInfo();
						carInfo.setCarid(rs.getInt("carid"));
						carInfo.setTerminal(rs.getString("terminal"));
						carInfo.setCarnumber(rs.getString("carnumber"));
						carInfo.setDevicetype(rs.getInt("devicetype"));
						list.add(carInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				rs.close();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {   
				try {
					conn.close();
					// 释放
					pst = null;
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
					ExceptionUtil.getInfo(e);
				}
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
