/**
 * Description: car-eye车辆管理平台
 * 文件名：SetAction.java
 * 版本信息：1.0
 * 日期：2014-7-13
 * Copyright car-eye 车辆管理平台 Copyright (c) 2014
 * 版权所有
 */
package com.careye.sysset.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.constant.WebConstants;
import com.careye.mq.HandleUtil;
import com.careye.sysset.domain.AlarmtypeSet;
import com.careye.sysset.domain.DeviceType;
import com.careye.sysset.domain.PageSet;
import com.careye.sysset.domain.PaymentParamSet;
import com.careye.sysset.domain.VideoParamSet;
import com.careye.sysset.service.SetService;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.ExceptionUtil;
import com.careye.utils.FileHandler;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：car-eye
 * @类名称：SetAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 上午09:30:07
 * @修改人：huangqin
 * @修改时间：2015-3-9 上午09:30:07
 * @修改备注：
 * @version 1.0
 */
public class SetAction extends BasePageAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SetAction.class);
	private SetService setService;
	private CarService carService;
	private CarInfo carInfo;
	private DeviceType deviceType;
	private String type;
	private String ids;
	private String success;
	private String typename;
	private String usertype;
	private String company;
	private String inteltype;
	private Map result;

	private AlarmtypeSet alarmmusicSet;
	private String stime;
	private String etime;
	private String alarmkey;
	private String alarmname;
	private PageSet pageSet;
	private String userid;
	
	private String folder;
	private String fileext;
	private String fileInputContentType;
	private String Upload;
	private String Filename;
	private File fileInput;   
	private String fileInputFileName;
	
	private Integer count;
	private String items;
	
	private PaymentParamSet paymentParamSet;
	private PaymentParamSet data;
	private String id;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

/*
	*//**
	 * 报警设置
	 * @return
	 *//*
	public String saveAlarmInterval(){
		try {
			initMap();
			if(alarmInterval==null){
				alarmInterval=new AlarmInterval();
			}
			
			if(setParamSet==null){
				setParamSet=new SetParamSet();
			}
			
			setParamSet.setSpskey(1);
			setParamSet.setSpsvalue(String.valueOf(alarmInterval.getInterval()));
			int count = setService.updateSetParam(setParamSet);
			if(count<=0){
				result.put("su", -1);
			}else{
				result.put("su", 1);
			}
			this.success="true";
			return SUCCESS;

		} catch (Exception e) {
			this.success="false";
			logger.error("SetAction的方法 saveTextInfo执行出错，原因：" + e, e);
			return ERROR;
		}
	}*/
	
	
/*************************报警类型设置***************************/
	
	/**
	 * 报警类型列表分页
	 * @return
	 */
	public String alarmmusicSetList(){
		try {
			initMap();
			if(alarmmusicSet==null){
				alarmmusicSet = new AlarmtypeSet();
			}
			if(StringUtils.isNotEmty(alarmkey)){
				alarmmusicSet.setAlarmkey(URLDecoder.decode(alarmkey, "utf-8"));
			}
			if(StringUtils.isNotEmty(alarmname)){
				alarmmusicSet.setAlarmname(URLDecoder.decode(alarmname, "utf-8").trim());
			}
//			if(stime!=null){
//				alarmmusicSet.setStime(URLDecoder.decode(stime, "UTF-8"));
//			}
//			if(etime!=null){
//				alarmmusicSet.setEtime(URLDecoder.decode(etime, "UTF-8"));
//			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				alarmmusicSet.setUserid(SessionUtils.getUserId());
			}	
			result = setService.findPageAlarmmusicSetList(this.getPage(), this.getLimit(),alarmmusicSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SetAction的方法 alarmmusicSetList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 保存报警类型
	 * @return
	 */
	public String saveAlarmmusicSet(){

		try {
			initMap();
			if(alarmmusicSet==null){
				return SUCCESS;
			}
			int re = 0;
			int isname = setService.alarmNameIsExist(alarmmusicSet);
			if (isname > 0) {
				result.put("su", -1);
			}else {
				int iskey = setService.alarmNameKeyIsExist(alarmmusicSet);
				if (iskey > 0) {
					result.put("su", -3);
				} else {
					if (alarmmusicSet.getId()==null) {
						 re = setService.saveAlarmType(alarmmusicSet);
					}else {
						 re = setService.updateAlarmmusicSet(alarmmusicSet);
					}
					if(re<=0){
						result.put("su", -2);
					}else{
						result.put("su", 1);
					}

				}
			}
			this.success = "true";
			return SUCCESS;

		} catch (Exception e) {
			this.success = "false";
			logger.error("SetAction 的方法 saveAlarmmusicSet 执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 报警声音上传
	 */
	public String musicUpload() throws SizeLimitExceededException{
		try {
			HttpServletResponse response = ServletActionContext.getResponse();   
			response.setCharacterEncoding("utf-8"); 
			//上传文件保存路径
			String savePath = ServletActionContext.getServletContext().getRealPath("")+"/";
			String folder = WebConstants.appDir + "/" + WebConstants.TAXI_ALARMMUSIC_DIR + "/";
			savePath = savePath + folder;
			String picturename = FileHandler.getRandomFileName() + FileHandler.getFileExtName(fileInputFileName);
			fileInput.renameTo(new File(savePath + picturename));
			response.getWriter().println(folder + picturename);  
		} catch (Exception e) {
			logger.error("SetAction的方法 musicUpload执行出错，原因：" + e, e);
		}
		return null;
	}
	
	/**
	 * 删除报警类型
	 * @return
	 */
	public String deleteAlarmType(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
					int re = setService.deleteAlarmType(Integer.parseInt(id[i]));
					if(re > 0){
						result.put("su", 1);
					}else{
						result.put("su", -1);	//删除失败
					}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("BillAction 的方法 deleteAlarmType 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
						/***====================设备类型设置=====================*/
	
	/**
	 * 分页查询设备类型设置信息
	 * @return
	 */
	public String queryDeviceTypeList() {
		
		try {
			initMap();
			if(deviceType==null){
				deviceType=new DeviceType();
			}
			if(typename!=null&&!typename.equals("")&&!typename.equals("null")){
				deviceType.setTypename(URLDecoder.decode(typename,"UTF-8").trim());
			}
			if(company!=null&&!company.equals("")&&!company.equals("null")){
				deviceType.setCompany(URLDecoder.decode(company,"UTF-8").trim());
			}
			if(usertype!=null&&!usertype.equals("")&&!usertype.equals("null")){
				String str= URLDecoder.decode(usertype,"UTF-8").trim();
				if(!"".equals(str)){
					deviceType.setUsertype(Integer.parseInt(usertype));
				}
			}
			if(inteltype!=null&&!inteltype.equals("")&&!inteltype.equals("null")){
				deviceType.setInteltype(URLDecoder.decode(inteltype,"UTF-8").trim());
			}
			result=setService.findPageDeviceTypeList(this.getPage(),this.getLimit(), deviceType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("DeviceTypeAction的方法 queryDeviceTypeList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	

	/**
	 * Excel设备类型设置信息
	 * @return
	 */
	public void exportDeviceTypeList() {
		
		try {
			initMap();
			if(deviceType==null){
				deviceType=new DeviceType();
			}
			if(typename!=null&&!typename.equals("")&&!typename.equals("null")){
				deviceType.setTypename(new String(typename.getBytes("iso8859-1"),"utf-8").trim());
			}
			if(company!=null&&!company.equals("")&&!company.equals("null")){
				deviceType.setCompany(new String(company.getBytes("iso8859-1"),"utf-8").trim());
			}
			if(usertype!=null&&!usertype.equals("")&&!usertype.equals("null")){
				deviceType.setUsertype(Integer.parseInt(usertype));
			}
			if(inteltype!=null&&!inteltype.equals("")&&!inteltype.equals("null")){
				deviceType.setInteltype(URLDecoder.decode(inteltype,"UTF-8"));
			}
			if(StringUtils.isNotEmty(userid)){
				deviceType.setUserid(Integer.parseInt(userid));
			}
			List<DeviceType> list=setService.findPageDeviceTypeList(deviceType);
			String fileName = "终端类型设置";
		   	 HSSFWorkbook book = new HSSFWorkbook();

	         ExcelDownWay exceldownway= new ExcelDownWay();
	         
	         //2.设置列宽（列数要对应上）
	         String str="7,20,25,15,15,20,25,20";
	         List<String> numberList=Arrays.asList(str.split(","));
		     Sheet sheet= book.createSheet(fileName);
		     Row titleRow= sheet.createRow(0);
	         sheet= exceldownway.setColumnWidth(sheet,numberList);
	         sheet.setDefaultRowHeight((short) 18);
	         titleRow.setHeightInPoints(20);
		   	 
			
			titleRow.createCell(0).setCellValue("序号");
	        titleRow.createCell(1).setCellValue("终端类型ID");
	        titleRow.createCell(2).setCellValue("终端类型名称");
	        titleRow.createCell(3).setCellValue("用户类型");
	        titleRow.createCell(4).setCellValue("所属厂家");
	        titleRow.createCell(5).setCellValue("终端网络类型");
	        titleRow.createCell(6).setCellValue("创建时间");
	        titleRow.createCell(7).setCellValue("操作员");

	         for(int i=0;i<titleRow.getLastCellNum();i++){
	        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
	         }
	        if(list.size()>0){
	            for(int i=0;i<list.size();i++){
	                int  index=i+1;
	                Row contentRow= sheet.createRow(index);
	                contentRow.createCell(0).setCellValue(index);
	                DeviceType data= (DeviceType) list.get(i);
	                contentRow.createCell(1).setCellValue(data.getTypeid());
	                contentRow.createCell(2).setCellValue(data.getTypename());
	                contentRow.createCell(3).setCellValue(data.getUsertype());
	                contentRow.createCell(4).setCellValue(data.getCompany());
	                contentRow.createCell(5).setCellValue(data.getInteltype());
	                contentRow.createCell(6).setCellValue(data.getCreatetime());
	                contentRow.createCell(7).setCellValue(data.getUsername());

	                 for(int m=0;m<contentRow.getLastCellNum();m++){
	                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
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
	 * 添加或修改设备类型设置信息
	 * @param deviceType
	 * @return
	 */
	public String saveDeviceType(){
		try {
			initMap();
			if(deviceType==null){
				deviceType=new DeviceType();
			}
			deviceType.setUserid(SessionUtils.getUserId());
			int su = setService.saveDeviceType(deviceType);
			result.put("su", su);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("DeviceTypeAction的方法 saveDeviceType执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除设备类型设置信息
	 * @param deviceType
	 * @return
	 */
	public String deleteDeviceType(){
		try {
			initMap();
			String id[] = ids.split(",");
			int su =1;
			for (int i = 0; i < id.length; i++) {
				int count = setService.selectDeviceTypeByCar(Integer.parseInt(id[i]));
				if (count<=0) {
					setService.deleteDeviceType(Integer.parseInt(id[i]));
				}else {
					su = -2;
				}
			}
			result.put("su", su);
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("DeviceTypeAction的方法 deleteDeviceType执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	/**
	 * 界面展开关闭设置
	 * @return
	 */
	public String savePageSet(){
		try {
			initMap();
			if(pageSet==null){
				return ERROR;
			}
			setService.savePageSet(pageSet);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("SetAction的方法 savePageSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 终端参数设置
	 * @return
	 */
	public String terParamSet(){
		try {
			initMap();
			
			String id[] = ids.split(",");
			
			for (int i = 0; i < id.length; i++) {
				//下发
            	carInfo = carService.getCarInfoCarId(Integer.parseInt(id[i]));
            	
				//终端参数设置
				HandleUtil.TerParamSet(carInfo.getTerminal(),count,items);
			}
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("SetAction的方法 terParamSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/*******************支付参数设置***************************/
	
	/**
	 * 保存收款公司
	 * @return
	 */
	public String addCompany(){
		try {
			initMap();
			if(paymentParamSet == null){
				paymentParamSet = new PaymentParamSet();
			}
			int su = setService.addCompany(paymentParamSet);
			result.put("su", su);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			result.put("su", -2);
			this.success="false";
			logger.error("SetAction的方法 addCompany执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 保存付款参数信息
	 * @return
	 */
	public String updatePaymentParamSet(){
		try {
			initMap();
			if(paymentParamSet == null){
				paymentParamSet = new PaymentParamSet();
			}
			int su = setService.updatePaymentParamSet(paymentParamSet);
			result.put("su", su);
			this.success="true";
			return SUCCESS;
		} catch (Exception e) {
			this.success="false";
			logger.error("SetAction的方法 updatePaymentParamSet执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 删除付款公司
	 * @return
	 */
	public String deleteCompany(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
					int re = setService.deleteCompany(id[i]);
					if(re > 0){
						result.put("su", 1);
					}else{
						result.put("su", -1);	//删除失败
					}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SetAction 的方法 deleteCompany执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 获取收款公司分页列表
	 * @return
	 */
	public String findCompanyList() {
		
		try {
			initMap();
			if(paymentParamSet == null){
				paymentParamSet = new PaymentParamSet();
			}
			if(StringUtils.isNotEmty(company)){
				paymentParamSet.setCompany(URLDecoder.decode(company,"UTF-8").trim());
			}
			
			result = setService.findCompanyList(this.getPage(),this.getLimit(), paymentParamSet);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SetAction的方法 findCompanyList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 获取付款参数
	 * @return
	 */
	public String findPaymentParamById(){
		try {
			initMap();
//			if(StringUtils.isEmty(id)){
//				return ERROR;
//			}
			
			data = setService.findPaymentParamById(id);
			if(data == null){
				data = new PaymentParamSet();
			}
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			ExceptionUtil.getInfo(e);
			return ERROR;
		}
	}
	
	/**
	 * 获取所有收款公司列表
	 * @return
	 */
	public String findAllCompanyList() {
		
		try {
			initMap();
			if(paymentParamSet == null){
				paymentParamSet = new PaymentParamSet();
			}
			
			result = setService.findCompanyList(1,Integer.MAX_VALUE, paymentParamSet);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("SetAction的方法 findAllCompanyList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public SetService getSetService() {
		return setService;
	}

	public void setSetService(SetService setService) {
		this.setService = setService;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getInteltype() {
		return inteltype;
	}

	public void setInteltype(String inteltype) {
		this.inteltype = inteltype;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public AlarmtypeSet getAlarmmusicSet() {
		return alarmmusicSet;
	}

	public void setAlarmmusicSet(AlarmtypeSet alarmmusicSet) {
		this.alarmmusicSet = alarmmusicSet;
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


	public String getAlarmkey() {
		return alarmkey;
	}

	public void setAlarmkey(String alarmkey) {
		this.alarmkey = alarmkey;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public String getFileInputContentType() {
		return fileInputContentType;
	}

	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}

	public String getUpload() {
		return Upload;
	}

	public void setUpload(String upload) {
		Upload = upload;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String getAlarmname() {
		return alarmname;
	}

	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}

	public PageSet getPageSet() {
		return pageSet;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public PaymentParamSet getPaymentParamSet() {
		return paymentParamSet;
	}

	public void setPaymentParamSet(PaymentParamSet paymentParamSet) {
		this.paymentParamSet = paymentParamSet;
	}

	public PaymentParamSet getData() {
		return data;
	}

	public void setData(PaymentParamSet data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
