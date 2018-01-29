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
import com.careye.monitor.domain.CommondLog;
import com.careye.monitor.domain.OperateData;
import com.careye.monitor.service.OperateDataService;
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
 * @创建时间：2015-11-10 上午11:31:11
 * @修改人：ll
 * @修改时间：2015-11-10 上午11:31:11
 * @修改备注：
 * @version 1.0
 */
public class OperateDataAction extends BasePageAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OperateDataAction.class);
	
	private OperateDataService operateDataService;
	private OperateData operateData;
	
	private String blocid;
	private String carnumber;
	private String terminal;
	private String stime;
	private String etime;
	
	private Map result;
	private String success;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 分页查询营运信息
	 * @return
	 */
	public String getOperateDataList() {

		try {
			initMap();
			if(operateData==null){
				operateData=new OperateData();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				operateData.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				operateData.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				operateData.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(carnumber)){
				operateData.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				operateData.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				operateData.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				operateData.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
			
			result = operateDataService.getOperateDataList(this.getPage(), this.getLimit(), operateData);
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("OperateDataAction的方法 getOperateDataList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel导出
     * @throws IOException
     */
	public void exportExcel(){
		try {
		 fileName="营运信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,25,20,20,20,20,20,20,20,25,35,20,20,20,20,20,20,20," +
         		"25,35,20,20,20,20,20,20,20,25,20,20,20,20,20,20,20,25,20,20,20,20,20,20,20,20,20,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
         if(operateData==null){
				operateData=new OperateData();
			}
			if(SessionUtils.getUser() == null){
				return ;
			}else{
				operateData.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				operateData.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				operateData.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(carnumber)){
				operateData.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				operateData.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(stime)){
				operateData.setStime(URLDecoder.decode(stime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(etime)){
				operateData.setEtime(URLDecoder.decode(etime,"UTF-8").trim());
			}
        List<OperateData>  list= operateDataService.exportOperateData(operateData);   //得到Excel数据
        
        if(list == null){
        	list = new ArrayList<OperateData>();
        }
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("集团");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("设备号");
         titleRow.createCell(4).setCellValue("创建时间");
         titleRow.createCell(5).setCellValue("空车报警信息");
         titleRow.createCell(6).setCellValue("空车状态信息");
         titleRow.createCell(7).setCellValue("空车纬度");
         titleRow.createCell(8).setCellValue("空车经度");
         titleRow.createCell(9).setCellValue("空车高程");
         titleRow.createCell(10).setCellValue("空车速度");
         titleRow.createCell(11).setCellValue("空车方向");
         titleRow.createCell(12).setCellValue("空车时间");
         titleRow.createCell(13).setCellValue("空车百度地址");
         titleRow.createCell(14).setCellValue("重车报警信息");
         titleRow.createCell(15).setCellValue("重车状态信息");
         titleRow.createCell(16).setCellValue("重车纬度");
         titleRow.createCell(17).setCellValue("重车经度");
         titleRow.createCell(18).setCellValue("重车高程");
         titleRow.createCell(19).setCellValue("重车速度");
         titleRow.createCell(20).setCellValue("重车方向");
         titleRow.createCell(21).setCellValue("重车时间");
         titleRow.createCell(22).setCellValue("重车百度地址");
         titleRow.createCell(23).setCellValue("营运ID");
         titleRow.createCell(24).setCellValue("人数");
         titleRow.createCell(25).setCellValue("评价ID");
         titleRow.createCell(26).setCellValue("评价选项");
         titleRow.createCell(27).setCellValue("单位代码");
         titleRow.createCell(28).setCellValue("司机代码");
         titleRow.createCell(29).setCellValue("驾驶员唯一编号");
         titleRow.createCell(30).setCellValue("上车时间");
         titleRow.createCell(31).setCellValue("下车时间");
         titleRow.createCell(32).setCellValue("计程公里");
         titleRow.createCell(33).setCellValue("空驶公里");
         titleRow.createCell(34).setCellValue("附加费");
         titleRow.createCell(35).setCellValue("等待计时时间");
         titleRow.createCell(36).setCellValue("交易金额");
         titleRow.createCell(37).setCellValue("当前车次");
         titleRow.createCell(38).setCellValue("交易时间");
         titleRow.createCell(39).setCellValue("交易类型");
         titleRow.createCell(40).setCellValue("卡类型");
         titleRow.createCell(41).setCellValue("卡厂商");
         titleRow.createCell(42).setCellValue("物理卡号");
         titleRow.createCell(43).setCellValue("交易卡号");
         titleRow.createCell(44).setCellValue("硬件交易流水号");
         titleRow.createCell(45).setCellValue("SAM卡卡号");
         titleRow.createCell(46).setCellValue("SAM交易序号");
         titleRow.createCell(47).setCellValue("卡交易金额");
         titleRow.createCell(48).setCellValue("交易后余额");
         titleRow.createCell(49).setCellValue("评价选项扩展");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 OperateData cInfo= (OperateData) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
                 contentRow.createCell(4).setCellValue(cInfo.getCreatetime());
                 contentRow.createCell(5).setCellValue(cInfo.getKalarminfo());
                 contentRow.createCell(6).setCellValue(cInfo.getKstateinfo());
                 contentRow.createCell(7).setCellValue(cInfo.getKlat());
                 contentRow.createCell(8).setCellValue(cInfo.getKlng());
                 contentRow.createCell(9).setCellValue(cInfo.getKaltitude());
                 contentRow.createCell(10).setCellValue(cInfo.getKspeed());
                 contentRow.createCell(11).setCellValue(cInfo.getKdirection());
                 contentRow.createCell(12).setCellValue(cInfo.getKtime());
                 contentRow.createCell(13).setCellValue(cInfo.getKaddress());
                 contentRow.createCell(14).setCellValue(cInfo.getZalarminfo());
                 contentRow.createCell(15).setCellValue(cInfo.getZstateinfo());
                 contentRow.createCell(16).setCellValue(cInfo.getZlat());
                 contentRow.createCell(17).setCellValue(cInfo.getZlng());
                 contentRow.createCell(18).setCellValue(cInfo.getZaltitude());
                 contentRow.createCell(19).setCellValue(cInfo.getZspeed());
                 contentRow.createCell(20).setCellValue(cInfo.getZdirection());
                 contentRow.createCell(21).setCellValue(cInfo.getZtime());
                 contentRow.createCell(22).setCellValue(cInfo.getZaddress());
                 contentRow.createCell(23).setCellValue(cInfo.getRunid());
                 contentRow.createCell(24).setCellValue(cInfo.getPeoplenumber());
                 contentRow.createCell(25).setCellValue(cInfo.getEvaluateid());
                 String optionsStr = "";
                 if(cInfo.getOptions() == null){
                 }else if(cInfo.getOptions() == 0){
                	 optionsStr = "没有做出评价";
                 }else if(cInfo.getOptions() == 1){
                	 optionsStr = "非常满意";
                 }else if(cInfo.getOptions() == 2){
                	 optionsStr = "满意";
                 }else if(cInfo.getOptions() == 3){
                	 optionsStr = "一般";
                 }else if(cInfo.getOptions() == 4){
                	 optionsStr = "不满意";
                 }else if(cInfo.getOptions() == 5){
                	 optionsStr = "投诉";
                 }
                 contentRow.createCell(26).setCellValue(optionsStr);
                 contentRow.createCell(27).setCellValue(cInfo.getCompanycode());
                 contentRow.createCell(28).setCellValue(cInfo.getDrivercode());
                 contentRow.createCell(29).setCellValue(cInfo.getDriverid());
                 contentRow.createCell(30).setCellValue(cInfo.getStime());
                 contentRow.createCell(31).setCellValue(cInfo.getEtime());
                 contentRow.createCell(32).setCellValue(cInfo.getMileage());
                 contentRow.createCell(33).setCellValue(cInfo.getAirmileage());
                 contentRow.createCell(34).setCellValue(cInfo.getFuelsurcharge());
                 contentRow.createCell(35).setCellValue(cInfo.getWaitingtime());
                 contentRow.createCell(36).setCellValue(cInfo.getTradeamount());
                 contentRow.createCell(37).setCellValue(cInfo.getVehicletrips());
                 contentRow.createCell(38).setCellValue(cInfo.getTradetime());
                 String tradetypeStr = "";
                 Integer value = cInfo.getTradetype();
                 if(value != null){
	                 if(value == 0){
						tradetypeStr = "现金交易";
					 }else if(value == 1){
						tradetypeStr = "M1 卡交易";
					 }else if(value==3){
						tradetypeStr = "CPU卡交易";
					 }else if(value==9){
						tradetypeStr = "其他";
					 }else if(value==192){
						tradetypeStr = "合乘现金交易";
					 }else if(value==193){
						tradetypeStr = "合乘M1 卡交易";
					 }else if(value==195){
						tradetypeStr = "合乘CPU卡交易";
					 }else{
						tradetypeStr = "";
					 }
                 }
                 contentRow.createCell(39).setCellValue(tradetypeStr);
                 String cardtypeStr = "";
                 if(cInfo.getCardtype() == null){
                 }else if(cInfo.getCardtype() == 1){
                	 cardtypeStr = "M1卡";
                 }else if(cInfo.getCardtype() == 2){
                	 cardtypeStr = "CPU电子钱包";
                 }else if(cInfo.getCardtype() == 3){
                	 cardtypeStr = "CPU电子现金";
                 }
                 contentRow.createCell(40).setCellValue(cardtypeStr);
                 String cardoemStr = "";
                 if(cInfo.getCardoem() == null){
                 }else if(cInfo.getCardoem() == 1){
                	 cardoemStr = "天府通";
                 }else if(cInfo.getCardoem() == 2){
                	 cardoemStr = "和信通";
                 }
                 contentRow.createCell(41).setCellValue(cardoemStr);
                 contentRow.createCell(42).setCellValue(cInfo.getCsn());
                 contentRow.createCell(43).setCellValue(cInfo.getTradecardno());
                 contentRow.createCell(44).setCellValue(cInfo.getTradeseq());
                 contentRow.createCell(45).setCellValue(cInfo.getSamno());
                 contentRow.createCell(46).setCellValue(cInfo.getSamseq());
                 contentRow.createCell(47).setCellValue(cInfo.getCardtradeamount());
                 contentRow.createCell(48).setCellValue(cInfo.getTradebalance());
                 contentRow.createCell(49).setCellValue(cInfo.getExtend());
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("OperateDataAction的方法 exportExcel执行出错，原因：" + e, e);
		}
	}

	public OperateDataService getOperateDataService() {
		return operateDataService;
	}

	public void setOperateDataService(OperateDataService operateDataService) {
		this.operateDataService = operateDataService;
	}

	public OperateData getOperateData() {
		return operateData;
	}

	public void setOperateData(OperateData operateData) {
		this.operateData = operateData;
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

}
