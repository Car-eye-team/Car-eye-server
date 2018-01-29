/**
 * 
 */
package com.careye.advert.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.careye.advert.domain.AdvertCon;
import com.careye.advert.domain.AdvertPos;
import com.careye.advert.domain.AdvertVer;
import com.careye.advert.service.AdvertManageService;
import com.careye.advert.service.HostService;
import com.careye.base.action.BasePageAction;
import com.careye.constant.Constant;
import com.careye.constant.WebConstants;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.FileHandler;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class HostAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(AdvertManageAction.class);
	
	private AdvertPos advertPos;
	private AdvertVer advertVer;
	private AdvertCon advertCon;
	private HostService hostService;
	
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
	
	private String fileInputFileName;
	private File fileInput;
	private String Filename;
	private String Upload;
	private String fileInputContentType;
	private String fileext;
	private String folder;

	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 广告图片上传
	 */
	public void advertUpload(){
		try {
			 HttpServletResponse response = ServletActionContext.getResponse();   
			  response.setCharacterEncoding("utf-8"); 
			  //上传文件保存路径
			  String savePath = ServletActionContext.getServletContext().getRealPath("");
			  String folder = "/"+WebConstants.scrollAdvertDir + "/" + WebConstants.SCROLLADVERT + "/";
			  savePath = savePath + folder;
			  //判断文件夹是否存在,如果不存在则创建文件夹
			  File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
			  String picturename = FileHandler.getRandomFileName() + FileHandler.getFileExtName(fileInputFileName);
			  fileInput.renameTo(new File(savePath + picturename));
			  //返回数据
			  response.getWriter().println(folder + picturename); 
				
			  	
		} catch (Exception e) {
			logger.error("HostAction的方法 advertUpload执行出错，原因：" + e, e);
		}
	}
	
	

	
	
	
	
	/**
	 * getTypeList 类型下拉列表
	 */
	public String getTypeList(){
		try {
			initMap();
			List list = hostService.getTypeList();
			result.put("list", list);
		} catch (Exception e) {
			logger.error("HostAction 的方法 getTypeList 执行出错，原因：" + e, e);
		}
		return SUCCESS;
	}
	
	
	
	
	
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
			result = hostService.getAdvertVerList(this.getPage(),this.getLimit(), advertVer);
			
			return SUCCESS;

		} catch (Exception e) {
			logger.error("HostAction的方法 getAdvertVerList执行出错，原因：" + e, e);
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
			int record = hostService.verIsExist(advertVer);
			int record1 = hostService.veridIsExist(advertVer);
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
				count = hostService.updateVer(advertVer);
			} else {
//				Integer verid = hostService.selVerid();
//				
//				Integer v = verid+1;
//				advertVer.setVerid(v);
				
				
				count = hostService.addVer(advertVer);
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
			logger.error("HostAction的方法 saveVer执行出错，原因：" + e, e);
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
				int count = hostService.queryVer(Integer.parseInt(id[i]));
				if(count==0){
					int re = hostService.deleteVer(Integer.parseInt(id[i]));
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("HostAction 的方法 deleteVer执行出错，原因：" + e, e);
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
			fileName = "主机广告版本列表";
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
			List<AdvertVer> list= hostService.exportVer(advertVer);

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
			int count = hostService.saveAuditVer(advertVer);
			if (count > 0) {
				result.put("returnType", 0);
				this.success = "true";
			} else {
				result.put("returnType", -2);
			}

			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("HostAction的方法 saveVer执行出错，原因：" + e, e);
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
				hostService.activeVer(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("HostAction 的方法 activeVer执行出错，原因：" + e, e);
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
				hostService.inactiveVer(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("HostAction 的方法 inactiveVer执行出错，原因：" + e, e);
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
			
			result = hostService.getAdvertConList(this.getPage(),this.getLimit(), advertCon);
			
			return SUCCESS;

		} catch (Exception e) {
			logger.error("HostAction的方法 getAdvertVerList执行出错，原因：" + e, e);
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
//			String savePath = ServletActionContext.getServletContext().getRealPath("");
//			String folder = advertCon.getPath();
//			savePath = savePath + folder;
//			File file = new File(savePath);
//			FileInputStream fis = new FileInputStream(file);
//			BufferedImage bufferedImg = ImageIO.read(fis);
//				int imgWidth = bufferedImg.getWidth();
//				int imgHeight = bufferedImg.getHeight();
//				if(imgWidth != 800 && imgHeight !=480){
//					result.put("returnType",3);
//					this.success = "true";
//					return SUCCESS;
//				}
			
			advertCon.setUserid(SessionUtils.getUserId());
			int record = hostService.conIsExist(advertCon);
			if (record > 0) {
				// 名称是否存在
				result.put("returnType", 1);
				this.success = "true";
				return SUCCESS;
			}
			int count = 0;
			if (advertCon.getId() != null) {
				count = hostService.updateCon(advertCon);
			} else {
				Integer pid = Constant.HOSTADV;
				Integer connumber = hostService.selconnumber(pid);
//				
				Integer v = connumber+1;
				advertCon.setConnumber(v);
				count = hostService.addCon(advertCon);
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
			logger.error("HostAction的方法 saveCon执行出错，原因：" + e, e);
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
				int count = hostService.deleteCon(Integer.parseInt(id[i]));
				if(count>0){
					result.put("su", 0);
				}else {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("HostAction 的方法 deleteVer执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	public AdvertPos getAdvertPos() {
		return advertPos;
	}
	public void setAdvertPos(AdvertPos advertPos) {
		this.advertPos = advertPos;
	}
	
	public HostService getHostService() {
		return hostService;
	}

	public void setHostService(HostService hostService) {
		this.hostService = hostService;
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

	public AdvertVer getAdvertVer() {
		return advertVer;
	}

	public void setAdvertVer(AdvertVer advertVer) {
		this.advertVer = advertVer;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AdvertCon getAdvertCon() {
		return advertCon;
	}

	public void setAdvertCon(AdvertCon advertCon) {
		this.advertCon = advertCon;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public String getUpload() {
		return Upload;
	}

	public void setUpload(String upload) {
		Upload = upload;
	}

	public String getFileInputContentType() {
		return fileInputContentType;
	}

	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
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
}
