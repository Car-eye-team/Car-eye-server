/**
* Description: 多森商用车平台
* 文件名：CarParamServiceImpl.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.api.service.impl;

import java.util.List;
import java.util.Map;

import com.careye.api.domain.AdviceFeedBack;
import com.careye.api.domain.ApiKey;
import com.careye.api.domain.AppUserFriends;
import com.careye.api.domain.AppUserInvite;
import com.careye.api.domain.ComplaintInfo;
import com.careye.api.domain.InviteList;
import com.careye.api.domain.VersionControl;
import com.careye.api.service.ApiService;
import com.careye.base.service.GenericService;
import com.careye.utils.DateUtil;



/**
 * @项目名称：car-eyeAPI
 * @类名称：CarParamServiceImpl
 * @类描述：API接口实现
 * @创建人：zhangrong
 * @创建时间：2015-8-17 下午02:55:49
 * @修改人：zhangrong
 * @修改时间：2015-8-17 下午02:55:49
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ApiServiceImpl  extends GenericService implements ApiService{
	
	/**
	 * API密钥信息名是否存在
	 * @param apiKeyInfo
	 * @return
	 */
	public Integer apikeyIsExist(ApiKey apiKey){
		return (Integer)this.baseDao.queryForObject("oracle-apiSQL.apikeyIsExist",apiKey);
	}
	
	/**
	 *  更新API密钥状态，请求次数 
	 * @param ApiKey
	 */
	public int updateApiKeyByIdKey(ApiKey apiKey){
		return this.baseDao.update("oracle-apiSQL.updateApiKeyByIdKey", apiKey);
	}

	/**
	 * 添加意见反馈
	 * @param User
	 */
	public int addAdviceFeedBack(AdviceFeedBack adviceFeedBack){
		adviceFeedBack.setCreatetime(DateUtil.getSQLDate());
		return this.baseDao.save("oracle-apiSQL.addAdviceFeedBack", adviceFeedBack);
	}
	
	/**
	 * 登录
	 * @param map
	 * @return
	 */
	public int login(Map map){
		return (Integer)this.baseDao.queryForObject("oracle-apiSQL.login", map);
	}

	/**
	 * 根据API密钥或ID获取APP版本最新详细信息 
	 */
	public VersionControl getApiKeyControlInfo(int typeid){
		return (VersionControl)this.baseDao.queryForObject("oracle-apiSQL.getApiKeyControlInfo", typeid);
	}
	
	/**
	 * 获取投诉类型列表
	 */
	public List getComplaintTypeList(){
		return this.baseDao.queryForList("oracle-apiSQL.getComplaintTypeList");
	}
	
	/**
	 * 添加投诉信息
	 */
	public int addComplaintInfo(ComplaintInfo complaintInfo){
		return this.baseDao.save("oracle-apiSQL.addComplaintInfo", complaintInfo);
	}
	
	/**
	 * 获取投诉信息列表
	 */
	public Map getComplaintInfoList(int currentPage,int everyPage, String phone){
		return this.baseDao.findPageList("oracle-apiSQL.getComplaintInfoList", 
				"oracle-apiSQL.getComplaintInfoListCount", phone, currentPage, everyPage);
	}
	
	/**
	 * 获取投诉信息详情
	 */
	public ComplaintInfo getComplaintInfo(String id){
		return (ComplaintInfo) this.baseDao.queryForObject("oracle-apiSQL.getComplaintInfo", id);
	}
	
	/**
	 * 根据设备号获取当前里程
	 */
	public Double getNowMileByTerminal(String terminal){
		return (Double) this.baseDao.queryForObject("oracle-apiSQL.getNowMileByTerminal", terminal);
	}
	
	/**
	 * 获取广告类型版本号
	 */
	public String getVeridByPositionid(String positionid){
		return (String) this.baseDao.queryForObject("oracle-apiSQL.getVeridByPositionid", positionid);
	}
	
	/**
	 * 获取顶灯广告列表
	 */
	public List getAdvertContentDdList(String positionid){
		return this.baseDao.queryForList("oracle-apiSQL.getAdvertContentDdList", positionid);
	}
	
	/**
	 * 获取后枕屏广告列表
	 */
	public List getAdvertContentHzpList(String positionid){
		return this.baseDao.queryForList("oracle-apiSQL.getAdvertContentHzpList", positionid);
	}
	
	/**
	 * 添加邀请信息
	 * @param appUserInvite
	 * @return
	 */
	public int addAppUserInvite(AppUserInvite appUserInvite){
		return this.baseDao.save("oracle-apiSQL.addAppUserInvite", appUserInvite);
	}
	
	/**
	 * 同意/拒绝邀请
	 * @param appUserInvite
	 * @return
	 */
	public int updateAppUserInvite(AppUserInvite appUserInvite){
		return this.baseDao.update("oracle-apiSQL.updateAppUserInvite", appUserInvite);
	}
	
	/**
	 * 根据id获取邀请信息
	 * @param appUserInvite
	 * @return
	 */
	public AppUserInvite getAppUserInviteById(String id){
		return (AppUserInvite) this.baseDao.queryForObject("oracle-apiSQL.getAppUserInviteById", id);
	}
	
	/**
	 * 邀请信息列表
	 * @param map
	 * @return
	 */
	public List<InviteList> getAppUserInviteList(Map map){
		return this.baseDao.queryForList("oracle-apiSQL.getAppUserInviteList", map);
	}
	
	/**
	 * 添加车友
	 * @param appUserFriends
	 * @return
	 */
	public int addAppUserFriends(AppUserFriends appUserFriends){
		return this.baseDao.save("oracle-apiSQL.addAppUserFriends", appUserFriends);
	}
	
	/**
	 * 删除车友
	 * @param appUserFriends
	 * @return
	 */
	public int deleteAppUserFriends(AppUserFriends appUserFriends){
		return this.baseDao.delete("oracle-apiSQL.deleteAppUserFriends", appUserFriends);
	}
	
	/**
	 * 车友是否已存在
	 * @param appUserFriends
	 * @return
	 */
	public int isExistFriends(AppUserFriends appUserFriends){
		return (Integer) this.baseDao.queryForObject("oracle-apiSQL.isExistFriends", appUserFriends);
	}
	
	/**
	 * 获取车友列表
	 * @param map
	 * @return
	 */
	public Map getAppUserFriendsList(Map map,int currentPage,int everyPage){
		return this.baseDao.findPageList("oracle-apiSQL.getAppUserFriendsList",
				"oracle-apiSQL.getAppUserFriendsListCount", map, currentPage, everyPage);
	}
	
	/**
	 * 根据车牌号获取车辆id
	 */
	public String getCaridByCarnumber(String carnumber){
		return (String) this.baseDao.queryForObject("oracle-apiSQL.getCaridByCarnumber",carnumber);
	}
	
}


