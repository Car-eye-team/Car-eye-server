package com.careye.monitor.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.ClockInfo;
import com.careye.monitor.domain.CommondLog;
import com.careye.monitor.service.CommondLogService;
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
 * @创建时间：2015-11-4 下午02:09:38
 * @修改人：ll
 * @修改时间：2015-11-4 下午02:09:38
 * @修改备注：
 * @version 1.0
 */
public class CommondLogAction extends BasePageAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CommondLogAction.class);
	
	private CommondLogService commondLogService;
	private CommondLog commondLog;
	
	private String blocid;
	private String carnumber;
	private String terminal;
	private String status;
	private String stime;
	private String etime;
	private String ids;
	
	private Map result;
	private String success;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 分页查询指令下发日志
	 * @return
	 */
	public String getCommondLogList() {

		try {
			initMap();
			if(commondLog==null){
				commondLog=new CommondLog();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				commondLog.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				commondLog.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				commondLog.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(carnumber)){
				commondLog.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				commondLog.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(status)){
				commondLog.setTerminal(URLDecoder.decode(status,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				commondLog.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				commondLog.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			result = commondLogService.getCommondLogList(this.getPage(), this.getLimit(), commondLog);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CommondLogAction的方法 getClockInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel导出
     * @throws IOException
     */
	public void exportExcel(){
		try {
		 fileName="指令下发日志"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20,20,20,25,25,40,40";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
         if(commondLog==null){
				commondLog=new CommondLog();
			}
			if(SessionUtils.getUser() == null){
				return ;
			}else{
				commondLog.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				commondLog.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				commondLog.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(carnumber)){
				commondLog.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				commondLog.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(status)){
				commondLog.setTerminal(URLDecoder.decode(status,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				commondLog.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				commondLog.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
        List<CommondLog>  list= commondLogService.exportCommondLog(commondLog);   //得到Excel数据
        
        if(list == null){
        	list = new ArrayList<CommondLog>();
        }
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("集团");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("设备号");
         titleRow.createCell(4).setCellValue("协议ID");
         titleRow.createCell(5).setCellValue("下发结果");
         titleRow.createCell(6).setCellValue("流水号");
         titleRow.createCell(7).setCellValue("协议名称");
         titleRow.createCell(8).setCellValue("操作时间");
         titleRow.createCell(9).setCellValue("应答时间");
         titleRow.createCell(10).setCellValue("下发数据");
         titleRow.createCell(11).setCellValue("备注");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 CommondLog cInfo= (CommondLog) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
                 contentRow.createCell(4).setCellValue(cInfo.getMsgid());
                 String statusStr = "";
                 if(cInfo.getStatus() == null){
                 }else if(cInfo.getStatus() == 0){
                	 statusStr = "成功/确认";
                 }else if(cInfo.getStatus() == 1){
                	 statusStr = "失败";
                 }else if(cInfo.getStatus() == 2){
                	 statusStr = "消息有误";
                 }else if(cInfo.getStatus() == 3){
                	 statusStr = "不支持";
                 }
                 contentRow.createCell(5).setCellValue(statusStr);
                 contentRow.createCell(6).setCellValue(cInfo.getSeq());
                 contentRow.createCell(7).setCellValue(cInfo.getMsgtype());
                 contentRow.createCell(8).setCellValue(cInfo.getCreatetime());
                 contentRow.createCell(9).setCellValue(cInfo.getRestime());
                 contentRow.createCell(10).setCellValue(cInfo.getData());
                 contentRow.createCell(11).setCellValue(cInfo.getRemark());
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("CommondLogAction的方法 exportExcel执行出错，原因：" + e, e);
		}
	}
	
	public String deleteCommondLog(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = commondLogService.deleteCommondLog(Integer.parseInt(id[i]));
				if (re <= 0) {
					result.put("su", -1);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("CommondLogAction 的方法 deleteCommondLog执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	public CommondLogService getCommondLogService() {
		return commondLogService;
	}

	public void setCommondLogService(CommondLogService commondLogService) {
		this.commondLogService = commondLogService;
	}

	public CommondLog getCommondLog() {
		return commondLog;
	}

	public void setCommondLog(CommondLog commondLog) {
		this.commondLog = commondLog;
	}

	public String getBlocid() {
		return blocid;
	}

	public void setBlocid(String blocid) {
		this.blocid = blocid;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
