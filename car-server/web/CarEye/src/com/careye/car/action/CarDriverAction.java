/**
* Description: car-eye车辆管理平台
* 文件名：CarDriverAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.car.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarDriver;
import com.careye.car.domain.CarInfo;
import com.careye.car.domain.PreJobExam;
import com.careye.car.domain.ServiceLicense;
import com.careye.car.domain.TaxiMeter;
import com.careye.car.service.CarDriverService;
import com.careye.car.service.CarService;
import com.careye.constant.WebConstants;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.BlocUser;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.FileHandler;
import com.careye.utils.FileSizeUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * @项目名称：CarDriverAction
 * @类名称：CarDriverServiceImpl
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-10 上午10:00:03
 * @修改人：huangqin
 * @修改时间：2015-3-10 上午10:00:03
 * @修改备注：
 * @version 1.0
 */
public class CarDriverAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(CarDriverAction.class);
	private CarDriverService carDriverService;
	private CarService carService;
	
	private CarDriver carDriver;
	private TaxiMeter taxiMeter;
	private PreJobExam preJobExam;
	private ServiceLicense serviceLicense;
	
	private Map result;
	private String success;
	private List list=new ArrayList<CarDriver>();
	private String ids;
	private BlocUser blocUser;
	private int su;
	
	private String drivername;
	private String phone;
	private String sscno;
	private String id;
	private String userid;
	private String carnumber;
	private String blocid;
	private String carids;
	
	private String flagnew;
	private String stime;
	private String etime;
	private String stime1;
	private String etime1;
	
	private String fileInputFileName;
	private File fileInput;
	private String Filename;
	private String Upload;
	private String fileInputContentType;
	private String fileext;
	private String folder;
	
	private String driverid;
	
	private String carid;
	private String servicenumber;
	private String starlevel;
	private String starleveltext;
	private String version;
	private String picturepath;
	
	private String fileName;
	
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}

	/**
	 * 分页查询司机信息
	 * @return
	 */
	public String queryCarDriverList() {
		
		try {
			initMap();
			if(carDriver==null){
				carDriver=new CarDriver();
			}
			
			if(drivername!=null&&!drivername.equals("")&&!drivername.equals("null")){
				carDriver.setDrivername(URLDecoder.decode(drivername,"UTF-8").trim());
			}
			if(phone!=null&&!phone.equals("")&&!phone.equals("null")){
				carDriver.setPhone(URLDecoder.decode(phone,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(sscno)){
				carDriver.setSscno(URLDecoder.decode(sscno,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(carnumber)){
				carDriver.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
			}
			if(id!=null&&!id.equals("")&&!id.equals("null")){
				carDriver.setId(Integer.parseInt(id));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carDriver.setUserid(SessionUtils.getUserId());
			}
			result=carDriverService.selectCheckCarDriver(this.getPage(),this.getLimit(), carDriver);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 queryCarDriverList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改司机信息
	 * @param carDriver
	 * @return
	 */
	public String saveCarDriver(){
		try {
			initMap();
			if(carDriver==null){
				carDriver=new CarDriver();
			}
//			int isdrivername=carDriverService.queryDriverNameIsExist(carDriver);
			if(StringUtils.isEmty(carDriver.getDrivercode())){
				carDriver.setUserid(SessionUtils.getUserId());
					int count=-1;
					if(carDriver.getId()==null){
						 carDriver.setCreatetime(DateUtil.getSQLDate());
						 count = carDriverService.insertCarDriver(carDriver);
						 
						 //更新司机代码
						 int driver_id = carDriverService.getNewId();
						 carDriver.setId(driver_id);
						 carDriver.setDrivercode(driver_id+"");
						 carDriverService.updateDrivercode(carDriver);
						 
						 //更新当班司机
						 CarInfo carInfo = new CarInfo();
						 carInfo.setId(carDriver.getCarid());
						 carInfo.setDrivercode(carDriver.getDrivercode());
						 carService.updateDriverCode(carInfo);
					}else{
						carDriver.setDrivercode(carDriver.getId()+"");
						 count=carDriverService.updateCarDriver(carDriver);
					}
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
			}else{
				int scode = carDriverService.getDrivercodeIsExist(carDriver);
				carDriver.setUserid(SessionUtils.getUserId());
				if(scode>0) {
					result.put("su", -3);
				}else{
					int count=-1;
					if(carDriver.getId()==null){
						 carDriver.setCreatetime(DateUtil.getSQLDate());
						 count = carDriverService.insertCarDriver(carDriver);
						 
						//更新当班司机
						 CarInfo carInfo = new CarInfo();
						 carInfo.setId(carDriver.getCarid());
						 carInfo.setDrivercode(carDriver.getDrivercode());
						 carService.updateDriverCode(carInfo);
					}else{
						 count=carDriverService.updateCarDriver(carDriver);
					}
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}
			}
			
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("CarDriverAction的方法 saveCarDriver执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除司机信息
	 * @param carDriver
	 * @return
	 */
	public String deleteCarDriver(){
		try {
			initMap();
			if (ids == null)
				return ERROR;
			List<String> idList = Arrays.asList(ids.split(","));
			result.put("su", carDriverService.deleteCarDriver(idList));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 deleteCarDriver执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	 /**
     * Excel导出
     * @throws IOException
     */
	public  void   exportExcel(){
		try {
	     //1.设置名字
		 fileName="司机信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);
         //sheet.setDefaultColumnWidth(15);
         
         ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20,20,20,20,20,20,20,20,25,20,20,20,40,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         if(carDriver==null){
        	 carDriver=new CarDriver();
	     }
         
         carDriver.setUserid(SessionUtils.getUserId());
         if(StringUtils.isNotEmty(drivername)){
        	 carDriver.setDrivername(new String(drivername.getBytes("iso8859-1"),"utf-8").trim());
		 }
         if(StringUtils.isNotEmty(phone)){
        	 carDriver.setPhone(new String(phone.getBytes("iso8859-1"),"utf-8").trim());
		 }
         
     	if(StringUtils.isNotEmty(sscno)){
			carDriver.setSscno(new String(sscno.getBytes("iso8859-1"),"utf-8").trim());
		}
     	if(StringUtils.isNotEmty(carnumber)){
     		carDriver.setCarnumber(new String(carnumber.getBytes("iso8859-1"),"utf-8").trim());
     	}
         
         list= carDriverService.getAllCarDriver(carDriver);   //Excel查询
         
         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
         titleRow.createCell(1).setCellValue("姓名");
         titleRow.createCell(2).setCellValue("集团");
         titleRow.createCell(3).setCellValue("手机号");
         titleRow.createCell(4).setCellValue("性别");
         titleRow.createCell(5).setCellValue("车牌号");
         titleRow.createCell(6).setCellValue("文化程度");
         titleRow.createCell(7).setCellValue("政治面貌");
         titleRow.createCell(8).setCellValue("当前状态");
         titleRow.createCell(9).setCellValue("出生日期");
         titleRow.createCell(10).setCellValue("身份证号");
         titleRow.createCell(11).setCellValue("民族");
         titleRow.createCell(12).setCellValue("家庭住址");
         titleRow.createCell(13).setCellValue("创建时间");
         titleRow.createCell(14).setCellValue("驾驶证号");
         titleRow.createCell(15).setCellValue("准驾车型");
         titleRow.createCell(16).setCellValue("驾驶证有效期(年)");
         titleRow.createCell(17).setCellValue("备注");
         titleRow.createCell(18).setCellValue("初次领证日期");
         titleRow.createCell(19).setCellValue("发证日期");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 CarDriver carDriver= (CarDriver) list.get(i);
                 contentRow.createCell(1).setCellValue(carDriver.getDrivername());
                 contentRow.createCell(2).setCellValue(carDriver.getBlocname());
                 contentRow.createCell(3).setCellValue(carDriver.getPhone());
                 String sexString=null;
                 if(carDriver.getSex()!=null){
                	 int sex=carDriver.getSex();
                	 if(sex==1){
                		 sexString="男";
	                 }else if(sex==2){
	                	 sexString="女";
	                 }
                 }
                 contentRow.createCell(4).setCellValue(sexString);
                 contentRow.createCell(5).setCellValue(carDriver.getCarnumber());
                 String educationString=null;
                 if(carDriver.getEducation()!=null){
                	 int education=carDriver.getEducation();
                	 if(education==1){
                		 educationString="小学";
	                 }else if(education==2){
	                	 educationString="初中";
	                 }else if(education==2){
	                	 educationString="高中";
	                 }else if(education==2){
	                	 educationString="中专";
	                 }else if(education==2){
	                	 educationString="大专";
	                 }else if(education==2){
	                	 educationString="职高";
	                 }else if(education==2){
	                	 educationString="本科";
	                 }else if(education==2){
	                	 educationString="硕士研究生";
	                 }else if(education==2){
	                	 educationString="博士研究生";
	                 }else if(education==2){
	                	 educationString="博士后";
	                 }else if(education==2){
	                	 educationString="其它";
	                 }else{
	                	 educationString="";
	                 }
                 }
                 contentRow.createCell(6).setCellValue(educationString);
                 String politicalString=null;
                 if(carDriver.getPolitical()!=null){
                	 int political=carDriver.getPolitical();
                	 if(political==1){
                		 politicalString="党员";
	                 }else if(political==2){
	                	 politicalString="团员";
	                 }else if(political==2){
	                	 politicalString="群众";
	                 }else if(political==2){
	                	 politicalString="九三学社";
	                 }else if(political==2){
	                	 politicalString="其它";
	                 }else{
	                	 politicalString="";
	                 }
                 }
                 contentRow.createCell(7).setCellValue(politicalString);
                 String nowstatusString=null;
                 if(carDriver.getNowstatus()!=null){
                	 int nowstatus=carDriver.getNowstatus();
                	 if(nowstatus==1){
                		 nowstatusString="正常";
	                 }else if(nowstatus==2){
	                	 nowstatusString="注销";
	                 }else{
	                	 nowstatusString="";
	                 }
                 }
                 contentRow.createCell(8).setCellValue(nowstatusString);
                 contentRow.createCell(9).setCellValue(carDriver.getBirthday());
                 contentRow.createCell(10).setCellValue(carDriver.getIdnumber());
                 contentRow.createCell(11).setCellValue(carDriver.getNation());
                 contentRow.createCell(12).setCellValue(carDriver.getAddr());
                 contentRow.createCell(13).setCellValue(carDriver.getCreatetime());
                 contentRow.createCell(14).setCellValue(carDriver.getDrivingnumber());
                 contentRow.createCell(15).setCellValue(carDriver.getZjcartype());
                 if(carDriver.getValidity() != null){
                	 contentRow.createCell(16).setCellValue(carDriver.getValidity());
                 }else{
                	 contentRow.createCell(16).setCellValue("");
                 }
                 contentRow.createCell(17).setCellValue(carDriver.getRemark());
                 contentRow.createCell(18).setCellValue(carDriver.getFirstlztime());
                 contentRow.createCell(19).setCellValue(carDriver.getFztime());
                 
                 for(int m=0;m<contentRow.getLastCellNum();m++){
                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
                 }
             }
         }
         
         exceldownway.getCommonExcelListWay(book,fileName);
         
	} catch (Exception e) {
		try {
			getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    }
	
	
	/**************************   计价器     ********************************/

	/**
	 * 分页查询计价器信息
	 * @return
	 */
	public String queryTaxiMeterList() {
		
		try {
			initMap();
			if(taxiMeter==null){
				taxiMeter=new TaxiMeter();
			}
			
			if(StringUtils.isNotEmty(carnumber)){
				taxiMeter.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(StringUtils.isNotEmty(flagnew)){
				taxiMeter.setFlagnew(URLDecoder.decode(flagnew,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime)){
				taxiMeter.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				taxiMeter.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(StringUtils.isNotEmty(stime1)){
				taxiMeter.setStime1(URLDecoder.decode(stime1,"UTF-8"));
			}
			if(StringUtils.isNotEmty(etime)){
				taxiMeter.setEtime1(URLDecoder.decode(etime,"UTF-8"));
			}
			
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carDriver.setUserid(SessionUtils.getUserId());
			}
//			result=carDriverService.selectTaxiMeter(this.getPage(),this.getLimit(), taxiMeter);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 queryTaxiMeterList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	 /**
     * 计价器Excel导出
     * @throws IOException
     */
	public  void   exportTaxiMeterExcel(){
		try {
	     //1.设置名字
		 fileName="计价器信息"; 
//    	 HSSFWorkbook book = new HSSFWorkbook();
//         Sheet sheet= book.createSheet(fileName);
//         //sheet.setDefaultColumnWidth(15);
//         
//         ExcelDownWay exceldownway= new ExcelDownWay();
//         
//         //2.设置列宽（列数要对应上）
//         String str="7,20,20,15,15,15,7,30,25,15,15,15,15,20,20";
//         List<String> numberList=Arrays.asList(str.split(","));
//         sheet= exceldownway.setColumnWidth(sheet,numberList);
//         
//         sheet.setDefaultRowHeight((short) 18);
//         Row titleRow= sheet.createRow(0);
//         titleRow.setHeightInPoints(20);
//         if(carDriver==null){
//        	 carDriver=new CarDriver();
//	     }
//         
//         carDriver.setUserid(SessionUtils.getUserId());
//         if(StringUtils.isNotEmty(drivername)){
//        	 carDriver.setDrivername(new String(drivername.getBytes("iso8859-1"),"utf-8"));
//		 }
//         if(StringUtils.isNotEmty(phone)){
//        	 carDriver.setPhone(new String(phone.getBytes("iso8859-1"),"utf-8"));
//		 }
//         
//         list= carDriverService.getAllCarDriver(carDriver);   //Excel查询
//         
//         titleRow.createCell(0).setCellValue("序号");//titleRow.setHeight((short)(20 * 15));
//         titleRow.createCell(1).setCellValue("集团");
//         titleRow.createCell(2).setCellValue("服务监督卡号");
//         titleRow.createCell(3).setCellValue("姓名");
//         titleRow.createCell(4).setCellValue("联系电话");
//         titleRow.createCell(5).setCellValue("手机号");
//         titleRow.createCell(6).setCellValue("性别");
//         titleRow.createCell(7).setCellValue("联系地址");
//         titleRow.createCell(8).setCellValue("身份证号");
//         titleRow.createCell(9).setCellValue("出生日期");
//         titleRow.createCell(10).setCellValue("从业资格证");
//         titleRow.createCell(11).setCellValue("发证机构");
//         titleRow.createCell(12).setCellValue("驾驶证号");
//         titleRow.createCell(13).setCellValue("驾驶证年审日期");
//         titleRow.createCell(14).setCellValue("创建时间");
//         
//         for(int i=0;i<titleRow.getLastCellNum();i++){
//        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
//         }
//         
//         if(list.size()>0){
//             for(int i=0;i<list.size();i++){
//                 int  index=i+1;
//                 Row contentRow= sheet.createRow(index);
//                 contentRow.createCell(0).setCellValue(index);
//                 CarDriver carDriver= (CarDriver) list.get(i);
//                 contentRow.createCell(1).setCellValue(carDriver.getBlocname());
//                 contentRow.createCell(2).setCellValue(carDriver.getSscno());
//                 contentRow.createCell(3).setCellValue(carDriver.getDrivername());
//                 contentRow.createCell(4).setCellValue(carDriver.getTel());
//                 contentRow.createCell(5).setCellValue(carDriver.getPhone());
//                 String sex=carDriver.getSex().equals("1")?"男":"女";
//                 contentRow.createCell(6).setCellValue(sex);
//                 contentRow.createCell(7).setCellValue(carDriver.getAddr());
//                 contentRow.createCell(8).setCellValue(carDriver.getIdnumber());
//                 contentRow.createCell(9).setCellValue(carDriver.getBirthday());
//                 contentRow.createCell(10).setCellValue(carDriver.getQualificationcertificate());
//                 contentRow.createCell(11).setCellValue(carDriver.getCertifyingauthority());
//                 contentRow.createCell(12).setCellValue(carDriver.getDrivecrednum());
//                 contentRow.createCell(13).setCellValue(carDriver.getDriverannualdate());
//                 contentRow.createCell(14).setCellValue(carDriver.getCreatetime());
//                 
//                 for(int m=0;m<contentRow.getLastCellNum();m++){
//                	 contentRow.getCell(m).setCellStyle(exceldownway.setBookListStyle(book));
//                 }
//             }
//         }
//         
//         exceldownway.getCommonExcelListWay(book,fileName);
//         
	} catch (Exception e) {
		try {
			getResponse().getWriter().print("<script language=javascript>alert('Error!');history.back();</script>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    }
	/**
	 * 分页查询当班司机信息
	 * @return
	 */
	public String queryDutyCarDriverList() {
		
		try {
			initMap();
			if(carDriver==null){
				carDriver=new CarDriver();
			}
			
			if(StringUtils.isNotEmty(drivername)){
				carDriver.setDrivername(URLDecoder.decode(drivername,"UTF-8"));
			}
			if(StringUtils.isNotEmty(blocid)){
				carDriver.setBlocid(Integer.parseInt(blocid));
			}
			if(StringUtils.isNotEmty(carnumber)){
				carDriver.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(StringUtils.isNotEmty(phone)){
				carDriver.setPhone(URLDecoder.decode(phone,"UTF-8"));
			}
			if(StringUtils.isNotEmty(sscno)){
				carDriver.setSscno(sscno);
			}
			if(id!=null&&!id.equals("")&&!id.equals("null")){
				carDriver.setId(Integer.parseInt(id));
			}
			if(StringUtils.isNotEmty(carids)){
				String  []  datas= carids.split(",");
				for(int j=0;j<datas.length;j++){
					carDriver.getCarids().add(Integer.parseInt(datas[j]));
				}
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carDriver.setUserid(SessionUtils.getUserId());
			}
			result=carDriverService.selectDutyCarDriver(this.getPage(),this.getLimit(), carDriver);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 queryDutyCarDriverList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 根据驾驶员id查询岗前考试信息
	 * @return
	 */
	public String findPreJobExamByDriverid() {

		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			PreJobExam jobExam = carDriverService.findPreJobExamByDriverid(driverid);
			if(jobExam == null){
				result.put("su", 1);
			}else{
				result.put("su", 2);
				result.put("data", jobExam);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 findPreJobExamByDriverid执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改岗前考试信息
	 * @param preJobExam
	 * @return
	 */
	public String savePreJobExam(){
		try {
			initMap();
			
			if(preJobExam.getId() == null){	//添加
				carDriverService.insertPreJobExam(preJobExam);
			}else{	//修改
				carDriverService.updatePreJobExam(preJobExam);
			}
			result.put("su", su);
			this.success="true";
			return SUCCESS;

		} catch (Exception e) {
			this.success="false";
			logger.error("CarDriverAction的方法 savePreJobExam执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 根据驾驶员id查询服务证信息
	 * @return
	 */
	public String findServiceLicenseByDriverid() {

		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			
			ServiceLicense serLicense = carDriverService.findServiceLicenseByDriverid(driverid);
			if(serLicense == null){
				result.put("su", 1);
				result.put("data", "null");
			}else{
				result.put("su", 2);
				result.put("data", serLicense);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 findServiceLicenseByDriverid执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改服务证信息
	 * @param preJobExam
	 * @return
	 */
	public String saveServiceLicense(){
		try {
			initMap();
			
			if(serviceLicense.getId() == null){	//添加
				serviceLicense.setPicturepath(serviceLicense.getDownloadaddr());
				carDriverService.insertServiceLicense(serviceLicense);
			}else{	//修改
				//上传文件保存路径
				 String savePath = ServletActionContext.getServletContext().getRealPath("");
				 
				if(StringUtils.isNotEmty(serviceLicense.getPicturepath())){
					(new File(savePath+serviceLicense.getPicturepath())).delete();//删除图片
				}
				serviceLicense.setPicturepath(serviceLicense.getDownloadaddr());
				carDriverService.updateServiceLicense(serviceLicense);
			}
			result.put("su", su);
			this.success="true";
			return SUCCESS;

		} catch (Exception e) {
			this.success="false";
			logger.error("CarDriverAction的方法 saveServiceLicense执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 服务证图片上传
	 */
	public String serviceLicenseUpload(){
		try {
			 HttpServletResponse response = ServletActionContext.getResponse();   
			  response.setCharacterEncoding("utf-8"); 
			  //上传文件保存路径
			  String savePath = ServletActionContext.getServletContext().getRealPath("");
			  String folder = "/"+WebConstants.SERVICELICENSE_DIR + "/" + WebConstants.SERVCIELISENCE + "/";
			  savePath = savePath + folder;
			  String picturename = FileHandler.getRandomFileName() + ".png";
			  if(!new File(savePath).exists()){
				  new File(savePath).mkdirs();
			  }
			  fileInput.renameTo(new File(savePath + picturename));
			  //返回数据
			  response.getWriter().println(folder + picturename);  
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 serviceLicenseUpload执行出错，原因：" + e, e);
		}
	  return null; //这里不需要页面转向，所以返回空就可以了    
	}
	
	
	/**
	 * 分页查询司机服务证列表信息
	 * @return
	 */
	public String queryServicePhotoList() {
		
		try {
			initMap();
			if(serviceLicense==null){
				serviceLicense=new ServiceLicense();
			}
			
			if(drivername!=null&&!drivername.equals("")&&!drivername.equals("null")){
				serviceLicense.setDrivername(URLDecoder.decode(drivername,"UTF-8").trim());
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				serviceLicense.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase());
			}
			if(StringUtils.isNotEmty(blocid)){
				serviceLicense.setBlocid(Integer.parseInt(blocid));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				serviceLicense.setUserid(SessionUtils.getUserId());
			}
			result=carDriverService.queryServicePhotoList(this.getPage(),this.getLimit(), serviceLicense);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 queryCarDriverList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 服务证补发
	 * @return
	 */
	public String reissueServicePhoto() {
		
		try {
			initMap();
			
			//根据车辆ID获取车辆信息
			CarInfo carinfo = carService.getCarInfoCarId(Integer.parseInt(carid));
			
			//图片转换为字节数组
			byte[] picdata = FileSizeUtil.getImageBinaryByUrl(picturepath);
			
			//拆分数组，大的字符数组拆成很多小的大小为512字节数组
			Object [] data = FileSizeUtil.splitAry(picdata, 512);;
			String datastr = "";
			int count = 0;
			for(int i=0;i<data.length;i++){
				
				datastr = FileSizeUtil.byte2hex((byte[])data[i]);
				
				HandleUtil.reissueServicePhoto(carinfo.getUsertype(), carinfo.getTerminal(), drivername, carinfo.getBlocname(), 
						driverid,starlevel, starleveltext, version, datastr,i);
				
				count = count + 1;
			}
			logger.info("图片包总数："+count);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarDriverAction的方法 reissueServicePhoto执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public CarDriverService getCarDriverService() {
		return carDriverService;
	}

	public void setCarDriverService(CarDriverService carDriverService) {
		this.carDriverService = carDriverService;
	}

	public CarDriver getCarDriver() {
		return carDriver;
	}

	public void setCarDriver(CarDriver carDriver) {
		this.carDriver = carDriver;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	public BlocUser getBlocUser() {
		return blocUser;
	}

	public void setBlocUser(BlocUser blocUser) {
		this.blocUser = blocUser;
	}

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSscno() {
		return sscno;
	}

	public void setSscno(String sscno) {
		this.sscno = sscno;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public TaxiMeter getTaxiMeter() {
		return taxiMeter;
	}

	public void setTaxiMeter(TaxiMeter taxiMeter) {
		this.taxiMeter = taxiMeter;
	}

	public String getFlagnew() {
		return flagnew;
	}

	public void setFlagnew(String flagnew) {
		this.flagnew = flagnew;
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

	public String getStime1() {
		return stime1;
	}

	public void setStime1(String stime1) {
		this.stime1 = stime1;
	}

	public String getEtime1() {
		return etime1;
	}

	public void setEtime1(String etime1) {
		this.etime1 = etime1;
	}

	public PreJobExam getPreJobExam() {
		return preJobExam;
	}

	public void setPreJobExam(PreJobExam preJobExam) {
		this.preJobExam = preJobExam;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public ServiceLicense getServiceLicense() {
		return serviceLicense;
	}

	public void setServiceLicense(ServiceLicense serviceLicense) {
		this.serviceLicense = serviceLicense;
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

	public String getServicenumber() {
		return servicenumber;
	}

	public void setServicenumber(String servicenumber) {
		this.servicenumber = servicenumber;
	}

	public String getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getStarleveltext() {
		return starleveltext;
	}

	public void setStarleveltext(String starleveltext) {
		this.starleveltext = starleveltext;
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}
	
	
}
