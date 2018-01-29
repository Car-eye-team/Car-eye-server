/**
* Description: 多森56taxi物流平台
* 文件名：CarServiceImpl.java
* 版本信息：1.0
* 日期：2014-5-22
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarDeviceDetail;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarDriverInfo;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.CarPosSuffix;
import com.careye.car.domain.CarStatus;
import com.careye.car.domain.CarnumberInfo;
import com.careye.car.domain.ControlRecord;
import com.careye.car.domain.Drivercode;
import com.careye.car.domain.OperateCertificate;
import com.careye.car.service.CarService;
import com.careye.common.domain.CarDetail;
import com.careye.common.domain.CarState;
import com.careye.common.domain.TerminalPositionInfo;
import com.careye.common.service.SysOperateLogService;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.dssservice.domain.TerminalAuth;
import com.careye.dssservice.service.DssService;
import com.careye.mq.HandleUtil;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.domain.CarUse;
import com.careye.sysset.domain.DeviceType;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

/**
 * @项目名称：FMS
 * @类名称：CarServiceImpl
 * @类描述：
 * @创建人：zr
 * @创建时间：2014-5-22 上午10:05:25
 * @修改人：zr
 * @修改时间：2014-5-22 上午10:05:25
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class CarServiceImpl extends GenericService implements CarService{
	
	private TerminalDeviceInfoService terminalDeviceInfoService;
	private DssService dssService;
	private SysOperateLogService  logService;

	/**
	 * 当班司机列表
	 * @param 
	 * @return
	 */
	public List<Drivercode> getDrivercodeList(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.getDrivercodeList",carInfo);
	}
	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarList(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.selectCarList",carInfo);
	}
	/**
	 * 车辆列表,取出集团
	 * @param 
	 * @return
	 */
	public List<CarnumberInfo> queryCarList(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.queryCarList",carInfo);
	}

	/**
	 * 雅讯车辆列表
	 * @param 
	 * @return
	 */
	public List<CarnumberInfo> queryYxCarList(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.queryYxCarList",carInfo);
	}
	
	/**
	 * 车辆下拉列表
	 * @param 
	 * @return
	 */
	public List<CarInfo> selectCarListByCheck(CarInfo carInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.selectCarListByCheck",carInfo);
	}
	
	
	/**
	 * 根据终端号码获取车辆信息
	 * @param terminal
	 * @return
	 */
	@Override
	public CarInfo getCarInfo(String terminal) {
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfo", terminal);
	}
	
	/**
	 * 根据终端号码获取车辆信息id
	 * @param terminal
	 * @return
	 */
	public Integer getCaridByTerminal(String terminal){
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getCaridByTerminal", terminal);
	}
	/**
	 * 根据carid获取用户类型
	 * @param terminal
	 * @return
	 */
	public Integer getUserTypeByCarid(int carid){
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getUserTypeByCarid", carid);
	}
	
	/**
	 * 根据车牌号查询车辆信息
	 * @param terminal
	 * @return
	 */
	@Override
	public CarInfo getCarInfoByCarNumber(String carnumber) {
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoByCarNumber", carnumber);
	}

	/**
	 * 根据车牌号更新车辆当前状态
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	@Override
	public int updateCarState(CarInfo carInfo) {
		return this.baseDao.update("oracle-carInfoSQL.updateCarState", carInfo);
	}

	/**
	 * 根据车辆ID获取车辆信息
	 * @param carid
	 * @return
	 */
	@Override
	public CarInfo getCarInfoCarId(int carid) {
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoCarId", carid);
	}

	/**
	 * 查询车牌号信息
	 * @return
	 */
	@Override
	public List<CarInfo> selectCarNumber(CarInfo carInfo) {
		return this.baseDao.queryForList("oracle-carInfoSQL.selectCarNumber", carInfo);
	}
	
	/**
	 * 终端号码是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	@Override
	public int queryTerminalIsExist(CarInfo carInfo) {
		return (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.queryTerminalIsExist", carInfo);
	}
	
	/**
	 * 验证手机卡号是否存在
	 * @param carInfo
	 * @return
	 */
	public int queryPhoneIsExist(CarInfo carInfo){
		return (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.queryPhoneIsExist", carInfo);
	}
	
	
	/**
	 * 车牌号是否在已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	@Override
	public int queryCarNumberIsExist(CarInfo carInfo) {
		return (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.queryCarNumberIsExist", carInfo);
	}/**
	 * 设备号是否已存在
	 * @param carnumber
	 * @param carstatus
	 * @return
	 */
	@Override
	public int queryDevicenumberIsExist(CarInfo carInfo) {
		return (Integer)this.baseDao.queryForObject("oracle-carInfoSQL.queryDevicenumberIsExist", carInfo);
	}
	
	/**
	 * 删除车辆信息
	 * @param id
	 * @return
	 */
	//@Log(operationType="delete操作:",operationName="删除车辆信息")
	@Override
	public int deleteCarInfo(int id) {
		
		//根据删除车辆最后位置表数据
		CarInfo carInfo = getCarInfoCarId(id);
		
		Integer count = this.baseDao.delete("oracle-carInfoSQL.deleteCarInfo", id);
		
		deleteCarProperty(id+"");//删除车辆信息后将车辆属性删除
		deleteOperateCertificate(id+"");//删除车辆信息后将营运证信息删除
		
		//删除成功后更新车辆所在组织机构车辆统计总数
//		updateDeptTotal(carInfo.getBlocid());
		
		//删除鉴权信息
		TerminalAuth tAuth = new TerminalAuth();
		tAuth.setCarTel(carInfo.getTerminal());
		tAuth.setUserType(carInfo.getUsertype());
		dssService.deleteTerminalAuth(tAuth);
		//删除位置表
		this.baseDao.delete("oracle-carInfoSQL.deleteCarPosTable","TO_VEHICLE_POSITION_"+id);
		//删除历史位置记录
		this.baseDao.delete("oracle-carInfoSQL.deleteCarPosition",id);
		//删除统计数据
		this.baseDao.delete("oracle-carInfoSQL.deleteCarStatistic",id);
		
		logService.addLogInfo(SessionUtils.getUserId(), "删除车辆记录:车牌号:"+carInfo.getCarnumber()+",车辆ID:"+carInfo.getId(),3);
		
		return count;
	}

	/**
	 * 添加车辆信息
	 * @param carInfo
	 * @return
	 */
	//@Log(operationType="add操作:",operationName=" 添加车辆信息")
	@Override
	public int insertCarInfo(CarInfo carInfo) {
		carInfo.setCreatetime(DateUtil.getSQLDate());
		Integer count = this.baseDao.save("oracle-carInfoSQL.insertCarInfo", carInfo);
		logService.addLogInfo(SessionUtils.getUserId(), "添加车辆记录:车牌号:"+carInfo.getCarnumber()+",车辆ID:"+count,1);
		return count;
	}
	
	/**
	 * 添加车辆状态
	 * @param carInfo
	 * @return
	 */
	@Override
	public int insertCarState(CarState carState){
		Integer count = this.baseDao.save("oracle-carInfoSQL.insertCarState", carState);
		return count;
	}

     /**
	 * 查询车辆信息
	 * @param carInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckCarInfo(int currentPage, int everyPage,
			CarInfo carInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-carInfoSQL.selectCheckCarInfo",
				"oracle-carInfoSQL.selectCarInfo", carInfo,currentPage,everyPage);
	}
	/**
	 * 查询车辆信息部分信息
	 * @param carInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map queySomeCarInfo(int currentPage, int everyPage,
			CarInfo carInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-carInfoSQL.queySomeCarInfo",
				"oracle-carInfoSQL.queySomeCarInfoCount", carInfo,currentPage,everyPage);
	}
	@Override
	public Map queryLedCarList(int currentPage, int everyPage,
			CarInfo carInfo) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-carInfoSQL.queryLedCarList",
				"oracle-carInfoSQL.queryLedCarListCount", carInfo,currentPage,everyPage);
	}
	
	/**
	 * 更新车辆信息
	 * @param carInfo
	 * @return
	 */
	//@Log(operationType="update操作:",operationName="更新车辆信息")
	@Override
	public int updateCarInfo(CarInfo carInfo) {
		// TODO Auto-generated method stub
		logService.addLogInfo(SessionUtils.getUserId(), "修改车辆记录:车牌号:"+carInfo.getCarnumber()+",车辆ID:"+carInfo.getId(),2);
		return this.baseDao.update("oracle-carInfoSQL.updateCarInfo", carInfo);
	}


	/**
	 * 根据车牌号获取车辆信息
	 * @param terminal
	 * @return
	 */
	@Override
	public CarInfo getCarInfoCarnumber(String carnumber) {
		return (CarInfo) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoCarnumber", carnumber);
	}

	/**
	 * 插入车辆，位置信息后缀表记录
	 * @param carPosSuffix
	 * @return
	 */
	@Override
	public Integer insertCarPosSuffix(CarPosSuffix carPosSuffix) {
		return this.baseDao.save("oracle-carInfoSQL.insertCarPosSuffix", carPosSuffix);
	}


	/**
	 * 根据车辆ID修改 车辆，位置信息后缀表记录状态
	 * @param carid
	 * @return
	 */
	@Override
	public Integer updateCarPosSuffixState(int carid) {
		return this.baseDao.update("oracle-carInfoSQL.updateCarPosSuffixState", carid);
	}

	/**
	 * 根据车辆ID得到车辆位置表后缀名
	 * @param carid
	 * @return
	 */
	public String getSuffix(int carid){
		return (String)this.baseDao.queryForObject("oracle-carInfoSQL.getSuffix", carid);
	}


	/**
	 * 导出车辆信息记录
	 * @return
	 */
	public List<CarInfo> exportCarInfo (CarInfo carInfo){
		logService.addLogInfo(SessionUtils.getUserId(), "导出车辆记录",4);
		return this.baseDao.queryForList("oracle-carInfoSQL.selectCheckCarInfo",carInfo);
	}
	
	/**
	 * 保存车辆信息（此方法为一个业务逻辑）
	 * @param carInfo
	 * @return
	 */
	public Integer saveCarInfo(CarInfo carInfo) {
		Integer su;
		Integer opetype = 1; //1添加，2修改
		int iscarnumber = this.queryCarNumberIsExist(carInfo);
		int terminal = this.queryTerminalIsExist(carInfo);
		int phone = this.queryPhoneIsExist(carInfo);
		if(carInfo.getUserid() == null){
			carInfo.setUserid(SessionUtils.getUserId());
		}
		
		if (carInfo.getTerminal() == carInfo.getCarnumber()) {
			su = -5;
		} else if (iscarnumber > 0) {
			su = -2;
		} else if (terminal > 0) {
			su = -4;
		}else if (phone > 0) {
			su = -3;
		} else {
			
			//查询密码没有时取设备号后6位
			if(StringUtils.isEmty(carInfo.getPassword())){
				if(carInfo.getTerminal().length() < 6){
					carInfo.setPassword(carInfo.getTerminal());
				}else{
					carInfo.setPassword(carInfo.getTerminal().substring(carInfo.getTerminal().length()-6));
				}
			}
			
			if (carInfo.getId() == null) {																
				carInfo.setUserid(SessionUtils.getUserId());
				if(StringUtils.isEmty(carInfo.getInstalltime())){
					carInfo.setInstalltime(DateUtil.getToday());
				}
				int count = this.insertCarInfo(carInfo);
				carInfo.setCarid(this.getNewCarid());
				this.insertCarProperty(carInfo);

				if (count <= 0) {
					su = -1;
				} else {
					
					// 动态创建车辆位置信息表
					Map<String,String> map = new HashMap<String,String>();
					map.put("tableName", "TO_VEHICLE_POSITION_"+ count);
					map.put("ACC", "TO_VE_PO_INDEX_"+ count+"_ACC");
					map.put("CARNUMBE", "TO_VE_PO_INDEX_"+ count+"_CARNUMBE");
					map.put("CARSTATUS", "TO_VE_PO_INDEX_"+ count+"_CARSTATUS");
					map.put("CREATETIME", "TO_VE_PO_INDEX_"+ count+"_CREATETIME");
					map.put("GPSFLAG", "TO_VE_PO_INDEX_"+ count+"_GPSFLAG");
					map.put("TERMINAL", "TO_VE_PO_INDEX_"+ count+"_TERMINAL");
					map.put("ZKSTATE", "TO_VE_PO_INDEX_"+ count+"_ZKSTATE");
					terminalDeviceInfoService.createCarPosTable(map);


					// 插入车辆最后位置信息
					TerminalPositionInfo tp = new TerminalPositionInfo();
					tp.setCarid(count);
					
					tp.setAddress("广东省深圳市宝安区玉律路48");
					tp.setTerminal(carInfo.getTerminal());
					tp.setCreatetime(DateUtil.getSQLDate());
					tp.setLng(113.874548);
					tp.setLat(22.566461);
					tp.setBlat(22.569773);
					tp.setBlng(113.885890);
					tp.setGlat(22.566461);
					tp.setGlng(113.874548);
					tp.setProvince("广东省");
					tp.setCity("深圳市");
					tp.setDistrict("宝安区");
					tp.setDirection("0");
					tp.setSpeed("0");
					tp.setCarnumber(carInfo.getCarnumber());
					terminalDeviceInfoService.addTerminalPositionInfo(tp);
					
					//插入车辆状态
					CarState carState = new CarState();
					carState.setCarid(count);
					carState.setCarstatus(1);
					carState.setKzstate(1);
					insertCarState(carState);
					
					su = 1;
				}
			} else {
				int re = this.updateCarInfo(carInfo);

				// 根据车辆ID更新最后位置表设备号、车牌号
//				terminalDeviceInfoService.updateTerminalPosition(carInfo);
				if (re <= 0) {
					su = -1;
				} else {
					su = 1;
					opetype = 2;
				}
				
			}

			// 操作鉴权表
			TerminalAuth terminalAuth = new TerminalAuth();
			terminalAuth.setCarTel(carInfo.getTerminal());
			int type = 29;
			if (carInfo.getDevicetype() != null) {
				//根据设备类型获取用户类型
				try {
					type = this.getUserType(carInfo.getDevicetype());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				type = 29;
			}
			terminalAuth.setUserType(type);

			int flag = dssService.getTerminalAuth(terminalAuth);
			if (flag > 0) {
				dssService.deleteTerminalAuth(terminalAuth);
			}
			dssService.insertTerminalAuth(terminalAuth);
			this.updateCarProperty(carInfo);
			//操作车辆通知通讯平台
//			HandleUtil.operateCar(type,carInfo.getTerminal(), opetype, HandleUtil.getSerialId(), carInfo.getCarnumber());
			
		}
		return su;
	}
	
	/**
	 * 批量转移车辆
	 * @param map
	 * deptlist转移之前的deptid 
	 * @return 
	 */
	public String updateCarDept(String deptid,String ids){
		List<Integer> list = new ArrayList<Integer>();  
		List<String> deptlist = new ArrayList<String>();  
	    for(String str : ids.split(",")){  
	    	if(!str.equals("root") && (str.indexOf("C")==0)){
	    		list.add(Integer.parseInt(str.substring(1)));  
	    		Integer did = getCarInfoCarId(Integer.parseInt(str.substring(1))).getBlocid();
	    		if(!deptlist.contains(deptid)){
	    			deptlist.add(did.toString());  
	    		}
	    	}
		}  
	    Map map = new HashMap();
	    map.put("blocid", deptid);
	    map.put("list",list);
	    
		Integer count = this.baseDao.update("oracle-carInfoSQL.updateCarDept", map);
		logService.addLogInfo(SessionUtils.getUserId(), "转移车辆记录到集团id为"+deptid+"下，被转移的车辆id为"+list.toString(),2);
		//更新转移之后的车辆所属组织机构
		if(!deptlist.contains(deptid)){
			deptlist.add(deptid);
		}
		
	    return StringUtils.listToString(deptlist);
	}
	
	/**
	 * 根据设备类型获取用户类型
	 */
	@Override
	public int getUserType(int devicetype) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getUserType", devicetype);
	}

	/**
	 * 根据设备号获取用户类型
	 * @param devicetype
	 * @return
	 */
	public int getUserTypeByTerminal(String terminal){
		Object obj = this.baseDao.queryForObject("oracle-carInfoSQL.getUserTypeByTerminal", terminal);
		if(obj != null){
			return (Integer)obj;
		}
		return -1;
		
	}
	
	/**
	 * 根据组织机构更新对应组织机构车辆总数
	 * @param devicetype
	 * @return
	 */
	public int updateDeptTotal(int deptid){
		
		//先统计上级所有的组织机构id
		List<Integer> deptidlist = this.baseDao.queryForList("oracle-carInfoSQL.getUpDeptlist", deptid);
		
		//重新统计组织机构下面的车辆数
		for(int i=0;i<deptidlist.size();i++){
			this.baseDao.update("oracle-carInfoSQL.updateOneDeptTotal", deptidlist.get(i));
		}
		
		return deptidlist.size();
	}

	/**
	 * 获取设备类型列表
	 * @return
	 */
	@Override
	public List<DeviceType> getDeviceTypeList() {
		return this.baseDao.queryForList("oracle-carInfoSQL.getDeviceTypeList");
	}
	
	/**
	 * 获取车辆类型列表
	 * @return
	 */
	public List<CarType> getCarTypeList(){
		return this.baseDao.queryForList("oracle-carInfoSQL.getCarTypeList");
	}
	
	/**
	 * 获取车辆用途列表
	 * @return
	 */
	public List<CarUse> getCarUseList(){
		return this.baseDao.queryForList("oracle-carInfoSQL.getCarUseList");
	}
	
	/**
	 * 查询司机信息
	 * @return
	 */
	@Override
	public List<CarDriver> selectCarDriver(CarDriver carDriver) {
		return this.baseDao.queryForList("oracle-carInfoSQL.selectCarDriver", carDriver);
	}
	
	public SysOperateLogService getLogService() {
		return logService;
	}

	public void setLogService(SysOperateLogService logService) {
		this.logService = logService;
	}


	public TerminalDeviceInfoService getTerminalDeviceInfoService() {
		return terminalDeviceInfoService;
	}


	public void setTerminalDeviceInfoService(
			TerminalDeviceInfoService terminalDeviceInfoService) {
		this.terminalDeviceInfoService = terminalDeviceInfoService;
	}


	public DssService getDssService() {
		return dssService;
	}


	public void setDssService(DssService dssService) {
		this.dssService = dssService;
	}


	@Override
	public Integer getCarDeviceType(String carnumber) {
		Object dtype = this.baseDao.queryForObject("oracle-carInfoSQL.getCarDeviceType", carnumber);
		if(dtype== null){
			return 0;
		}else{
			return (Integer) dtype;
		}
	}

	/**
	 * 根据车牌号号获取车辆设备类型与用户类型
	 * @param terminal
	 * @return
	 */
	public CarDeviceDetail getCarDeviceDetail(String carnumber){
		return (CarDeviceDetail)this.baseDao.queryForObject("oracle-carInfoSQL.getCarDeviceDetail", carnumber);
	}
	
    /**
     * 读取车辆详细信息
     */
	@Override
	public CarDetail readCarDetailInfo(int carid) {
		return (CarDetail) this.baseDao.queryForObject("oracle-carInfoSQL.readCarDetailInfo",carid);
	}
	
	  /**
     * 得到车辆详细信息
     */
	@Override
	public CarDetail getCarDetailInfo(String carnumber) {
		return (CarDetail) this.baseDao.queryForObject("oracle-carInfoSQL.getCarDetailInfo",carnumber);
	}
	
	/**
	 * 得到车辆运行轨迹经纬度点集合
	 * @param paramsMap 可以传入参数queryTime1,queryTime2，起止时间,车辆id
	 * @return
	 */
	public List<CarStatus> findCarTrackPointList(Map paramsMap){
		return this.baseDao.queryForList("oracle-carInfoSQL.findCarTrackPointList", paramsMap);
	}
	
	/**
	 * oracle鉴权数据往86-mysql插一份
	 */
	public void addEcsTo86(){
		List<String> terlist = this.baseDao.queryForList("oracle-carInfoSQL.getTerminalList");
		if(terlist.size()>0){
			for(String terminal : terlist){
				TerminalAuth terminalAuth = new TerminalAuth();
				terminalAuth.setCarTel(terminal);
				int type = 29;
				terminalAuth.setUserType(type);
		
				int flag = dssService.getTerminalAuth(terminalAuth);
				if (flag > 0) {
					dssService.deleteTerminalAuth(terminalAuth);
				}
				System.out.println(terminal);
				dssService.insertTerminalAuth(terminalAuth);
			}
		}
	}
	
	/**
	 * 86-mysql鉴权数据往118-oracle插一份
	 */
	public void addEcsTo118(){
		List<TerminalAuth> list = dssService.getTerminalAuthList();
		List<TerminalAuth> addlist = new ArrayList<TerminalAuth>();
		if(list.size()>0){
			for(TerminalAuth ter : list){
				TerminalAuth terminalAuth = new TerminalAuth();
				terminalAuth.setCarTel(ter.getCarTel());
				terminalAuth.setUserType(ter.getUserType());
				addlist.add(terminalAuth);
			}
			this.baseDao.save("oracle-carInfoSQL.addEcsTo118", addlist);
			System.out.println("添加了"+addlist.size()+"条记录");
		}
	}
	
	/**
	 * taxi数据往oracle插一份鉴权数据
	 */
	public void addEcsToOracle(){
		List<TerminalAuth> list = this.baseDao.queryForList("oracle-carInfoSQL.getTerminalAuthList");
		System.out.println("taxi取出了"+list.size()+"条记录");
		List<TerminalAuth> addlist = new ArrayList<TerminalAuth>();
		if(list.size()>0){
			for(TerminalAuth ter : list){
				TerminalAuth terminalAuth = new TerminalAuth();
				terminalAuth.setCarTel(ter.getCarTel());
				terminalAuth.setUserType(ter.getUserType());
				int flag = dssService.getTerminalAuth(terminalAuth);
				if (flag == 0) {
					addlist.add(terminalAuth);
				}
			}
			if(addlist.size() > 0){
				dssService.insertTerminalAuthBitch(addlist);
			}
			System.out.println("添加了"+addlist.size()+"条鉴权记录");
		}
	}
	
	/**
	 * 批量插入终端鉴权
	 * @param terminalAuth
	 */
	public void insertTerminalAuthBitch(List<TerminalAuth> terminalAuth){
		
	}
	
	/**
	 * 统计上级所有的组织机构id
	 * @param deptid
	 * @return
	 */
	@Override
	public List<Integer> getUpDeptlist(int deptid){
		return (List<Integer>)this.baseDao.queryForList("oracle-carInfoSQL.getUpDeptlist", deptid);
	}
	
	
	/**
	 * 获取车辆总数
	 * @param deptid
	 * @return
	 */
	@Override
	public int getCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getCarInfoSum", deptid);
	}

	/**
	 * 获取长离线数量
	 * @param deptid
	 * @return
	 */
	@Override
	public int getLongOffLineCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getLongOffLineCarInfoSum", deptid);
	}

	/**
	 * 根据流水号更新处理结果
	 * @param seq
	 * @return
	 */
	@Override
	public int updateControlResult(ControlRecord controlRecord) {
		return this.baseDao.update("oracle-carInfoSQL.updateControlResult", controlRecord);
	}
	
	/**
	 * 获取离线数量
	 * @param deptid
	 * @return
	 */
	@Override
	public int getOffLineCarInfoSum(int deptid) {
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getOffLineCarInfoSum", deptid);
	}
	
	/**
	 * 获取车辆司机信息 
	 * @param seq
	 * @return
	 */
	public List<CarDriverInfo> getCarDriverInfoList(CarDriverInfo carDriverInfo){
		return this.baseDao.queryForList("oracle-carInfoSQL.getCarDriverInfoList", carDriverInfo);
	}

	 /**
	 * 查询车辆控制记录信息
	 * @param carInfo
	 * @param currentPage
	 * @param everyPage
	 * @return
	 */
	@Override
	public Map selectCheckCarControlRecord(int currentPage, int everyPage,
			ControlRecord controlRecord) {
		// TODO Auto-generated method stub
		return this.baseDao.findPageList("oracle-carInfoSQL.selectCheckCarControlRecord",
				"oracle-carInfoSQL.selectCarControlRecord", controlRecord,currentPage,everyPage);
	}
	
	/**
	 * 添加车辆控制记录
	 * @param carInfo
	 * @return
	 */
	@Override
	public int insertCarControlrecord(ControlRecord controlRecord) {
		// TODO Auto-generated method stub
		controlRecord.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-carInfoSQL.insertCarControlrecord", controlRecord);
	}
	
	/**
	 * 获取最新插入的车辆id 
	 * @param seq
	 * @return
	 */
	public Integer getNewCarid(){
		return (Integer) this.baseDao.queryForObject("oracle-carInfoSQL.getNewCarid");
	}
	
	/**
	 * 根据carid新增车辆属性信息
	 * @param seq
	 * @return
	 */
	public int insertCarProperty(CarInfo carInfo){
		return this.baseDao.save("oracle-carInfoSQL.insertCarProperty", carInfo);
	}
	
	/**
	 * 根据carid修改车辆属性信息
	 * @param seq
	 * @return
	 */
	public int updateCarProperty(CarInfo carInfo){
		return this.baseDao.update("oracle-carInfoSQL.updateCarProperty", carInfo);
	}
	
	/**
	 * 根据carid删除车辆属性信息
	 * @param seq
	 * @return
	 */
	public int deleteCarProperty(String carid){
		return this.baseDao.delete("oracle-carInfoSQL.deleteCarProperty", carid);
	}
	
	/**
	 * 根据carid获取营运证信息
	 * @param seq
	 * @return
	 */
	public OperateCertificate findOperateCertificateByCarid(Integer carid){
		return (OperateCertificate) this.baseDao.queryForObject("oracle-carInfoSQL.findOperateCertificateByCarid", carid);
	}
	
	/**
	 * 根据carid新增营运证信息
	 * @param seq
	 * @return
	 */
	public int insertOperateCertificate(OperateCertificate operateCertificate){
		operateCertificate.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-carInfoSQL.insertOperateCertificate", operateCertificate);
	}
	
	/**
	 * 根据id修改营运证信息
	 * @param seq
	 * @return
	 */
	public int updateOperateCertificate(OperateCertificate operateCertificate){
		return this.baseDao.update("oracle-carInfoSQL.updateOperateCertificate", operateCertificate);
	}
	
	/**
	 * 根据carid删除营运证信息
	 * @param seq
	 * @return
	 */
	public int deleteOperateCertificate(String carid){
		return this.baseDao.delete("oracle-carInfoSQL.deleteOperateCertificate", carid);
	}
	
	/**
	 * 根据caridStr获得车辆信息
	 * @param caridStr
	 * @return
	 */
	public Map getCarInfoListByCaridStr(final int currentPage, final int everyPage,String caridStr){
		return this.baseDao.findPageList("oracle-carInfoSQL.getCarInfoListByCaridStr",
				"oracle-carInfoSQL.getCarInfoListByCaridStrCount", caridStr, currentPage, everyPage);
	}
	
	/**
	 * 更新司机代码
	 * @param seq
	 * @return
	 */
	public int updateDriverCode(CarInfo carInfo){
		return this.baseDao.update("oracle-carInfoSQL.updateDriverCode", carInfo);
	}
}



