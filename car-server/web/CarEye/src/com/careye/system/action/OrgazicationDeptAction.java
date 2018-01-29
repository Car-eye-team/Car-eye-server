/**
* Description: car-eye车辆管理平台
* 文件名：OrgazicationDeptAction.java
* 版本信息：1.0
* 日期：2014-5-20
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.system.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.base.action.TreeDomain;
import com.careye.common.domain.Account;
import com.careye.common.service.CmssService;
import com.careye.common.service.MenuTreeService;
import com.careye.constant.ConfigProperties;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.BlocGroup;
import com.careye.system.domain.BlocUser;
import com.careye.system.service.OrgazicationDeptService;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：OrgazicationDeptAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2014-5-20 下午02:55:49
 * @修改人：zhangrong
 * @修改时间：2014-5-20 下午02:55:49
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class OrgazicationDeptAction extends BasePageAction {

private static final Logger logger = Logger.getLogger(OrgazicationDeptAction.class);
	
	private OrgazicationDeptService orgazicationDeptService;
	private MenuTreeService menuTreeService;
	private CmssService cmssService;
	
	private Bloc orgazicationDept;
	private Map result;
	private String success;
	private String ids;
	
	private Integer id;
	private String blocid;
	private String moveblocid;	//转移目标组织机构
	private String deptname;
	private String etime;
	private String stime;
	
	private String bloc_name;
	private List<Bloc> list;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 根据部门id查询部门信息
	 */
	@SuppressWarnings("unchecked")
	public String quertDeptById(){
		initMap();
		if(this.id == null){
			return ERROR;
		}
		orgazicationDept = orgazicationDeptService.quertDeptById(this.id);
		
		result.put("id", orgazicationDept.getId());
		result.put("parentid", orgazicationDept.getParentid());
		
		result.put("bloc_name", orgazicationDept.getBloc_name());
		result.put("res_per", orgazicationDept.getRes_per());
		result.put("address", orgazicationDept.getAddress());
		result.put("reg_address", orgazicationDept.getReg_address());
		result.put("com_homepage", orgazicationDept.getCom_homepage());
		result.put("fax", orgazicationDept.getFax());
		result.put("remark", orgazicationDept.getRemark());
		
		result.put("leg_per", orgazicationDept.getLeg_per());
		result.put("ec_nature", orgazicationDept.getEc_nature());
		result.put("reg_number", orgazicationDept.getReg_number());
		result.put("reg_person", orgazicationDept.getReg_person());
		result.put("tel", orgazicationDept.getTel());
		result.put("est_date", orgazicationDept.getEst_date());
		
		result.put("leg_per_card", orgazicationDept.getLeg_per_card());
		result.put("management", orgazicationDept.getManagement());
		result.put("reg_capital", orgazicationDept.getReg_capital());
		result.put("reg_date", orgazicationDept.getReg_date());
		result.put("email", orgazicationDept.getEmail());
		result.put("ent_reg_date", orgazicationDept.getEnt_reg_date());
		
		result.put("bu_li_number", orgazicationDept.getBu_li_number());
		result.put("tax_reg_number", orgazicationDept.getTax_reg_number());
		result.put("issuing_date", orgazicationDept.getIssuing_date());
		result.put("permit_person", orgazicationDept.getPermit_person());
		result.put("business_scope", orgazicationDept.getBusiness_scope());
		
		result.put("companyid", orgazicationDept.getCompanyid());
		return SUCCESS;
	}
	
	/**
	 * 组织机构列表不分页
	 * @return
	 */
	public String selectorgazicationDeptList() {
		try {
			initMap();
			orgazicationDept = new Bloc();
			Integer userid = null;
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
				orgazicationDept.setUserid(userid);
			}
			if(bloc_name !=null && !bloc_name.equals("null")&& !bloc_name.equals("undefined")){
				orgazicationDept.setBloc_name(URLDecoder.decode(bloc_name, "UTF-8"));
			}
			if(blocid !=null && !bloc_name.equals("null")&& !blocid.equals("undefined")){
				orgazicationDept.setBlocid(Integer.parseInt(blocid));
			}
			List list = orgazicationDeptService.selectOrgazicationDeptList(orgazicationDept);
			result.put("list", list);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OrgazicationDeptAction的方法 selectorgazicationDeptList执行出错，原因：" + e, e);
			return ERROR;
		}	
	};
	
	
	/**
	 * 保存组织机构
	 * @return
	 */
	public String saveOrgazicationDept() {
		
		try {
			initMap();
			if (orgazicationDept == null)
				return ERROR;
			//检查组织机构名是否重复
			int record = orgazicationDeptService.deptNameIsExist(orgazicationDept);
			if (record > 0) {
				//组织机构名已经存在
				result.put("su", -2);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if(orgazicationDept.getId()!=null){
				 count = orgazicationDeptService.updateOrgazicationDept(orgazicationDept);
				 result.put("deptid", orgazicationDept.getId());
			}else{
				 if(orgazicationDept.getParentid() == null)
					 orgazicationDept.setParentid(1);
				 count = orgazicationDeptService.addOrgazicationDept(orgazicationDept);
				 result.put("deptid", count);
			}
			if(count > 0){
				if("1".equals(ConfigProperties.CMSS_STATUS)){
					//Mysql数据库中插入数据
					Account account = new Account();
					account.setName(orgazicationDept.getBloc_name());
					account.setAccount(orgazicationDept.getBloc_name());
					account.setType(2);
					
					cmssService.saveAccount(account);
				}
				
				result.put("su", 1);
				this.success = "true";
			}else{
				result.put("su", -1);
			}
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("OrgazicationDeptAction的方法 saveOrgazicationDept执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除组织机构信息
	 * @return
	 */
	public String deleteOrgazicationDept(){
		try {
			initMap();
			
//			//检查组织机构下面是否有车辆
//			int index = orgazicationDeptService.containCarNum(id);
//			if (index > 0) {
//				//组织机构下面有车辆
//				result.put("su", -1);
//				return SUCCESS;
//			}
			
			//检测组织机构下面是否还有组织
			int count = orgazicationDeptService.queryDeptCount(id);
			if(count > 0){
				result.put("su", -2);
				return SUCCESS;
			}
			//检查组织机构下面是否分配有用户组
			int num = orgazicationDeptService.containUserGroupNum(id);
			if (num > 0) {
				//组织机构下面分配有用户组
				result.put("su", -3);
				return SUCCESS;
			}
			Bloc org = orgazicationDeptService.quertDeptById(id);
			
			int del = orgazicationDeptService.deleteOrgazicationDept(id);
			if(del > 0){
				if(org != null){
					if("1".equals(ConfigProperties.CMSS_STATUS)){
						cmssService.deleteAccount(org.getBloc_name());
					}
				}
				
				result.put("su", 1);
			}else{
				result.put("su", -4);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OrgazicationDeptAction 的方法 deleteOrgazicationDept执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 部门车辆统计列表
	 * @return
	 */
	public String carstatisticList() {
		
//		try {
//			initMap();
//			if(orgazicationDept==null){
//				orgazicationDept = new Bloc();
//			}
//			if(SessionUtils.getUser() == null){
//				return SUCCESS;
//			}
//			if(!SessionUtils.getUser().getLoginname().equals("admin")){
//				orgazicationDept.setUserid(SessionUtils.getUserId());
//			}
//			String sysdate = new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
//			orgazicationDept.setSysdate(sysdate);
//
//			if(deptid !=null && !deptid.equals("null")&& !deptid.equals("undefined")){
//				orgazicationDept.setDeptid(Integer.parseInt(deptid));
//				
//				result = orgazicationDeptService.findPageCarCountList(this.getPage(), this.getLimit(), orgazicationDept);	
//				List<Bloc> list = (List<Bloc>)result.get("list");
//				
//				for(int i=list.size()-1;i>=0;i--){
//					Bloc dept = list.get(i);
//					if(dept.getParentid() == 0 || dept.getDeptid() == Integer.parseInt(deptid)){
//						dept.setContract("");
//						dept.setTel("");
//						dept.setAddress("");
//						dept.setDeptname(dept.getDeptname()+"<font color='red'>总数</font>");
//						break;
//					}
//				}
//			}else {
//				result =null;
//			}
//			
//			return SUCCESS;
//		} catch (Exception e) {
//			logger.error("OrgazicationDeptAction的方法 carstatisticList执行出错，原因：" + e, e);
//			return ERROR;
//		}
		
		return null;
	}
	
	/**
	 * 转移组织机构 deptid带转移的机构id,movedeptid目标父机构id
	 * @return
	 */
	public String deptMove() {
		
		try {
			initMap();
			if (moveblocid == null || "".equals(moveblocid)||blocid == null || "".equals(blocid))
				return ERROR;
			orgazicationDept = new Bloc();
			orgazicationDept.setId(Integer.parseInt(blocid));
			orgazicationDept.setParentid(Integer.parseInt(moveblocid));
			orgazicationDept.setBloc_name(URLDecoder.decode(bloc_name, "UTF-8"));
			
			Integer count = orgazicationDeptService.updateOrgazicationDept(orgazicationDept);
			if(count > 0){
				result.put("su", 1);
			}else{
				result.put("su", -2);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OrgazicationDeptAction的方法 deptMove执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	
	public String  getBlocList(){
	try {
		initMap();
		if(orgazicationDept==null){
			orgazicationDept = new Bloc();
		}
		if(StringUtils.isNotEmty(bloc_name)){
			orgazicationDept.setBloc_name(URLDecoder.decode(bloc_name, "UTF-8"));
		}
		if(StringUtils.isNotEmty(blocid)){
			orgazicationDept.setId(Integer.parseInt(blocid));
		}
		if(SessionUtils.getUser() == null){
			return SUCCESS;
		}
		if(!SessionUtils.getUser().getLoginname().equals("admin")){
			orgazicationDept.setUserid(SessionUtils.getUserId());
		}
		list = orgazicationDeptService.selectOrgazicationDeptList(orgazicationDept);	 
		return SUCCESS;
	} catch (Exception e) {
		logger.error("UserGroupAction 的方法 queryUserGroupList 执行出错，原因：" + e, e);
		return ERROR;
	}
	}
	
	public OrgazicationDeptService getOrgazicationDeptService() {
		return orgazicationDeptService;
	}

	public void setOrgazicationDeptService(
			OrgazicationDeptService orgazicationDeptService) {
		this.orgazicationDeptService = orgazicationDeptService;
	}

	public Bloc getOrgazicationDept() {
		return orgazicationDept;
	}

	public void setOrgazicationDept(Bloc orgazicationDept) {
		this.orgazicationDept = orgazicationDept;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	public String getMoveblocid() {
		return moveblocid;
	}

	public void setMoveblocid(String moveblocid) {
		this.moveblocid = moveblocid;
	}

	public String getBloc_name() {
		return bloc_name;
	}

	public void setBloc_name(String bloc_name) {
		this.bloc_name = bloc_name;
	}

	public List<Bloc> getList() {
		return list;
	}

	public void setList(List<Bloc> list) {
		this.list = list;
	}

	public CmssService getCmssService() {
		return cmssService;
	}

	public void setCmssService(CmssService cmssService) {
		this.cmssService = cmssService;
	}
	
}





