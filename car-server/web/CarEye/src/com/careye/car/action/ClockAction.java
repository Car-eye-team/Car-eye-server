package com.careye.car.action;

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
import com.careye.car.service.ClockService;
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
 * @创建时间：2015-11-3 下午03:06:31
 * @修改人：ll
 * @修改时间：2015-11-3 下午03:06:31
 * @修改备注：
 * @version 1.0
 */
public class ClockAction extends BasePageAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ClockAction.class);
	
	private ClockService clockService;
	private ClockInfo clockInfo;
	
	private String blocid;
	private String carnumber;
	private String terminal;
	private String startstime;
	private String startetime;
	private String endstime;
	private String endetime;
	
	private Map result;
	private String success;
	
	private String fileName;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 分页查询考勤信息
	 * @return
	 */
	public String getClockInfoList() {

		try {
			initMap();
			if(clockInfo==null){
				clockInfo=new ClockInfo();
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}else{
				clockInfo.setUserid(SessionUtils.getUserId());
			}
			if(StringUtils.isNotEmty(blocid)){
				clockInfo.setBlocid(Integer.parseInt(blocid.trim()));
			}else{
				clockInfo.setBlocid(SessionUtils.getUser().getBlocid());
			}
			if(StringUtils.isNotEmty(carnumber)){
				clockInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8").toUpperCase().trim());
			}
			if(StringUtils.isNotEmty(terminal)){
				clockInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(startstime)){
				clockInfo.setStartstime(URLDecoder.decode(startstime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(startetime)){
				clockInfo.setStartetime(URLDecoder.decode(startetime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(endstime)){
				clockInfo.setEndstime(URLDecoder.decode(endstime,"UTF-8").trim());
			}
			if(StringUtils.isNotEmty(endetime)){
				clockInfo.setEndetime(URLDecoder.decode(endetime,"UTF-8").trim());
			}
			
			result=clockService.getClockInfoList(this.getPage(),this.getLimit(), clockInfo);
			List<ClockInfo> list = (List<ClockInfo>) result.get("list");
			if(list != null && list.size() > 0){
				for(ClockInfo info : list){
					if(info.getJstmie() != null){
						int jstmie = Integer.parseInt(info.getJstmie());
						int hour = jstmie/(60*60);
						int min = (jstmie%(60*60))/60;
						int secord = (jstmie%(60*60))%60;
						String jstmiestr = ""; 
						if(hour != 0){
							jstmiestr += hour+"时";
						}
						if(min != 0){
							jstmiestr += min+"分";
						}else if(hour != 0){
							jstmiestr += min+"分";
						}
						if(secord != 0){
							jstmiestr += secord+"秒";
						}
						info.setJstmie(jstmiestr);
					}
					
					if(info.getTotalwaittime() != null){
						int totalwaittime = Integer.parseInt(info.getTotalwaittime());
						int hour = totalwaittime/(60*60);
						int min = (totalwaittime%(60*60))/60;
						int secord = (totalwaittime%(60*60))%60;
						String totalwaittimestr = ""; 
						if(hour != 0){
							totalwaittimestr += hour+"时";
						}
						if(min != 0){
							totalwaittimestr += min+"分";
						}else if(hour != 0){
							totalwaittimestr += min+"分";
						}
						if(secord != 0){
							totalwaittimestr += secord+"秒";
						}
						info.setTotalwaittime(totalwaittimestr);
					}
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ClockAction的方法 getClockInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
     * Excel导出
     * @throws IOException
     */
	public void exportExcel(){
		try {
		 fileName="营运考勤信息"; 
    	 HSSFWorkbook book = new HSSFWorkbook();
         Sheet sheet= book.createSheet(fileName);sheet.setDefaultColumnWidth(15);
        ExcelDownWay exceldownway= new ExcelDownWay();
         
         //2.设置列宽（列数要对应上）
         String str="7,20,20,20,20,20,20,20,20,25,20,20,20,20,20,20,25,25,20,20,20,20,20,20,20,20,20,20";
         List<String> numberList=Arrays.asList(str.split(","));
         sheet= exceldownway.setColumnWidth(sheet,numberList);
         
         sheet.setDefaultRowHeight((short) 18);
         Row titleRow= sheet.createRow(0);
         titleRow.setHeightInPoints(20);
         
        if(clockInfo == null){
        	clockInfo = new ClockInfo();
		}
		if(SessionUtils.getUser() == null){
			return ;
		}else{
			clockInfo.setUserid(SessionUtils.getUserId());
		}
		if(StringUtils.isNotEmty(blocid)){
			clockInfo.setBlocid(Integer.parseInt(blocid.trim()));
		}else{
			clockInfo.setBlocid(SessionUtils.getUser().getBlocid());
		}
		if(StringUtils.isNotEmty(carnumber)){
			clockInfo.setCarnumber(new String(carnumber.getBytes("ISO-8859-1"),"utf-8").toUpperCase().trim());
		}
		if(StringUtils.isNotEmty(terminal)){
			clockInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8").trim());
		}
		if(StringUtils.isNotEmty(startstime)){
			clockInfo.setStartstime(URLDecoder.decode(startstime,"UTF-8").trim());
		}
		if(StringUtils.isNotEmty(startetime)){
			clockInfo.setStartetime(URLDecoder.decode(startetime,"UTF-8").trim());
		}
		if(StringUtils.isNotEmty(endstime)){
			clockInfo.setEndstime(URLDecoder.decode(endstime,"UTF-8").trim());
		}
		if(StringUtils.isNotEmty(endetime)){
			clockInfo.setEndetime(URLDecoder.decode(endetime,"UTF-8").trim());
		}
        List<ClockInfo>  list= clockService.exportClockInfo(clockInfo);   //得到Excel数据
        
        if(list == null){
        	list = new ArrayList<ClockInfo>();
        }
         
         titleRow.createCell(0).setCellValue("序号");
         titleRow.createCell(1).setCellValue("集团");
         titleRow.createCell(2).setCellValue("车牌号");
         titleRow.createCell(3).setCellValue("设备号");
         titleRow.createCell(4).setCellValue("单位代码");
         titleRow.createCell(5).setCellValue("司机代码");
         titleRow.createCell(6).setCellValue("驾驶员唯一编号");
         titleRow.createCell(7).setCellValue("类型");
         titleRow.createCell(8).setCellValue("车辆自编号");
         titleRow.createCell(9).setCellValue("时间");
         titleRow.createCell(10).setCellValue("总营运次数");
         titleRow.createCell(11).setCellValue("操作结果");
         titleRow.createCell(12).setCellValue("脉冲数");
         titleRow.createCell(13).setCellValue("卡次");
         titleRow.createCell(14).setCellValue("总营运次数");
         titleRow.createCell(15).setCellValue("车次");
         titleRow.createCell(16).setCellValue("上班时间");
         titleRow.createCell(17).setCellValue("下班时间");
         titleRow.createCell(18).setCellValue("当班公里");
         titleRow.createCell(19).setCellValue("当班营运公里");
         titleRow.createCell(20).setCellValue("计时时间");
         titleRow.createCell(21).setCellValue("总计金额(元)");
         titleRow.createCell(22).setCellValue("卡收金额(元)");
         titleRow.createCell(23).setCellValue("班间公里");
         titleRow.createCell(24).setCellValue("总计公里");
         titleRow.createCell(25).setCellValue("总营运公里");
         titleRow.createCell(26).setCellValue("单价(元)");
         titleRow.createCell(27).setCellValue("总等待时间");
         
         for(int i=0;i<titleRow.getLastCellNum();i++){
        	 titleRow.getCell(i).setCellStyle(exceldownway.setBookHeadStyle(book));
         }
         
         
         if(list.size()>0){
             for(int i=0;i<list.size();i++){
                 int  index=i+1;
                 Row contentRow= sheet.createRow(index);
                 contentRow.createCell(0).setCellValue(index);
                 ClockInfo cInfo= (ClockInfo) list.get(i);
                 contentRow.createCell(1).setCellValue(cInfo.getBlocname());
                 contentRow.createCell(2).setCellValue(cInfo.getCarnumber());
                 contentRow.createCell(3).setCellValue(cInfo.getTerminal());
                 contentRow.createCell(4).setCellValue(cInfo.getCompanycode());
                 contentRow.createCell(5).setCellValue(cInfo.getDrivercode());
                 contentRow.createCell(6).setCellValue(cInfo.getDriverid());
                 String typeStr = "";
                 if(cInfo.getType() == null){
                 }else if(cInfo.getType() == 1){
                	 typeStr = "上班";
                 }else if(cInfo.getType() == 2){
                	 typeStr = "下班";
                 }
                 contentRow.createCell(7).setCellValue(typeStr);
                 contentRow.createCell(8).setCellValue(cInfo.getVehicleid());
                 contentRow.createCell(9).setCellValue(cInfo.getSignintime());
                 contentRow.createCell(10).setCellValue(cInfo.getCount());
                 String resultStr = "";
                 if(cInfo.getResult() == null){
                 }else if(cInfo.getResult() == 144){
                	 resultStr = "执行成功";
                 }else if(cInfo.getResult() == 255){
                	 resultStr = "执行错误";
                 }
                 contentRow.createCell(11).setCellValue(resultStr);
                 contentRow.createCell(12).setCellValue(cInfo.getMcs());
                 contentRow.createCell(13).setCellValue(cInfo.getCardnum());
                 contentRow.createCell(14).setCellValue(cInfo.getTotalnumber());
                 contentRow.createCell(15).setCellValue(cInfo.getVehicletrips());
                 contentRow.createCell(16).setCellValue(cInfo.getStime());
                 contentRow.createCell(17).setCellValue(cInfo.getEtime());
                 contentRow.createCell(18).setCellValue(cInfo.getDbmileage());
                 contentRow.createCell(19).setCellValue(cInfo.getDbyymileage());
                 if(cInfo.getJstmie() != null){
						int jstmie = Integer.parseInt(cInfo.getJstmie());
						int hour = jstmie/(60*60);
						int min = (jstmie%(60*60))/60;
						int secord = (jstmie%(60*60))%60;
						String jstmiestr = ""; 
						if(hour != 0){
							jstmiestr += hour+"时";
						}
						if(min != 0){
							jstmiestr += min+"分";
						}else if(hour != 0){
							jstmiestr += min+"分";
						}
						if(secord != 0){
							jstmiestr += secord+"秒";
						}
						contentRow.createCell(20).setCellValue(jstmiestr);
				 }else{
					    contentRow.createCell(20).setCellValue("");
				 }
                 contentRow.createCell(21).setCellValue(cInfo.getTotalamount());
                 contentRow.createCell(22).setCellValue(cInfo.getCardamount());
                 contentRow.createCell(23).setCellValue(cInfo.getBjmileage());
                 contentRow.createCell(24).setCellValue(cInfo.getTotalmileage());
                 contentRow.createCell(25).setCellValue(cInfo.getTotalyymileage());
                 contentRow.createCell(26).setCellValue(cInfo.getPrice());
                 if(cInfo.getTotalwaittime() != null){
						int totalwaittime = Integer.parseInt(cInfo.getTotalwaittime());
						int hour = totalwaittime/(60*60);
						int min = (totalwaittime%(60*60))/60;
						int secord = (totalwaittime%(60*60))%60;
						String totalwaittimestr = ""; 
						if(hour != 0){
							totalwaittimestr += hour+"时";
						}
						if(min != 0){
							totalwaittimestr += min+"分";
						}else if(hour != 0){
							totalwaittimestr += min+"分";
						}
						if(secord != 0){
							totalwaittimestr += secord+"秒";
						}
						contentRow.createCell(27).setCellValue(totalwaittimestr);
					}else{
						contentRow.createCell(27).setCellValue("");
					}
             }
         }
         
         new ExcelDownWay().getCommonExcelListWay(book,fileName);
        
		} catch (Exception e) {
			logger.error("ClockAction的方法 exportExcel执行出错，原因：" + e, e);
		}
	}

	public ClockService getClockService() {
		return clockService;
	}

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}

	public ClockInfo getClockInfo() {
		return clockInfo;
	}

	public void setClockInfo(ClockInfo clockInfo) {
		this.clockInfo = clockInfo;
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

	public String getStartstime() {
		return startstime;
	}

	public void setStartstime(String startstime) {
		this.startstime = startstime;
	}

	public String getStartetime() {
		return startetime;
	}

	public void setStartetime(String startetime) {
		this.startetime = startetime;
	}

	public String getEndstime() {
		return endstime;
	}

	public void setEndstime(String endstime) {
		this.endstime = endstime;
	}

	public String getEndetime() {
		return endetime;
	}

	public void setEndetime(String endetime) {
		this.endetime = endetime;
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
	
	
	
}
