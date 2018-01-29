/**
 * 
 */
package com.careye.advert.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.advert.domain.AdvertCon;
import com.careye.advert.domain.AdvertPos;
import com.careye.advert.domain.AdvertVer;
import com.careye.advert.service.TopLightService;
import com.careye.base.action.BasePageAction;
import com.careye.constant.Constant;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class TopLightAction extends BasePageAction{
private static final Logger logger = Logger.getLogger(AdvertManageAction.class);
	
	private AdvertPos advertPos;
	private AdvertVer advertVer;
	private AdvertCon advertCon;
	private TopLightService topLightService;
	
	private String adname;
	private String position;
	private String stime;
	private String etime;
	private String version;
	private String auditstatus;
	private String onstatus ;
	private Integer adminid;
	private String ids;
	private String success;
	private Map result;
	private String fileName;
	
	private String positionid;
	private String versionid;
	
	

	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * getTypeList 类型下拉列表
	 */
	public String getTypeList(){
		try {
			initMap();
			List list = topLightService.getTypeList();
			result.put("list", list);
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 getTypeList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	/**
	 * getVersionList  版本下拉列表
	 */
	public String getVersionList(){
		try {
			initMap();
			List list = topLightService.getVersionList();
			result.put("list", list);
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 getVersionList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	
	/**
	 * advertUpload
	 */
	
	
	
	
	/**
	 * getAdvertVerList
	 * 广告版本分页查询列表
	 * @return
	 */
	public String getAdvertVerList(){
		try {
			initMap();
			if(advertVer==null){
				advertVer=new AdvertVer();
			}
			if(StringUtils.isNotEmty(version)){
				advertVer.setVersion(URLDecoder.decode(version,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(auditstatus)){
				advertVer.setAuditstatus(Integer.parseInt(auditstatus));
			}
			if(StringUtils.isNotEmty(onstatus)){
				advertVer.setOnstatus(Integer.parseInt(onstatus));
			}
			if(StringUtils.isNotEmty(stime)){
				advertVer.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				advertVer.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			result = topLightService.getAdvertVerList(this.getPage(),this.getLimit(), advertVer);
			
			return SUCCESS;

		} catch (Exception e) {
			logger.error("AdvertManageAction的方法 getAdvertVerList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * saveVer
	 * 保存版本
	 * @return
	 */
	public String saveVer(){
		try {
			initMap();
			if (advertVer == null) {
				return ERROR;
			}
			advertVer.setUserid(SessionUtils.getUserId());
			int record = topLightService.verIsExist(advertVer);
			int record1 = topLightService.veridIsExist(advertVer);
			if (record > 0) {
				// 名称是否存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
				
			}
			if(record1 > 0){
				// 版本号是否存在
				result.put("returnType", 2);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (advertVer.getId() != null) {
				count = topLightService.updateVer(advertVer);
			} else {
//				Integer verid = topLightService.selVerid();
//				
//				Integer v = verid+1;
//				advertVer.setVerid(v);
				
				
				count = topLightService.addVer(advertVer);
			}
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("AdvertManageAction的方法 saveVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * deleteVer
	 * 删除版本
	 * @return
	 */
	public String deleteVer(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int count = topLightService.queryVer(Integer.parseInt(id[i]));
				if(count==0){
					int re = topLightService.deleteVer(Integer.parseInt(id[i]));
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 deleteVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * exportVer
	 * 导出版本
	 * @return
	 */
	public void exportVer(){
		try {
			// 1.设置名字
			fileName = "顶灯广告版本列表";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,25,25,25,25,25,25,25,25,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);
			

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			if(advertVer==null){
				advertVer=new AdvertVer();
			}
			if(StringUtils.isNotEmty(version)){
				advertVer.setVersion(URLDecoder.decode(version,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(auditstatus)){
				advertVer.setAuditstatus(Integer.parseInt(auditstatus));
			}
			if(StringUtils.isNotEmty(onstatus)){
				advertVer.setOnstatus(Integer.parseInt(onstatus));
			}
			if(StringUtils.isNotEmty(stime)){
				advertVer.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				advertVer.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(positionid)){
				advertVer.setPositionidStr(URLDecoder.decode(positionid,"UTF-8"));
			}
			List<AdvertVer> list= topLightService.exportVer(advertVer);

//			 String fileName="广告版本表"; 
//		   	 HSSFWorkbook book = new HSSFWorkbook();
//		     Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
//		     Row titleRow= sheet.createRow(0);
		        
		        titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
		        titleRow.createCell(1).setCellValue("版本名称");
		        titleRow.createCell(2).setCellValue("显示位置");
		        titleRow.createCell(3).setCellValue("到期日期");
		        titleRow.createCell(4).setCellValue("发布人");
		        titleRow.createCell(5).setCellValue("发布时间");
		        titleRow.createCell(6).setCellValue("在线状态");
		        titleRow.createCell(7).setCellValue("描述");
		        titleRow.createCell(8).setCellValue("创建时间");
		        titleRow.createCell(9).setCellValue("审核状态");
		        titleRow.createCell(10).setCellValue("审核人");
		        titleRow.createCell(11).setCellValue("审核时间");
		        titleRow.createCell(12).setCellValue("审核描述");
		        
		        sheet.setDefaultColumnWidth(20);  
		        sheet.setDefaultRowHeightInPoints(20); 
		        
		        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
					titleRow.getCell(i).setCellStyle(
							exceldownway.setBookHeadStyle(book));
				}
		        
		        if(list.size()>0){
		            for(int i=0;i<list.size();i++){
		                int  index=i+1;
		                Row contentRow= sheet.createRow(index);
		                contentRow.createCell(0).setCellValue(index);
		                AdvertVer data= (AdvertVer) list.get(i);
		                contentRow.createCell(1).setCellValue(data.getVersion());
		                contentRow.createCell(2).setCellValue(data.getPosition());
		                contentRow.createCell(3).setCellValue(data.getDtime());
		                contentRow.createCell(4).setCellValue(data.getUsername());
		               
		                
		                contentRow.createCell(5).setCellValue(data.getReltime());
		                
//		                contentRow.createCell(7).setCellValue(data.getOnstatus());
		                if (data.getOnstatus()!= null) {
		                	Integer statu = data.getOnstatus();
			                String statuString ="";
			                if (statu==1) {
			                	statuString = "上架";
							} else if (statu==2) {
								statuString = "下架";
							} else {
								statuString = "";
							}
			                contentRow.createCell(6).setCellValue(statuString);
						}else {
			                contentRow.createCell(6).setCellValue("");
						}
		                
		                contentRow.createCell(7).setCellValue(data.getRemark());
		                contentRow.createCell(8).setCellValue(data.getCreatetime());
		                
//		                contentRow.createCell(9).setCellValue(data.getAuditstatus());
		                if (data.getAuditstatus()!=null) {
		                	Integer statu = data.getAuditstatus();
			                String statuString ="";
			                if (statu==0) {
			                	statuString = "未审核";
							} else if (statu==1) {
								statuString = "审核通过";
							} else if (statu==2) {
								statuString = "审核不通过";
							} else {
								statuString = "";
							}
			                contentRow.createCell(9).setCellValue(statuString);
						}else {
			                contentRow.createCell(9).setCellValue("");
						}
		                
		                contentRow.createCell(10).setCellValue(data.getAuditname());
		                contentRow.createCell(11).setCellValue(data.getAudittime());
		                contentRow.createCell(12).setCellValue(data.getAuditremark());
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
	 * saveAuditVer
	 * 审核版本
	 * @return
	 */
	public String saveAuditVer(){
		try {
			initMap();
			if (advertVer == null) {
				return ERROR;
			}
			advertVer.setAdminid(SessionUtils.getUserId());
			int count = topLightService.saveAuditVer(advertVer);
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("AdvertManageAction的方法 saveVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 上架版本
	 * @return
	 */
	public String activeVer(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				topLightService.activeVer(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 activeVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	/**
	 * 下架版本
	 * @return
	 */
	public String inactiveVer(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				topLightService.inactiveVer(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 inactiveVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	
	
	
	/**
	 * getAdvertConList
	 * 广告版本分页查询列表
	 * @return
	 */
	public String getAdvertConList(){
		try {
			initMap();
			if(advertCon==null){
				advertCon=new AdvertCon();
			}
			if(StringUtils.isNotEmty(adname)){
				advertCon.setAdname(URLDecoder.decode(adname,"UTF-8").trim());
			}
//			if(StringUtils.isNotEmty(auditstatus)){
//				advertCon.setAuditstatus(Integer.parseInt(auditstatus));
//			}
//			if(StringUtils.isNotEmty(onstatus)){
//				advertCon.setOnstatus(Integer.parseInt(onstatus));
//			}
			if(StringUtils.isNotEmty(stime)){
				advertCon.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				advertCon.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(versionid)){
				advertCon.setVersionidStr(URLDecoder.decode(versionid,"UTF-8"));
			}
			
			result = topLightService.getAdvertConList(this.getPage(),this.getLimit(), advertCon);
			
			return SUCCESS;

		} catch (Exception e) {
			logger.error("AdvertManageAction的方法 getAdvertVerList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * saveCon
	 * 保存广告内容
	 * @return
	 */
	public String saveCon(){
		try {
			initMap();
			if (advertCon == null) {
				return ERROR;
			}
			
			advertCon.setUserid(SessionUtils.getUserId());
			String typename = "文字广告";
			advertCon.setTypeid(topLightService.selTypeid(typename));
			int record = topLightService.conIsExist(advertCon);
			if (record > 0) {
				// 名称是否存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (advertCon.getId() != null) {
				count = topLightService.updateCon(advertCon);
			} else {
				Integer pid = Constant.TOPLIGHT;
				Integer connumber = topLightService.selconnumber(pid);
//				
				Integer v = connumber+1;
				advertCon.setConnumber(v);
				count = topLightService.addCon(advertCon);
			}
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("AdvertManageAction的方法 saveCon执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * deleteCon 删除广告内容
	 * @return
	 */
	public String deleteCon(){
		try {
			initMap();
			String id[] = ids.split(",");
			
			for (int i = 0; i < id.length; i++) {
				int count = topLightService.deleteCon(Integer.parseInt(id[i]));
				if(count>0){
					result.put("su", 0);
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AdvertManageAction 的方法 deleteVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public AdvertPos getAdvertPos() {
		return advertPos;
	}



	public void setAdvertPos(AdvertPos advertPos) {
		this.advertPos = advertPos;
	}



	public AdvertVer getAdvertVer() {
		return advertVer;
	}



	public void setAdvertVer(AdvertVer advertVer) {
		this.advertVer = advertVer;
	}



	public AdvertCon getAdvertCon() {
		return advertCon;
	}



	public void setAdvertCon(AdvertCon advertCon) {
		this.advertCon = advertCon;
	}



	public TopLightService getTopLightService() {
		return topLightService;
	}



	public void setTopLightService(TopLightService topLightService) {
		this.topLightService = topLightService;
	}



	public String getAdname() {
		return adname;
	}



	public void setAdname(String adname) {
		this.adname = adname;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
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



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getAuditstatus() {
		return auditstatus;
	}



	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}



	public String getOnstatus() {
		return onstatus;
	}



	public void setOnstatus(String onstatus) {
		this.onstatus = onstatus;
	}



	public Integer getAdminid() {
		return adminid;
	}



	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public String getSuccess() {
		return success;
	}



	public void setSuccess(String success) {
		this.success = success;
	}



	public Map getResult() {
		return result;
	}



	public void setResult(Map result) {
		this.result = result;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getPositionid() {
		return positionid;
	}



	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}



	public String getVersionid() {
		return versionid;
	}



	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}



	public static Logger getLogger() {
		return logger;
	}
	
	
	
	
}
