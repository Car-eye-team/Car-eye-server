/**
 * Description: car-eye车辆管理平台
 * 文件名：CityInfoAction.java
 * 版本信息：1.0
 * 日期：2013-8-5
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013
 * 版权所有
 */
package com.careye.sysset.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.sysset.domain.CarType;
import com.careye.sysset.service.CarTypeService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import common.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
/**
 * @项目名称：car-eye
 * @类名称：CarTypeAction
 * @类描述：
 * @创建人：huangqin
 * @创建时间：2015-3-9 下午05:24:20
 * @修改人：huangqin
 * @修改时间：2013-3-9 下午05:24:20
 * @修改备注：
 * @version 1.0
 */

public class CarTypeAction extends BasePageAction {
	private static final Logger logger = Logger.getLogger(CarTypeAction.class);
	
	private CarTypeService carTypeService;
	private CarType carType;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private int su;
	private String typename;
    private String userid;
	
    private  String  fileName;
    
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 分页查询车辆类型名称信息
	 * @return
	 */
	public String queryPageCarTypeList() {
		
		try {
			initMap();
			if(carType==null){
				carType=new CarType();
			}
			if(typename!=null&&!typename.equals("")){
				carType.setTypename(URLDecoder.decode(typename, "UTF-8"));
			}
			result=carTypeService.selectPageCarType(this.getPage(),this.getLimit(), carType);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarTypeAction的方法 queryPageCarTypeList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 保存车辆类型信息
	 * @param carTypeName
	 * @return
	 */
	public String saveCarType(){
		try {
			initMap();
			if(carType==null){
				carType=new CarType();
			}
			int count=-1;
			carType.setUserid(SessionUtils.getUser().getId());
			
			if(carType.getId()==null){
			    count = carTypeService.insertCarType(carType);
			}else{
				count=carTypeService.updateCarType(carType);
			}
			
			result.put("su", count);
			this.success="true";
			return SUCCESS;
			
		} catch (Exception e) {
			this.success="false";
			logger.error("CarTypeAction的方法 saveCarType执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 删除车辆类型名称信息
	 * @param carTypeName
	 * @return
	 */
	public String deleteCarType(){
		try {
			initMap();
			if (ids == null)
				return ERROR;
			String id[] = ids.split(",");
			List<String> idList = Arrays.asList(ids.split(","));
			int count=carTypeService.deleteCarType(idList);
			result.put("su", count);
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CarTypeAction 的方法 deleteCarTypeName 执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	
	 /**
     * Excel导出
     * @throws IOException
     */
	public  void   exportExcel(){
		try {
		 fileName="车辆类型信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
         Row titleRow= sheet.createRow(0);
         if(carType==null){
				carType=new CarType();
	     }
         if(StringUtils.isNotEmpty(userid)){
				carType.setUserid(Integer.parseInt(userid));
		 }
         list= carTypeService.getAllCarType(carType);   //得到Excel数据
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("类型名称");
         titleRow.createCell(2).setCellValue("备注");
         titleRow.createCell(3).setCellValue("操作人");
         titleRow.createCell(4).setCellValue("创建时间");
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 CarType cType= (CarType) list.get(i);
                 contentRow.createCell(1).setCellValue(cType.getTypename());
                 contentRow.createCell(2).setCellValue(cType.getRemark());
                 contentRow.createCell(3).setCellValue(cType.getUsername());
                 contentRow.createCell(4).setCellValue(cType.getCreatetime());
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
	
	public CarTypeService getCarTypeService() {
		return carTypeService;
	}

	public void setCarTypeService(CarTypeService carTypeService) {
		this.carTypeService = carTypeService;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
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

	public int getSu() {
		return su;
	}

	public void setSu(int su) {
		this.su = su;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public static Logger getLogger() {
		return logger;
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


}
