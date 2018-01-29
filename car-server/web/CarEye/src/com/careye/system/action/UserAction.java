/**
* Description: car-eye车辆管理平台
* 文件名：UserAction.java
* 版本信息：1.0
* 日期：2014-2-19
* Copyright car-eye 车辆管理平台 Copyright (c) 2013
* 版权所有
*/
package com.careye.system.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.common.service.UserLoginService;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.system.domain.UserCar;
import com.careye.system.domain.UserContact;
import com.careye.system.domain.WindowSet;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：UserAction
 * @类描述：
 * @创建人：zhangrong
 * @创建时间：2013-9-9 下午05:52:38
 * @修改人：zhangrong
 * @修改时间：2013-9-9 下午05:52:38
 * @修改备注：
 * @version 1.0
 */
public class UserAction extends BasePageAction{
	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private UserService userService;
	private UserLoginService userLoginService;
	
	private BlocUser user;
	private UserCar userCar;
	private SysAuthLoginLog loginLog;
	private UserContact userContact;
	private UserContact data;
	private Map result;
	private String success;
	private String ids;
	
	private String loginname;
	private String username;
	private String deptname;
	private String blocname;
	
	private String stime;
	private String etime;
	private String blocid;
	private String usergroupname;
	private String carnumber;
	
	private String userid;
	private String password;
	private String category;
	private String type;
	private String status;
	private String blocgroupname;
	
	
	private FileInputStream downloadFile;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	

	/**
	 * 修改报警声音
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateUserWarnswitch() {
		
		try {
			initMap();
			if (user == null)
				user = new BlocUser();
			if (StringUtils.isNotEmty(userid)) {
				user.setUserid(Integer.parseInt(userid));
			}
			if (StringUtils.isNotEmty(status)) {
				user.setWarnswitch(Integer.parseInt(status));
			}
		    int re = userService.updateUserWarnswitch(user);
		    
		    if(re > 0){
		    	//刷新session信息
				BlocUser userInfo = userLoginService.getUserInfo(SessionUtils.getUser());
				if(userInfo != null){
					SessionUtils.put(WebConstants.LOGIN_USER, userInfo);
				}
		    }
		    
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("UserAction的方法 updateUserWarnswitch执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 报警提示设置分页用户列表
	 * @return
	 */
	public String userListPrompt() {
		
		try {
			initMap();
			if(user==null){
				user = new BlocUser();
			}
			if(loginname !=null){
				user.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(username !=null){
				user.setUsername(URLDecoder.decode(username, "UTF-8").trim());
			}
			if(blocid !=null && !blocid.equals("null")&& !blocid.equals("undefined")){
				user.setBlocid(Integer.parseInt(blocid));
			}
			if(blocgroupname!=null && !"".equals(blocgroupname)){
				user.setBlocgroupname(URLDecoder.decode(blocgroupname, "UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				user.setUserid(SessionUtils.getUserId());
			}
			result = userService.findPageUserListPrompt(this.getPage(), this.getLimit(), user);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction的方法 userListPrompt执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 用户列表
	 * @return
	 */
	public String userList() {
		
		try {
			initMap();
			if(user==null){
				user = new BlocUser();
			}
			if(loginname !=null){
				user.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(username !=null){
				user.setUsername(URLDecoder.decode(username, "UTF-8").trim());
			}
			if(blocid !=null && !blocid.equals("null")&& !blocid.equals("undefined")){
				user.setBlocid(Integer.parseInt(blocid));
			}
			if(blocgroupname!=null && !"".equals(blocgroupname)){
				user.setBlocgroupname(URLDecoder.decode(blocgroupname, "UTF-8").trim());
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				user.setUserid(SessionUtils.getUserId());
			}
			user.setId(SessionUtils.getUserId());
			result = userService.findPageUserList(this.getPage(), this.getLimit(), user);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction的方法 userList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/*
	 * 界面展开列表
	 * @return
	 */
	public String findPageSetList() {
		
		try {
			initMap();
			if(user==null){
				user = new BlocUser();
			}
			if(loginname !=null){
				user.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(username !=null){
				user.setUsername(URLDecoder.decode(username, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(blocid)){
				user.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(blocgroupname)){
				user.setBlocgroupname(URLDecoder.decode(blocgroupname, "UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
//			if(!SessionUtils.getUser().getLoginname().equals("admin")){
//				user.setUserid(SessionUtils.getUserId());
//			}
			
			if(StringUtils.isNotEmty(userid)){
				user.setUserid(Integer.parseInt(userid));
			}
			
			result = userService.findPageSetList(this.getPage(), this.getLimit(), user);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction的方法 userList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	/**
	 * 增加、修改用户
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addOrUpdateUser() {
		
		try {
			initMap();
			if (user == null)
				return ERROR;
			//检查用户名是否重复
			int record = userService.loginNameIsExist(user);
			if (record > 0) {
				//用户名已经存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if(user.getId()!=null){
				 count = userService.updateUser(user);
				 
				 if(SessionUtils.getUserId() == user.getId()){
					//更新成功后更新session信息
					 user = userLoginService.getUserInfo(user);
					 SessionUtils.updateUser(user);
				 }
				 
			}else{
				 count = userService.addUser(user);
			}
			if(count > 0){
				result.put("returnType", 0);
				this.success = "true";
			}else{
				result.put("returnType", -1);
			}
			return SUCCESS;
		} catch (Exception e) {
			this.success = "false";
			logger.error("UserAction的方法 addOrUpdateUser执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 删除用户信息
	 * @return
	 */
	public String deleteUser(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.deleteUser(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 deleteUser执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 激活用户
	 * @return
	 */
	public String activeUser(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.activeUser(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 activeUser执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 禁用用户
	 * @return
	 */
	public String inactiveUser(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.inactiveUser(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 inactiveUser执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 用户登陆日志列表
	 * @return
	 */
	public String loginLogList() {
		
		try {
			initMap();
			if(loginLog == null){
				loginLog = new SysAuthLoginLog();
			}
			if(StringUtils.isNotEmty(loginname)){
				loginLog.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(blocname)){
				loginLog.setBlocname(URLDecoder.decode(blocname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				loginLog.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				loginLog.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				loginLog.setUserid(SessionUtils.getUserId());
			}
			result = userService.findPageLoginLogList(this.getPage(), this.getLimit(), loginLog);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction的方法 userList执行出错，原因：" + e, e);
			return ERROR;
		}
		
	}

	/**
	 * 用户登陆日志导出·
	 * @return
	 */
	public void loginLogExport() {
		
		try {
			initMap();
			if(loginLog == null){
				loginLog = new SysAuthLoginLog();
			}
			if(StringUtils.isNotEmty(loginname)){
				loginLog.setLoginname(URLDecoder.decode(loginname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(blocname)){
				loginLog.setBlocname(URLDecoder.decode(blocname, "UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				loginLog.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				loginLog.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(userid)) {
				loginLog.setId(Integer.parseInt(userid));
			} 

			List<SysAuthLoginLog> list = userService.exportLoginLogList(loginLog);

		    fileName = "用户登陆日志报表";
		   	 HSSFWorkbook book = new HSSFWorkbook();
		     Sheet sheet= book.createSheet(fileName);
		     

		        ExcelDownWay exceldownway= new ExcelDownWay();
		         
//		         //2.设置列宽（列数要对应上）
//		         String str="7,15,15,15,20,20,15,15,15,25";
//		         List<String> numberList=Arrays.asList(str.split(","));
//		         sheet= exceldownway.setColumnWidth(sheet,numberList);
//		         sheet.setDefaultRowHeight((short) 20);
			     Row titleRow= sheet.createRow(0);
		         titleRow.setHeightInPoints(20);
		         
		      // 设置列宽    
		         sheet.setColumnWidth(0, 2000);    
		         sheet.setColumnWidth(1, 3500);    
		         sheet.setColumnWidth(2, 3500);    
		         sheet.setColumnWidth(3, 6500);    
		         sheet.setColumnWidth(4, 5500);    
		         sheet.setColumnWidth(5, 3000);    
		         sheet.setColumnWidth(6, 4000);    
		         sheet.setColumnWidth(7, 4500);   
		         sheet.setColumnWidth(8, 5500);    
		        /* // Sheet样式    
		         HSSFCellStyle sheetStyle = book.createCellStyle();    
		         // 背景色的设定    
		         sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);    
		         // 前景色的设定    
		         sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);    
		         // 填充模式    
		         sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);    
		         // 设置列的样式    
		         for (int i = 0; i <= 14; i++) {    
		           sheet.setDefaultColumnStyle((short) i, sheetStyle);    
		         }   */
		         
		         // 字体样式    
		         HSSFFont columnHeadFont = book.createFont();    
		         columnHeadFont.setFontName("宋体");    
		         columnHeadFont.setFontHeightInPoints((short) 10);    
		         columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		         // 列头的样式    
		         HSSFCellStyle columnHeadStyle = book.createCellStyle();    
		         columnHeadStyle.setFont(columnHeadFont);    
		         columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
		         columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中    
		         columnHeadStyle.setLocked(true);    
		         columnHeadStyle.setWrapText(true);    
		         columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色    
		         columnHeadStyle.setBorderLeft((short) 1);// 边框的大小    
		         columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色    
		         columnHeadStyle.setBorderRight((short) 1);// 边框的大小    
		         columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体    
		         columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色    
		         // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）    
		         columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		         columnHeadStyle.setFillForegroundColor(HSSFColor.GREEN.index);    
		         
		         HSSFFont font = book.createFont();    
		         font.setFontName("宋体");    
		         font.setFontHeightInPoints((short) 10);    
		         // 普通单元格样式    
		         HSSFCellStyle style = book.createCellStyle();    
		         style.setFont(font);    
		         style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中    
		         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中    
		         style.setWrapText(true);    
		         style.setLeftBorderColor(HSSFColor.BLACK.index);    
		         style.setBorderLeft((short) 1);    
		         style.setRightBorderColor(HSSFColor.BLACK.index);    
		         style.setBorderRight((short) 1);    
		         style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体    
		         style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．    
		         style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．    
		         
			titleRow.createCell(0).setCellValue("序号");
	        titleRow.createCell(1).setCellValue("用户登陆名");
	        titleRow.createCell(2).setCellValue("用户名");
	        titleRow.createCell(3).setCellValue("集团");
	        titleRow.createCell(4).setCellValue("登陆时间");
	        titleRow.createCell(5).setCellValue("登陆IP");
	        titleRow.createCell(6).setCellValue("登陆状态");
	        titleRow.createCell(7).setCellValue("状态");
	        titleRow.createCell(8).setCellValue("备注");

	        
	         for(int i=0;i<titleRow.getLastCellNum();i++){
//	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	        	 titleRow.getCell(i).setCellStyle(columnHeadStyle);
	         }
	        
	        if(list.size()>0){
	            for(int i=0;i<list.size();i++){
	                int  index=i+1;
	                Row contentRow= sheet.createRow(index);
	                contentRow.setHeightInPoints(15);
	                contentRow.createCell(0).setCellValue(index);
	                SysAuthLoginLog data= (SysAuthLoginLog) list.get(i);
	                contentRow.createCell(1).setCellValue(data.getLoginname());
	                contentRow.createCell(2).setCellValue(data.getUsername());
	                contentRow.createCell(3).setCellValue(data.getBlocname());
	                contentRow.createCell(4).setCellValue(data.getLogindate());
	                contentRow.createCell(5).setCellValue(data.getLoginip());
	                contentRow.createCell(6).setCellValue(data.getLoginflag());
	                if (data.getStatus() == 2 || data.getStatus().equals(2)) {
	                	contentRow.createCell(7).setCellValue("退出");
					} else {
	                	contentRow.createCell(7).setCellValue("登陆");
					}
	                contentRow.createCell(8).setCellValue(data.getRemark());

	                 for(int m=0;m<contentRow.getLastCellNum();m++){
//	                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
	                	 contentRow.getCell(m).setCellStyle(style);
	                 }
	                
	            }
	        }
	        
	       new ExcelDownWay().getCommonExcelListWay(book,fileName);
	       
	       
			} catch (Exception e) {
				try {
					getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	}

	/**
	 * 删除用户登陆日志信息
	 * @return
	 */
	public String deleteLoginLog(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.deleteLoginLog(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 deleteLoginLog执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 清除session
	 * @return
	 */
	public String clearSession(){
		SessionUtils.put(WebConstants.LOGIN_USER, null);
		return SUCCESS;
	}
	
	/**
	 * 解锁用户
	 * @return
	 */
	public String openLockUser(){
		initMap();
		user = new BlocUser();
		user.setUserid(Integer.parseInt(userid));
		user.setPassword(password);
		BlocUser userInfo = userService.getUserDetatail(user);
		if(userInfo == null){
			result.put("su", -1);
		}else{
			SessionUtils.put(WebConstants.LOGIN_USER, userInfo);
			result.put("su", 1);
		}
		return SUCCESS;
	}
	
	/**分配车辆**/
	public String assignUserCar() {
		try {
			initMap();
			if (userid == null || ids == null)
				return ERROR;
			//先删除该用户下所能查看的车辆
		    userService.delUserCar(Integer.parseInt(userid));
		    
		    List<UserCar> list = new ArrayList<UserCar>();  
		    for(String str : ids.split(",")){  
		    	if(!str.equals("root") && (str.indexOf("C")==0)){
		    		UserCar userCar = new UserCar();
		    		userCar.setUserid(Integer.parseInt(userid));
		    		userCar.setCarid(Integer.parseInt(str.substring(1)));
		    		userCar.setCreatetime(DateUtil.getSQLDate());
		    		list.add(userCar);  
		    	}
		    }  
		    if(list.size() > 0){
		    	userService.addUserCar(list);
		    }
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 assignUserCar 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**关闭提醒类型**/
	public String assignRemind() {
		try {
			initMap();
			if (userid == null || ids == null)
				return ERROR;
			//先删除用户所能关闭提醒类型
			userService.delAssignRemind(Integer.parseInt(userid));
			
			List<WindowSet> list = new ArrayList<WindowSet>();  
			for(String str : ids.split(",")){  
				if(!str.equals("root")){
					WindowSet windowSet = new WindowSet();
					windowSet.setUserid(Integer.parseInt(userid));
					if(str.indexOf("2_") != -1){	//系统消息提醒
						windowSet.setCategory(2);
						windowSet.setType(str);
					}else if(str.equals("2")){	
						windowSet.setCategory(2);
						windowSet.setType("2");
					}else if(str.equals("3")){	//系统公告信息
						windowSet.setCategory(3);
					}else if(str.equals("4")){	//车辆温度报警
						windowSet.setCategory(4);
					}else{	//系统报警
						windowSet.setCategory(1);
						windowSet.setType(str);
					}
					list.add(windowSet);  
				}
			}  
			if(list.size()>0){
				userService.addAssignRemind(list);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 assignUserCar 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 查看指定用户提醒类型打开关闭状态，1打开 2 关闭
	 * @return
	 */
	public String queryRemindStatus(){
		try{
			initMap();
			WindowSet windowSet = new WindowSet();
			if(userid == null || "".equals(userid) || "null".equals(userid)){
				return ERROR;
			}
			if(category == null || "".equals(category) || "null".equals(category)){
				return ERROR;
			}
			windowSet.setUserid(Integer.parseInt(userid));
			windowSet.setCategory(Integer.parseInt(this.category));
			if(type!= null && !"".equals(type)){
				if(category.equals("1")){
					type = userService.getAlarmkeyByName(type);
				}
				windowSet.setType(type);
			}
			String value = userService.queryRemindStatus(windowSet);
			result.put("value", value);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 queryRemindStatus 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 保存用户联系人
	 * @param carInfo
	 * @return
	 */
	public String saveUserContact(){
		try {
			initMap();
			if(userContact == null){
				return ERROR;
			}
			Integer su = userService.saveUserContact(userContact);
			result.put("su", su);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("UserAction的方法 saveUserContact执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 加载用户联系人信息
	 * @return
	 */
	public String loadUserContact(){
		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return ERROR;
			}
			data = userService.loadUserContact(SessionUtils.getUserId());
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("UserAction的方法 loadUserContact执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 用户车辆列表 type 1 内部车辆 2 平台车辆
	 * @return
	 */
	public String getPtcarList() {
		
		try {
			initMap();
			if(userCar==null){
				userCar = new UserCar();
			}
			if(userid==null){
				return SUCCESS;
			}
			if(type==null){
				return SUCCESS;
			}else{
				userCar.setType(Integer.parseInt(type));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				userCar.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				userCar.setUserid(Integer.parseInt(userid));
			}
			result = userService.findPagePtcarList(this.getPage(), this.getLimit(), userCar);	
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction的方法 userList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**平台车辆分配**/
	public String assigntpcar() {
		try {
			initMap();
			if (userid == null || ids == null)
				return ERROR;
		    
		    List<UserCar> list = new ArrayList<UserCar>();  
		    for(String str : ids.split(",")){  
		    	if(!str.equals("root") && (str.indexOf("C")==0)){
		    		UserCar userCar = new UserCar();
		    		userCar.setUserid(Integer.parseInt(userid));
		    		userCar.setCarid(Integer.parseInt(str.substring(1)));
		    		userCar.setCreatetime(DateUtil.getSQLDate());
		    		userCar.setType(2);
		    		int count = userService.getPtcarCount(userCar);
		    		if(count == 0){
		    			list.add(userCar);  
		    		}
		    	}
		    }  
		    if(list.size() > 0){
		    	userService.addUserCar(list);
		    }
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 assigntpcar 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除平台车辆
	 * @return
	 */
	public String deletePtcar(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.deletePtcar(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 deletePtcar执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**内部车辆分配**/
	public String assignNbcar() {
		try {
			initMap();
			if (userid == null || ids == null)
				return ERROR;
			
			List<UserCar> list = new ArrayList<UserCar>();  
			for(String str : ids.split(",")){  
				if(!str.equals("root") && (str.indexOf("C")==0)){
					UserCar userCar = new UserCar();
					userCar.setUserid(Integer.parseInt(userid));
					userCar.setCarid(Integer.parseInt(str.substring(1)));
					userCar.setCreatetime(DateUtil.getSQLDate());
					userCar.setType(1);
					int count = userService.getPtcarCount(userCar);
					if(count == 0){
						list.add(userCar);  
					}
				}
			}  
			if(list.size() > 0){
				userService.addUserCar(list);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 assigntpcar 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除平台车辆
	 * @return
	 */
	public String deleteNbcar(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				userService.deleteNbcar(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 deletePtcar执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**查询用户是否进行了平台车辆分配**/
	public String queryUserPtcarIsAssign() {
		try {
			initMap();
			if (userid == null)
				return ERROR;
			int count = userService.queryUserPtcarIsAssign(Integer.parseInt(userid));
			result.put("su", count);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("UserAction 的方法 queryUserPtcarIsAssign 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BlocUser getUser() {
		return user;
	}

	public void setUser(BlocUser user) {
		this.user = user;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getBlocid() {
		return blocid;
	}



	public void setBlocid(String blocid) {
		this.blocid = blocid;
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

	public SysAuthLoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(SysAuthLoginLog loginLog) {
		this.loginLog = loginLog;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public UserLoginService getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserContact getUserContact() {
		return userContact;
	}

	public void setUserContact(UserContact userContact) {
		this.userContact = userContact;
	}

	public UserContact getData() {
		return data;
	}

	public void setData(UserContact data) {
		this.data = data;
	}

	public String getUsergroupname() {
		return usergroupname;
	}

	public void setUsergroupname(String usergroupname) {
		this.usergroupname = usergroupname;
	}

	public UserCar getUserCar() {
		return userCar;
	}

	public void setUserCar(UserCar userCar) {
		this.userCar = userCar;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}



	public String getBlocname() {
		return blocname;
	}



	public void setBlocname(String blocname) {
		this.blocname = blocname;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getBlocgroupname() {
		return blocgroupname;
	}



	public FileInputStream getDownloadFile() {
		return downloadFile;
	}



	public void setDownloadFile(FileInputStream downloadFile) {
		this.downloadFile = downloadFile;
	}



	public void setBlocgroupname(String blocgroupname) {
		this.blocgroupname = blocgroupname;
	}

}
