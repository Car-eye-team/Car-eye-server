package com.careye.common.service.impl;

import com.careye.base.service.GenericService;
import com.careye.car.domain.CarInfo;
import com.careye.common.domain.Account;
import com.careye.common.domain.DevInfo;
import com.careye.common.domain.DevStatus;
import com.careye.common.domain.UserInfo;
import com.careye.common.service.CmssService;
import com.careye.utils.DateUtil;
import com.careye.utils.MD5;
import com.careye.utils.StringUtils;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-27 上午11:58:40
 * @修改人：ll
 * @修改时间：2016-5-27 上午11:58:40
 * @修改备注：
 * @version 1.0
 */
public class CmssServiceImpl extends GenericService implements CmssService {
	
	/**
	 * 添加account
	 */
	public int saveAccount(Account account){
		if(StringUtils.isNotEmty(account.getPassword())){
			account.setPassword(MD5.MD5Encode(account.getPassword()));
		}else{
			account.setPassword(MD5.MD5Encode("000000"));
		}
		account.setValidity(DateUtil.fomatDate(DateUtil.addMonth("yyyy-MM-dd HH:mm:ss", DateUtil.getSQLDate(), 20, 0, 0)));
		
		int count = (Integer) this.baseDao.queryForObject("mysql-cmssSQL.accountIsExist", account);
		Integer resultCount = 0;
		if(count == 0){
			this.baseDao.save("mysql-cmssSQL.saveAccount", account);
		}else{
			this.baseDao.update("mysql-cmssSQL.updateAccount", account);
		}
		resultCount = (Integer) this.baseDao.queryForObject("mysql-cmssSQL.getIdByAccount", account);
		
		//保存userinfo
		UserInfo userInfo = new UserInfo();
		userInfo.setAccountid(resultCount);
		
		saveUserinfo(userInfo);
		
		return resultCount;
	}
	
	/**
	 * 删除account
	 */
	public int deleteAccount(String account){
		Account accInfo = new Account();
		accInfo.setAccount(account);
		Integer accountid = (Integer) this.baseDao.queryForObject("mysql-cmssSQL.getIdByAccount", accInfo);
		//删除userinfo
		deleteUserinfo(accountid+"");
		
		return this.baseDao.delete("mysql-cmssSQL.deleteAccount", account);
	}
	
	/**
	 * 添加DevInfo
	 */
	public int saveDevinfo(DevInfo devInfo){
		int count = (Integer) this.baseDao.queryForObject("mysql-cmssSQL.devinfoIsExist", devInfo);
		Integer resultCount = 0;
		if(count == 0){
			resultCount = this.baseDao.save("mysql-cmssSQL.saveDevinfo", devInfo);
		}else{
			resultCount = this.baseDao.update("mysql-cmssSQL.updateDevinfo", devInfo);
		}
		return resultCount;
	}
	
	/**
	 * 删除DevInfo
	 */
	public int deleteDevinfo(String idno){
		return this.baseDao.delete("mysql-cmssSQL.deleteDevinfo", idno);
	}
	
	/**
	 * 添加DevStatus
	 */
	public int saveDevstatus(DevStatus devStatus){
		int count = (Integer) this.baseDao.queryForObject("mysql-cmssSQL.devstatusIsExist", devStatus);
		Integer resultCount = 0;
		if(count == 0){
			resultCount = this.baseDao.save("mysql-cmssSQL.saveDevstatus", devStatus);
		}else{
			resultCount = this.baseDao.update("mysql-cmssSQL.updateDevstatus", devStatus);
		}
		return resultCount;
	}
	
	/**
	 * 删除DevStatus
	 */
	public int deleteDevStatus(String devidno){
		return this.baseDao.delete("mysql-cmssSQL.deleteDevStatus", devidno);
	}
	
	/**
	 * 添加userinfo
	 */
	public int saveUserinfo(UserInfo userInfo){
		userInfo.setParentid(0);
		userInfo.setIsadmin(1);
		userInfo.setRoleid(0);
		return this.baseDao.save("mysql-cmssSQL.saveUserinfo", userInfo);
	}
	
	/**
	 * 删除userinfo
	 */
	public int deleteUserinfo(String accountid){
		return this.baseDao.delete("mysql-cmssSQL.deleteUserinfo", accountid);
	}
	
	/**
	 * 终端信息保存
	 */
	public int saveTer(CarInfo carInfo){
		int reCount = 0;
		
		Account account = new Account();
		account.setName(carInfo.getBlocname());
		account.setAccount(carInfo.getTerminal());
		account.setType(1);
		//保存account
		Integer accountid = saveAccount(account);
		
		DevInfo devInfo = new DevInfo();
		devInfo.setAccountid(accountid);
		devInfo.setIdno(carInfo.getTerminal());
		devInfo.setSimcard(carInfo.getTerminal());
		devInfo.setDateproduct(DateUtil.getSQLDate());
		devInfo.setPaybegin(DateUtil.getToday()+" 08:00:00");
		devInfo.setDevtype(1);
		devInfo.setDevgroupid(0);
		devInfo.setIcon(1);
		devInfo.setChncount(4);
		devInfo.setChnname("CH1,CH2,CH3,CH4");
		devInfo.setIoincount(6);
		devInfo.setIoinname("IO_1,IO_2,IO_3,IO_4,IO_5,IO_6");
		devInfo.setTempcount(0);
		devInfo.setUserid(0);
		devInfo.setUsersex(1);
		devInfo.setUserpost(1);
		devInfo.setNetaddrtype(0);
		devInfo.setPayenable(0);
		devInfo.setPayperiod(24);
		devInfo.setPaymonth(0);
		devInfo.setPaydelayday(0);
		devInfo.setStoday(0);
		//保存dev_info
		saveDevinfo(devInfo);
		
		DevStatus devStatus = new DevStatus();
		devStatus.setDevidno(carInfo.getTerminal());
		//保存dev_status
		saveDevstatus(devStatus);
		
		return reCount;
	}
	
	/**
	 * 终端信息删除
	 */
	public int deleteTer(String ter){
		int reCount = 0;
		//删除ACCOUNT表数据
		deleteAccount(ter);
		//删除DEV_INFO数据
		deleteDevinfo(ter);
		//删除DEV_STATUS表数据
		reCount = deleteDevStatus(ter);
		
		return reCount;
	}

}
