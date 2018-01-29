/**
* Description: 多森商用车平台
* 文件名：CarParamService.java
* 版本信息：1.0
* 日期：2014-7-13
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.api.service;

import java.util.List;
import java.util.Map;

import com.careye.api.domain.AdviceFeedBack;
import com.careye.api.domain.ApiKey;
import com.careye.api.domain.AppUserFriends;
import com.careye.api.domain.AppUserInvite;
import com.careye.api.domain.ComplaintInfo;
import com.careye.api.domain.InviteList;
import com.careye.api.domain.VersionControl;



/**
 * @项目名称：car-eyeAPI
 * @类名称：ApiService
 * @类描述：API接口
 * @创建人：zhangrong
 * @创建时间：2015-8-17 下午02:55:49
 * @修改人：zhangrong
 * @修改时间：2015-8-17 下午02:55:49
 * @修改备注：
 * @version 1.0
 */
public interface ApiService {
	
	/**
	 * API密钥信息名是否存在
	 * @param apiKeyInfo
	 * @return
	 */
	public Integer apikeyIsExist(ApiKey apiKey);
	
	/**
	 *  更新API密钥状态，请求次数 
	 * @param ApiKey
	 */
	public int updateApiKeyByIdKey(ApiKey apiKey);
	
	/**
	 * 添加意见反馈
	 * @param User
	 */
	public int addAdviceFeedBack(AdviceFeedBack adviceFeedBack);

	/**
	 * 登录
	 * @param map
	 * @return
	 */
	public int login(Map map);
	

	/**
	 * 根据API密钥或ID获取APP版本最新详细信息 
	 */
	public VersionControl getApiKeyControlInfo(int typeid);
	
	/**
	 * 获取投诉类型列表
	 */
	public List getComplaintTypeList();
	
	/**
	 * 添加投诉信息
	 */
	public int addComplaintInfo(ComplaintInfo complaintInfo);
	
	/**
	 * 获取投诉信息列表
	 */
	public Map getComplaintInfoList(int currentPage,int everyPage, String phone);
	
	/**
	 * 获取投诉信息详情
	 */
	public ComplaintInfo getComplaintInfo(String id);
	
	/**
	 * 根据设备号获取当前里程
	 */
	public Double getNowMileByTerminal(String terminal);
	
	/**
	 * 获取广告类型版本号
	 */
	public String getVeridByPositionid(String positionid);
	
	/**
	 * 获取顶灯广告列表
	 */
	public List getAdvertContentDdList(String positionid);
	
	/**
	 * 获取后枕屏广告列表
	 */
	public List getAdvertContentHzpList(String positionid);
	
	
	/**
	 * 添加邀请信息
	 * @param appUserInvite
	 * @return
	 */
	public int addAppUserInvite(AppUserInvite appUserInvite);
	
	/**
	 * 同意/拒绝邀请
	 * @param appUserInvite
	 * @return
	 */
	public int updateAppUserInvite(AppUserInvite appUserInvite);
	
	/**
	 * 根据id获取邀请信息
	 * @param appUserInvite
	 * @return
	 */
	public AppUserInvite getAppUserInviteById(String id);
	
	/**
	 * 邀请信息列表
	 * @param map
	 * @return
	 */
	public List<InviteList> getAppUserInviteList(Map map);
	
	/**
	 * 添加车友
	 * @param appUserFriends
	 * @return
	 */
	public int addAppUserFriends(AppUserFriends appUserFriends);
	
	/**
	 * 车友是否已存在
	 * @param appUserFriends
	 * @return
	 */
	public int isExistFriends(AppUserFriends appUserFriends);
	
	/**
	 * 删除车友
	 * @param appUserFriends
	 * @return
	 */
	public int deleteAppUserFriends(AppUserFriends appUserFriends);
	
	/**
	 * 获取车友列表
	 * @param map
	 * @return
	 */
	public Map getAppUserFriendsList(Map map,int currentPage,int everyPage);
	
	/**
	 * 根据车牌号获取车辆id
	 */
	public String getCaridByCarnumber(String carnumber);
	
}
