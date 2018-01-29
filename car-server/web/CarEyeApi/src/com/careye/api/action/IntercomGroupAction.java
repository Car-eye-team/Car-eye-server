package com.careye.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.api.domain.ApiKey;
import com.careye.api.service.ApiService;
import com.careye.base.action.BasePageAction;
import com.careye.constant.Constant;
import com.careye.mq.HandleUtil;
import com.careye.taxi.api.service.GroupService;
import com.careye.taxi.api.service.TaxiService;
import com.careye.taxi.domain.IntercomGroup;
import com.careye.taxi.domain.IntercomGroupInvite;
import com.careye.taxi.domain.IntercomGroupMember;
import com.careye.taxi.domain.IntercomGroupSearch;
import com.careye.utils.DateUtil;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2016-5-5 下午01:55:54
 * @修改人：ll
 * @修改时间：2016-5-5 下午01:55:54
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class IntercomGroupAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(IntercomGroupAction.class);
	
	private String success;
	private Map jsondata;
	private List list;
	
	private ApiKey apiKey = new ApiKey();
	
	private GroupService groupService;
	
	private ApiService apiService;
	private TaxiService taxiService;
	
	private String ak;
	private String type;
	
	private String carnumber;
	private String id;
	private String gid;
	private String isagree;
	private String name;
	private String remark;
	private String mile;
	private String lng;
	private String lat;
	
	private String currentpage;
	private String everypage;
	
	//初始化返回JSON的Map对象
	private void initMap() {
		if(jsondata == null) {
			jsondata = new HashMap();
		}
	}
	
	/**
	 * 新建对讲组api接口
	 * @return
	 */
	 public String addIntercomGroup(){
		logger.info("新建对讲组api接口:ak="+ak+",carnumber="+carnumber+",name="+name+",remark="+remark+",lng="+lng+",lat="+lat);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
//			carnumber = "粤B00008";
			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			if(name == null || "".equals(name)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数name为空不存在");
				return SUCCESS;
			}
			if(lng == null || "".equals(lng)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数lng为空不存在");
				return SUCCESS;
			}
			if(lat == null || "".equals(lat)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数lat为空不存在");
				return SUCCESS;
			}
			
			IntercomGroup intercomGroup = new IntercomGroup();
			intercomGroup.setCarid(carid);
			intercomGroup.setName(name);
			intercomGroup.setRemark(remark);
			intercomGroup.setLng(Double.parseDouble(lng));
			intercomGroup.setLat(Double.parseDouble(lat));
			intercomGroup.setCreatetime(DateUtil.getSQLDate());
				
			groupService.addIntercomGroup(intercomGroup);
				
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
					
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("新建对讲组api接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}

 	/**
	 * 我加入的对讲组接口
	 * @return
	 */
	public String myAddGroup(){
		logger.info("我加入的对讲组接口:ak="+ak+",carnumber="+carnumber);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}
			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			
			list = groupService.myAddGroup(carid);
			
			jsondata.put("data", list);
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
				
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("我加入的对讲组接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}
	 
	 /**
	 * 删除对讲组api接口
	 * @return
	 */
	 public String delIntercomGroup(){
			logger.info("删除对讲组api接口:ak="+ak+",id="+id);
			initMap();
			ApiKey apiKey = new ApiKey();
			try {
				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.DJ_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}

				if(id == null || "".equals(id)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数id为空不存在");
					return SUCCESS;
				}
				
				int result = groupService.delIntercomGroup(id);
					
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
					
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("删除对讲组api接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		}

	 
	 /**
	 * 获取接收申请列表
	 * @return
	 */
	public String myInvite(){
		logger.info("获取接收申请列表接口:ak="+ak+",carnumber="+carnumber);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			
			Map map = new HashMap();
			map.put("carid", carid);
			
			if(StringUtils.isNotEmty(isagree)){
				map.put("isagree", isagree);
			}
			
			list = groupService.myInvite(map);
			
			jsondata.put("data", list);
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
				
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("获取接收申请列表接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}
	 
	
	/**
	 * 组成员列表接口
	 * @return
	 */
	public String getGroupMember(){
		logger.info("组成员列表接口:ak="+ak+",gid="+gid);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			if(StringUtils.isEmty(gid)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数gid为空不存在");
				return SUCCESS;
			}
			
			list = groupService.getGroupMember(Integer.parseInt(gid));
			
			jsondata.put("data", list);
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
				
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("我加入的对讲组接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	}
	
	/**
	 * 加入组成员申请api接口
	 * @return
	 */
	 public String addIntercomGroupInvite(){
		logger.info("加入组成员申请api接口:ak="+ak+",gid="+gid+",carnumber="+carnumber+",remark="+remark);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			if(gid == null || "".equals(gid)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数gid为空不存在");
				return SUCCESS;
			}
			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			
			IntercomGroupInvite intercomGroupInvite = new IntercomGroupInvite();
			intercomGroupInvite.setGid(Integer.parseInt(gid));
			intercomGroupInvite.setCarid(carid);
			intercomGroupInvite.setRemark(remark);
			intercomGroupInvite.setIsagree(0);
			intercomGroupInvite.setInvitetime(DateUtil.getSQLDate());
				
			int inviteid = groupService.addIntercomGroupInvite(intercomGroupInvite);
			if(inviteid > 0){
				IntercomGroup intercomGroup = groupService.getIntercomGroupDetail(inviteid);
				if(intercomGroup != null){
					HandleUtil.invateNotice(intercomGroup.getTerminal(),HandleUtil.getSerialId(), Integer.parseInt(gid),
							carid, intercomGroup.getName(), intercomGroup.getCarnumber());
				}
			}
				
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
					
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("新建组成员api接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	} 
	 
	 /**
	 * 退出组接口
	 * @return
	 */
	 public String quitGroup(){
		logger.info("退出组api接口:ak="+ak+",gid="+gid+",carnumber="+carnumber);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			if(gid == null || "".equals(gid)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数gid为空不存在");
				return SUCCESS;
			}
			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			IntercomGroupMember groupMember = new IntercomGroupMember();
			groupMember.setGid(Integer.parseInt(gid));
			groupMember.setCarid(carid);
			groupService.deleteIntercomGroupMember(groupMember);
				
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
					
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("退出组api接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	} 
		 
	 
	 /**
	 * （新版）加入组成员接口（默认同意）
	 * @return
	 */
	 public String addGroup(){
		logger.info("加入组成员接口:ak="+ak+",gid="+gid+",carnumber="+carnumber+",remark="+remark);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			if(gid == null || "".equals(gid)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数gid为空不存在");
				return SUCCESS;
			}
			//carnumber = "粤B00008";
			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			
			//插入组成员表
			IntercomGroupMember intercomGroupMember = new IntercomGroupMember();
			intercomGroupMember.setGid(Integer.parseInt(gid));
			intercomGroupMember.setCarid(carid);
			intercomGroupMember.setIsadmin(2);
			intercomGroupMember.setCreatetime(DateUtil.getSQLDate());
			
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
			
			int memebrount = groupService.isExistGroupMember(intercomGroupMember);
			if(memebrount > 0){
				jsondata.put("status", 9);
				jsondata.put("message", "好友已存在");
			}else{
				
				//插入邀请申请表
				IntercomGroupInvite intercomGroupInvite = new IntercomGroupInvite();
				intercomGroupInvite.setGid(Integer.parseInt(gid));
				intercomGroupInvite.setCarid(carid);
				intercomGroupInvite.setRemark(remark);
				intercomGroupInvite.setIsagree(1);
				intercomGroupInvite.setInvitetime(DateUtil.getSQLDate());
					
				int inviteid = groupService.addIntercomGroupInvite(intercomGroupInvite);
				
				if(memebrount <= 0){
					groupService.addIntercomGroupMember(intercomGroupMember);
				}
			}
				
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("加入组成员接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	} 
		 
	 
	 /**
	 * 根据组名称搜索对讲组组列表api接口
	 * @return
	 */
	 public String getIntercomGroupList(){
		logger.info("根据组名称搜索对讲组组列表api接口:ak="+ak+",carnumber="+carnumber+",name="+name);
		initMap();
		try {
			if(ak == null){
				jsondata.put("status", 4);
				jsondata.put("message", "ak不存在或者非法");
				return SUCCESS;
			}else{
				apiKey.setKey(DateUtil.StringChange(ak));
				apiKey.setTypeid(Constant.DJ_APP_TYPE);
				apiKey.setRequestcount(1);//只查询密钥是启用的
				//查询密钥是否存在
				int count = apiService.apikeyIsExist(apiKey);
				if(count<=0){
					jsondata.put("status", 1);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}
				// 增加API密钥请求次数
				apiService.updateApiKeyByIdKey(apiKey);
			}

			Integer carid = null;
			if(carnumber == null || "".equals(carnumber)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数carnumber为空");
				return SUCCESS;
			}else{
				carid = taxiService.getCaridByCarnumber(carnumber.trim());
				if(carid ==null){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber不存在于数据库");
					return SUCCESS;
				}
			}
			if(currentpage == null || "".equals(currentpage)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数currentpage为空不存在");
				return SUCCESS;
			}
			if(everypage == null || "".equals(everypage)){
				jsondata.put("status", 4);
				jsondata.put("message", "必填参数everypage为空不存在");
				return SUCCESS;
			}
			
			IntercomGroupSearch intercomGroupSearch = new IntercomGroupSearch();
			intercomGroupSearch.setCarid(carid);
			intercomGroupSearch.setName(name);
			
			Map resultMap = new HashMap();
			resultMap = groupService.getIntercomGroupList(Integer.parseInt(currentpage), 
					Integer.parseInt(everypage), intercomGroupSearch);
			
			Integer totalCount = (Integer) resultMap.get("totalCount");
			if(totalCount == null){
				totalCount = 0;
			}
			int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
			if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
				totalPages -= 1;
			}
			
			List<IntercomGroupSearch> list =  (List<IntercomGroupSearch>) resultMap.get("list");
			
			jsondata.put("totalCount", totalCount);
			jsondata.put("totalPages", totalPages);
			jsondata.put("list", list);
				
			jsondata.put("status", 0);
			jsondata.put("message", "成功");
					
			return SUCCESS;
		} catch (Exception e) {
			jsondata.put("status", 5);
			jsondata.put("message", "服务器内部错误");
			logger.info("新建组成员api接口请求异常");
			e.printStackTrace();
			return ERROR;
		}finally{
			logger.info("返回结果："+jsondata.toString());
		}
	} 
		 
		 /**
		 * 获取我创建的组api接口
		 * @return
		 */
		 public String myCreateGroup(){
			logger.info("获取我创建的组api接口:ak="+ak+",carnumber="+carnumber);
			initMap();
			try {
				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.DJ_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}

				Integer carid = null;
				if(carnumber == null || "".equals(carnumber)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber为空");
					return SUCCESS;
				}else{
					carid = taxiService.getCaridByCarnumber(carnumber.trim());
					if(carid ==null){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数carnumber不存在于数据库");
						return SUCCESS;
					}
				}
				
				List list = groupService.myCreateGroup(carid+"");
				
				jsondata.put("list", list);
					
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
						
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("获取我创建的组api接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		} 
	 
		 /**
		 * 根据设备传入的经纬度、范围（米）搜索周边组接口
		 * @return
		 */
		 public String getAroundIntercomGroupList(){
			logger.info("根据设备传入的经纬度、范围（米）搜索周边组接口:ak="+ak+",carnumber="+carnumber+",lng="+lng+",lat="+lat+",mile="+mile);
			initMap();
			try {
				if(ak == null){
					jsondata.put("status", 4);
					jsondata.put("message", "ak不存在或者非法");
					return SUCCESS;
				}else{
					apiKey.setKey(DateUtil.StringChange(ak));
					apiKey.setTypeid(Constant.DJ_APP_TYPE);
					apiKey.setRequestcount(1);//只查询密钥是启用的
					//查询密钥是否存在
					int count = apiService.apikeyIsExist(apiKey);
					if(count<=0){
						jsondata.put("status", 1);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}
					// 增加API密钥请求次数
					apiService.updateApiKeyByIdKey(apiKey);
				}

				Integer carid = null;
				if(carnumber == null || "".equals(carnumber)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数carnumber为空");
					return SUCCESS;
				}else{
					carid = taxiService.getCaridByCarnumber(carnumber.trim());
					if(carid ==null){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数carnumber不存在于数据库");
						return SUCCESS;
					}
				}
				if(mile == null || "".equals(mile)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数lng为空不存在");
					return SUCCESS;
				}
				if(lng == null || "".equals(lng)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数lng为空不存在");
					return SUCCESS;
				}
				if(lat == null || "".equals(lat)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数lat为空不存在");
					return SUCCESS;
				}
				
				if(currentpage == null || "".equals(currentpage)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数currentpage为空不存在");
					return SUCCESS;
				}
				if(everypage == null || "".equals(everypage)){
					jsondata.put("status", 4);
					jsondata.put("message", "必填参数everypage为空不存在");
					return SUCCESS;
				}
				Map querymap = new HashMap<String, String>();
				querymap.put("carid", carid);
				querymap.put("lng", lng);
				querymap.put("lat", lat);
				querymap.put("mile", mile);
				
				Map resultMap = groupService.getAroundIntercomGroupList(Integer.parseInt(currentpage), Integer.parseInt(everypage), querymap);
				
				Integer totalCount = (Integer) resultMap.get("totalCount");
				if(totalCount == null){
					totalCount = 0;
				}
				int totalPages = (int) Math.floor((double)totalCount/Integer.parseInt(everypage))+1;
				if((totalPages-1) * Integer.parseInt(everypage) == totalCount){
					totalPages -= 1;
				}
				
				List<IntercomGroupSearch> list =  (List<IntercomGroupSearch>) resultMap.get("list");
				
				jsondata.put("totalCount", totalCount);
				jsondata.put("totalPages", totalPages);
				jsondata.put("list", list);
					
				jsondata.put("status", 0);
				jsondata.put("message", "成功");
						
				return SUCCESS;
			} catch (Exception e) {
				jsondata.put("status", 5);
				jsondata.put("message", "服务器内部错误");
				logger.info("根据设备传入的经纬度、范围（米）搜索周边组接口请求异常");
				e.printStackTrace();
				return ERROR;
			}finally{
				logger.info("返回结果："+jsondata.toString());
			}
		} 		 
	
		 /**
		 * 同意/拒绝邀请api接口
		 * @return
		 */
		 public String updateIntercomGroupInvite(){
				logger.info("同意/拒绝邀请信息api接口:ak="+ak+",gid="+gid+",carnumber="+carnumber+",id="+id+",isagree="+isagree);
				initMap();
				ApiKey apiKey = new ApiKey();
				try {
					if(ak == null){
						jsondata.put("status", 4);
						jsondata.put("message", "ak不存在或者非法");
						return SUCCESS;
					}else{
						apiKey.setKey(DateUtil.StringChange(ak));
						apiKey.setTypeid(Constant.DJ_APP_TYPE);
						apiKey.setRequestcount(1);//只查询密钥是启用的
						//查询密钥是否存在
						int count = apiService.apikeyIsExist(apiKey);
						if(count<=0){
							jsondata.put("status", 1);
							jsondata.put("message", "ak不存在或者非法");
							return SUCCESS;
						}
						// 增加API密钥请求次数
						apiService.updateApiKeyByIdKey(apiKey);
					}

					if(gid == null || "".equals(gid)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数gid为空不存在");
						return SUCCESS;
					}
					if(id == null || "".equals(id)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数id为空不存在");
						return SUCCESS;
					}
					Integer carid = null;
					if(carnumber == null || "".equals(carnumber)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数carnumber为空");
						return SUCCESS;
					}else{
						carid = taxiService.getCaridByCarnumber(carnumber.trim());
						if(carid ==null){
							jsondata.put("status", 4);
							jsondata.put("message", "必填参数carnumber不存在于数据库");
							return SUCCESS;
						}
					}
					if(isagree == null || "".equals(isagree)){
						jsondata.put("status", 4);
						jsondata.put("message", "必填参数isagree为空不存在");
						return SUCCESS;
					}
					
					IntercomGroupInvite appUserInvite = new IntercomGroupInvite();
					appUserInvite.setGid(Integer.parseInt(gid));
					appUserInvite.setId(Integer.parseInt(id));
					appUserInvite.setCarid(carid);
					appUserInvite.setAgreedtime(DateUtil.getSQLDate());
					appUserInvite.setIsagree(Integer.parseInt(isagree));
					
					//修改
					int count = groupService.updateIntercomGroupInvite(appUserInvite);
					if(count > 0){
						if("2".equals(isagree)){
							jsondata.put("status", 0);
							jsondata.put("message", "成功");
						}else if("1".equals(isagree)){
							IntercomGroupMember intercomGroupMember = new IntercomGroupMember();
							intercomGroupMember.setGid(appUserInvite.getGid());
							intercomGroupMember.setCarid(appUserInvite.getCarid());
							intercomGroupMember.setIsadmin(2);
							intercomGroupMember.setCreatetime(DateUtil.getSQLDate());
							
							int memebrount = groupService.isExistGroupMember(intercomGroupMember);
							if(memebrount > 0){
								jsondata.put("status", 9);
								jsondata.put("message", "好友已存在");
							}else{
								if(memebrount <= 0){
									groupService.addIntercomGroupMember(intercomGroupMember);
								}
								jsondata.put("status", 0);
								jsondata.put("message", "成功");
							}
						}
					}
					
					return SUCCESS;
				} catch (Exception e) {
					jsondata.put("status", 5);
					jsondata.put("message", "服务器内部错误");
					logger.info("同意/拒绝邀请信息api接口请求异常");
					e.printStackTrace();
					return ERROR;
				}finally{
					logger.info("返回结果："+jsondata.toString());
				}
			}
			 
		
		 
		 
			 
	 
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Map getJsondata() {
		return jsondata;
	}

	public void setJsondata(Map jsondata) {
		this.jsondata = jsondata;
	}

	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	

	public ApiService getApiService() {
		return apiService;
	}

	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getIsagree() {
		return isagree;
	}

	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public String getEverypage() {
		return everypage;
	}

	public void setEverypage(String everypage) {
		this.everypage = everypage;
	}

	public String getMile() {
		return mile;
	}

	public void setMile(String mile) {
		this.mile = mile;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public TaxiService getTaxiService() {
		return taxiService;
	}

	public void setTaxiService(TaxiService taxiService) {
		this.taxiService = taxiService;
	}

	
}
