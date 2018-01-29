package com.careye.transaction.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.transaction.domain.CustomerEvaluation;
import com.careye.transaction.domain.DriverEvaluation;
import com.careye.transaction.service.CustomerEvaluationService;
import com.careye.transaction.service.DriverEvaluationService;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-11-25 下午06:48:00
 * @修改人：ll
 * @修改时间：2015-11-25 下午06:48:00
 * @修改备注：
 * @version 1.0
 */
public class CustomerEvaluationAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CustomerEvaluationAction.class);
	
	private CustomerEvaluationService customerEvaluationService;
	private CustomerEvaluation customerEvaluation;
	
	private String dname;
	private String cname;
	private String slevel;
	private String stime;
	private String etime;
	
	private String fileName;
	private Map result;

	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 查询客户评价信息列表
	 * 
	 * @return
	 */
	public String getCustomerEvaluationList() {
		try {
			initMap();
			if (customerEvaluation == null) {
				customerEvaluation = new CustomerEvaluation();
			}
			if (StringUtils.isNotEmty(dname)) {
				customerEvaluation.setDname(URLDecoder.decode(dname, "UTF-8"));
			}
			if (StringUtils.isNotEmty(cname)) {
				customerEvaluation.setCname(URLDecoder.decode(cname, "UTF-8"));
			}
			if (StringUtils.isNotEmty(slevel)) {
				customerEvaluation.setSlevel(Integer.parseInt(slevel));
			}
			if (StringUtils.isNotEmty(stime)) {
				customerEvaluation.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				customerEvaluation.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			
			result = customerEvaluationService.getCustomerEvaluationList(this.getPage(), this
					.getLimit(), customerEvaluation);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CustomerEvaluationAction 的方法 getDriverEvaluationList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}

	/**
	 * Excel导出
	 * 
	 * @throws IOException
	 */
	public void exportExcel() {
		try {
			// 1.设置名字
			fileName = "乘客评价司机";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,25,15,25,25,45,35,35,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			
			if (customerEvaluation == null) {
				customerEvaluation = new CustomerEvaluation();
			}
			if (StringUtils.isNotEmty(dname)) {
				customerEvaluation.setDname(new String(dname.getBytes("ISO-8859-1"),"utf-8"));
			}
			if (StringUtils.isNotEmty(cname)) {
				customerEvaluation.setCname(new String(cname.getBytes("ISO-8859-1"),"utf-8"));
			}
			if (StringUtils.isNotEmty(slevel)) {
				customerEvaluation.setSlevel(Integer.parseInt(slevel));
			}
			if (StringUtils.isNotEmty(stime)) {
				customerEvaluation.setStime(URLDecoder.decode(stime, "UTF-8"));
			}
			if (StringUtils.isNotEmty(etime)) {
				customerEvaluation.setEtime(URLDecoder.decode(etime, "UTF-8"));
			}
			
			List<CustomerEvaluation> list = customerEvaluationService.exportExcel(customerEvaluation);

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("客户姓名");
			titleRow.createCell(2).setCellValue("客户手机号");
			titleRow.createCell(3).setCellValue("星级");
			titleRow.createCell(4).setCellValue("司机姓名");
			titleRow.createCell(5).setCellValue("司机手机号");
			titleRow.createCell(6).setCellValue("评价内容");
			titleRow.createCell(7).setCellValue("上车地址");
			titleRow.createCell(8).setCellValue("下车地址");
			titleRow.createCell(9).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					CustomerEvaluation data = (CustomerEvaluation) list.get(i);
					contentRow.createCell(1).setCellValue(data.getCname());
					contentRow.createCell(2).setCellValue(data.getCphone());
					Integer slevel = data.getSlevel();
					String slevelStr = "";
					if(slevel == null){
					}else if(slevel == 1){
						slevelStr = "一星";
					}else if(slevel == 2){
						slevelStr = "二星";
					}else if(slevel == 3){
						slevelStr = "三星";
					}else if(slevel == 4){
						slevelStr = "四星";
					}else if(slevel == 5){
						slevelStr = "五星";
					}
					contentRow.createCell(3).setCellValue(slevelStr);
					contentRow.createCell(4).setCellValue(data.getDname());
					contentRow.createCell(5).setCellValue(data.getDphone());
					contentRow.createCell(6).setCellValue(data.getContent());
					contentRow.createCell(7).setCellValue(data.getSaddress());
					contentRow.createCell(8).setCellValue(data.getEaddress());
					contentRow.createCell(9).setCellValue(data.getCreatetime());
//					for (int m = 0; m < contentRow.getLastCellNum(); m++) {
//						contentRow.getCell(m).setCellStyle(
//								exceldownway.setBookListStyle(book));
//					}
				}
			}

			exceldownway.getCommonExcelListWay(book, fileName);

		} catch (Exception e) {
			try {
				getResponse()
						.getWriter()
						.print(
								"<script language=javascript>alert('Error!');history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	public CustomerEvaluationService getCustomerEvaluationService() {
		return customerEvaluationService;
	}

	public void setCustomerEvaluationService(
			CustomerEvaluationService customerEvaluationService) {
		this.customerEvaluationService = customerEvaluationService;
	}

	public CustomerEvaluation getCustomerEvaluation() {
		return customerEvaluation;
	}

	public void setCustomerEvaluation(CustomerEvaluation customerEvaluation) {
		this.customerEvaluation = customerEvaluation;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSlevel() {
		return slevel;
	}

	public void setSlevel(String slevel) {
		this.slevel = slevel;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}
	
	
}
