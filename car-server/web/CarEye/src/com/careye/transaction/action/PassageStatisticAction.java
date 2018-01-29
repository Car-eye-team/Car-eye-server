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
import com.careye.transaction.domain.PassageStatistic;
import com.careye.transaction.domain.Transaction;
import com.careye.transaction.service.PassageStatisticService;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;
import common.Logger;

/**
 * 
 * @项目名称：car-eye
 * @类名称：
 * @类描述：
 * @创建人：ll
 * @创建时间：2015-9-21 上午11:27:31
 * @修改人：ll
 * @修改时间：2015-9-21 上午11:27:31
 * @修改备注：
 * @version 1.0
 */
public class PassageStatisticAction extends BasePageAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(PassageStatisticAction.class);
	
	private PassageStatisticService passageStatisticService;
	private PassageStatistic passageStatistic;
	
	private String blocid;
	private String carnumber;
	private String terminal;
	private String stime;
	private String etime;
	
	private Map<Object, Object> result;
	private String fileName;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 得到乘客人数统计信息
	 * 
	 * @return
	 */
	public String getPassageStatisticList() {
		try {
			initMap();
			if (passageStatistic == null) {
				passageStatistic = new PassageStatistic();
			}
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
				passageStatistic.setUserid(userid);
			}
			
			if(StringUtils.isNotEmty(blocid)){
				passageStatistic.setBlocid(Integer.parseInt(URLDecoder.decode(blocid,"UTF-8").trim()));
			}
			if(StringUtils.isNotEmty(carnumber)){
				passageStatistic.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				passageStatistic.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				passageStatistic.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				passageStatistic.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			result = passageStatisticService.getPassageStatisticList(this.getPage(), this
					.getLimit(), passageStatistic);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("PassageStatisticAction 的方法 getPassageStatisticList 执行出错，原因："
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
			fileName = "乘客人数统计";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);
			// sheet.setDefaultColumnWidth(15);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,25,25,25,25,25,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			
			initMap();
			if (passageStatistic == null) {
				passageStatistic = new PassageStatistic();
			}
			
			Integer userid = null;
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				userid = SessionUtils.getUserId();
				passageStatistic.setUserid(userid);
			}
			
			if(StringUtils.isNotEmty(blocid)){
				passageStatistic.setBlocid(Integer.parseInt(URLDecoder.decode(blocid,"UTF-8").trim()));
			}
			if(StringUtils.isNotEmty(carnumber)){
				passageStatistic.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				passageStatistic.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				passageStatistic.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				passageStatistic.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			result = passageStatisticService.getPassageStatisticList(1, Integer.MAX_VALUE, passageStatistic);
			List<PassageStatistic> list = (List<PassageStatistic>) result.get("list");

			titleRow.createCell(0).setCellValue("序号");// titleRow.setHeight((short)(20
														// * 15));
			titleRow.createCell(1).setCellValue("集团");
			titleRow.createCell(2).setCellValue("车牌号");
			titleRow.createCell(3).setCellValue("终端号码");
			titleRow.createCell(4).setCellValue("人数");
			titleRow.createCell(5).setCellValue("计价器业务流水号");
			titleRow.createCell(6).setCellValue("地址");
			titleRow.createCell(7).setCellValue("上传时间");
			titleRow.createCell(8).setCellValue("创建时间");

			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.createCell(0).setCellValue(index);
					PassageStatistic passage = (PassageStatistic) list.get(i);
					contentRow.createCell(1).setCellValue(
							passage.getBlocname());
					contentRow.createCell(2).setCellValue(passage.getCarnumber());
					contentRow.createCell(3).setCellValue(passage.getTerminal());
					Integer peoplenumber = passage.getPeoplenumber();
					if(peoplenumber != null){
						contentRow.createCell(4).setCellValue(passage.getPeoplenumber()+"人");
					}else{
						contentRow.createCell(4).setCellValue(passage.getPeoplenumber());
					}
					
					contentRow.createCell(5).setCellValue(passage.getSerialnumber());
					contentRow.createCell(6).setCellValue(passage.getAddress());
					contentRow.createCell(7).setCellValue(passage.getUploadtime());
					contentRow.createCell(8).setCellValue(passage.getCreatetime());

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

	public PassageStatisticService getPassageStatisticService() {
		return passageStatisticService;
	}

	public void setPassageStatisticService(
			PassageStatisticService passageStatisticService) {
		this.passageStatisticService = passageStatisticService;
	}

	public PassageStatistic getPassageStatistic() {
		return passageStatistic;
	}

	public void setPassageStatistic(PassageStatistic passageStatistic) {
		this.passageStatistic = passageStatistic;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
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

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
