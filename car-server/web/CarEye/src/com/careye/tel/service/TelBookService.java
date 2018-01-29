/**
* Description: 多森商用车平台
* 文件名：CarService.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.service;

import java.util.List;
import java.util.Map;

import com.careye.tel.domain.TelBookCar;
import com.careye.tel.domain.TelBookSend;
import com.careye.tel.domain.TelBookSystem;


/**
 * @项目名称：FMS
 * @类名称：TelBookService
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-22 上午10:00:03
 * @修改人：lxh
 * @修改时间：2014-5-22 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public interface TelBookService {
	
	/**
	 * id查询系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public TelBookSystem selectTelBookSystemById(int id);
	
	/**
	 * id查询系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public int queryTelBookIsExist(TelBookSystem telBookSystem);
	
	/**
	 *通 过   车牌号   查询车辆电话本列表
	 * @param telBookSystem
	 * @return
	 */
	public List<TelBookCar> selectChecktelBookCarByCarNumber(TelBookCar telBookCar);
	
	/**
	 *通 过   ID   查询车辆电话本列表
	 * @param telBookSystem
	 * @return
	 */
	public TelBookCar selectTelBookCarById(int id);
	
	/**
	 * 删除系统电话本时查看车辆电话本中是否已存在
	 * @param telBookSystem
	 * @return
	 */
	public int queryTelBookCarIsExist(int telbookid);
	
	
	/**
	 * 更新电话本发送记录
	 * @param telBookSystem
	 * @return
	 */
	public int updateTelBookSend(TelBookCar telBookCar);
	
	/**
	 * 根据流水号更新时间处理结果
	 * @param telBookCar
	 * @return
	 */
	public int updateTelBookSendResult(TelBookSend telBookSend);
	
	/**
	 * 根据流水号修改车辆处理结果
	 * @param telBookCar
	 * @return
	 */
	public int updateTelBookCarResult(TelBookCar telBookCar);
	
	
	/**
	 * 添加电话本发送记录
	 * @param telBookSystem
	 * @return
	 */
	public int insertTelBookSend(TelBookCar telBookCar);
	
	/**
	 * 更新车辆电话本
	 * @param telBookSystem
	 * @return
	 */
	public int updateTelBookCar(TelBookCar telBookCar);
	
	
	/**
	 * 添加车辆电话本
	 * @param telBookSystem
	 * @return
	 */
	public int insertTelBookCar(TelBookCar telBookCar);
	

	
	/**
	 * 更新系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public int updateTelBookSystem(TelBookSystem telBookSystem);
	
	
	/**
	 * 删除系统电话本
	 * @param id
	 * @return
	 */
	public int deleteTelBookSystem(int id);
	
	/**
	 *   删除车辆电话本
	 * @param id
	 * @return
	 */
	public int deleteTelBookCar(TelBookCar telBookCar);
	
	/**
	 * 添加系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public int insertTelBookSystem(TelBookSystem telBookSystem);
	
	
	/**
	 * 条件查询系统电话本
	 * @param telBookSystem
	 * @return
	 */
	public Map selectCheckTelBookSystem(final int currentPage, final int everyPage,TelBookSystem telBookSystem);

	/**
	 * 查询电话本下发记录列表
	 * @param TelBookSend
	 * @return
	 */
	public Map selectCheckTelBookSend(final int currentPage, final int everyPage,TelBookSend telBookSend);
	/**
	 * 通 过   车牌号   查询车辆电话本列表
	 * @param TelBookCar
	 * @return
	 */
	public Map selectChecktelBookCarByCarNumber(final int currentPage, final int everyPage,TelBookCar telBookCar);
	
	
}
