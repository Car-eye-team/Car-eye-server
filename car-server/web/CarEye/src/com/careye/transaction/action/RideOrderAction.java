/**
 * 
 */
package com.careye.transaction.action;

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
import com.careye.transaction.domain.RideOrder;
import com.careye.transaction.domain.RidePassenger;
import com.careye.transaction.service.RideOrderService;
import com.careye.utils.DateUtil;
import com.careye.utils.ExcelDownWay;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @author Administrator
 *
 */
public class RideOrderAction extends BasePageAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RideOrderAction.class);
	
	private RideOrderService rideOrderService;
	private RideOrder rideOrder;
	private RidePassenger ridePassenger;
	
	private String blocid;
	private String carnumber;
	private String devicenumber;
	private String s_time;
	private String e_time;
	private String fileName;
	private String terminal;
	
	private String id;
	private String orderid;
	
	private String success;
	private Map result;
	private String ids;
	private List list;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 顺风车订单查询分页显示列表
	 * @return
	 */
	public String selRideOrderList(){
		try {
			initMap();
			if (rideOrder == null) {
				rideOrder = new RideOrder();
			}
			if (StringUtils.isNotEmty(blocid)){
				rideOrder.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)){
				rideOrder.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(terminal)){
				rideOrder.setTerminal(URLDecoder.decode(terminal, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(s_time)) {
				rideOrder.setStime(URLDecoder.decode(s_time, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(e_time)) {
				rideOrder.setEtime(URLDecoder.decode(e_time, "UTF-8").trim());
			}
			
			rideOrder.setUserid(SessionUtils.getUserId());
			
			result = rideOrderService.selRideOrderList(this.getPage(), this.getLimit(), rideOrder);
			
			List<RideOrder> list = (List<RideOrder>)result.get("list");
			if(list != null && list.size() > 0){
				for(RideOrder info : list){
					if(StringUtils.isNotEmty(info.getSummile())){
						info.setSummile(info.getSummile()+"KM");
					}
					if(StringUtils.isNotEmty(info.getTotalfee())){
						info.setTotalfee(info.getTotalfee()+"元");
					}
					
					
					if(StringUtils.isNotEmty(info.getStime()) && StringUtils.isNotEmty(info.getEtime())){
						int triptime = (int) (DateUtil.dateDiff(info.getEtime(),info.getStime())/1000);
						int hour = triptime/(60*60);
						int min = (triptime%(60*60))/60;
						int secord = (triptime%(60*60))%60;
						String longtimestr = ""; 
						if(hour != 0){
							longtimestr += hour+"时";
						}
						if(min != 0){
							longtimestr += min+"分";
						}else if(hour != 0){
							longtimestr += min+"分";
						}
						if(secord != 0){
							longtimestr += secord+"秒";
						}
						info.setSumtime(longtimestr);
					}
					
					
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RideOrderAction 的方法 selRideOrderList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 导出
	 * exportExcel
	 * @return
	 */
	public String exportExcel() {
		try {			
			initMap();
			if(rideOrder == null){
				rideOrder = new RideOrder();
			}
			if (StringUtils.isNotEmty(blocid)){
				rideOrder.setBlocid(Integer.parseInt(blocid));
			}
			if (StringUtils.isNotEmty(carnumber)){
				rideOrder.setCarnumber(new String(carnumber.getBytes("iso-8859-1"), "UTF-8"));
			}
			if (StringUtils.isNotEmty(terminal)){
				rideOrder.setTerminal(terminal);
			}
			if (StringUtils.isNotEmty(s_time)) {
				rideOrder.setStime(s_time);
			}
			if (StringUtils.isNotEmty(e_time)) {
				rideOrder.setEtime(e_time);
			}
			
			rideOrder.setUserid(SessionUtils.getUserId());
			
			result = rideOrderService.selRideOrderList(1, Integer.MAX_VALUE , rideOrder);
			
			List<RideOrder> list = (List<RideOrder>) result.get("list");
			if(list != null && list.size() > 0){
				for(RideOrder info : list){
					if(StringUtils.isNotEmty(info.getSummile())){
						info.setSummile(info.getSummile()+"KM");
					}
					if(StringUtils.isNotEmty(info.getTotalfee())){
						info.setTotalfee(info.getTotalfee()+"元");
					}
					
					
					if(StringUtils.isNotEmty(info.getStime()) && StringUtils.isNotEmty(info.getEtime())){
						int triptime = (int) (DateUtil.dateDiff(info.getEtime(),info.getStime())/1000);
						int hour = triptime/(60*60);
						int min = (triptime%(60*60))/60;
						int secord = (triptime%(60*60))%60;
						String longtimestr = ""; 
						if(hour != 0){
							longtimestr += hour+"时";
						}
						if(min != 0){
							longtimestr += min+"分";
						}else if(hour != 0){
							longtimestr += min+"分";
						}
						if(secord != 0){
							longtimestr += secord+"秒";
						}
						info.setSumtime(longtimestr);
					}
					
					
				}
			}
			if(list == null){
				list = new ArrayList<RideOrder>();
			}
			
			// 1.设置名字
			fileName = "顺风车订单列表信息";
			HSSFWorkbook book = new HSSFWorkbook();
			Sheet sheet = book.createSheet(fileName);

			ExcelDownWay exceldownway = new ExcelDownWay();

			// 2.设置列宽（列数要对应上）
			String str = "7,40,25,25,25,25,25,25,25,25,25,25,25,25";
			List<String> numberList = Arrays.asList(str.split(","));
			sheet = exceldownway.setColumnWidth(sheet, numberList);

			sheet.setDefaultRowHeight((short) 18);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("订单号");
			titleRow.createCell(2).setCellValue("集团名称");
			titleRow.createCell(3).setCellValue("车牌号");
			titleRow.createCell(4).setCellValue("设备号");
			titleRow.createCell(5).setCellValue("起点");
			titleRow.createCell(6).setCellValue("终点");
			titleRow.createCell(7).setCellValue("开始时间");
			titleRow.createCell(8).setCellValue("结束时间");
			titleRow.createCell(9).setCellValue("总用车时长");
			titleRow.createCell(10).setCellValue("总行车里程");
			titleRow.createCell(11).setCellValue("总费用");
			titleRow.createCell(12).setCellValue("订单状态");
			titleRow.createCell(13).setCellValue("创建时间");
			
			
			for (int i = 0; i < titleRow.getLastCellNum(); i++) {
				titleRow.getCell(i).setCellStyle(
						exceldownway.setBookHeadStyle(book));
			}

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					int index = i + 1;
					Row contentRow = sheet.createRow(index);
					contentRow.setHeightInPoints(15);
					RideOrder data = (RideOrder) list.get(i);
					contentRow.createCell(0).setCellValue(index);
					contentRow.createCell(1).setCellValue(data.getOrderid());
					contentRow.createCell(2).setCellValue(data.getBloc_name());
					contentRow.createCell(3).setCellValue(data.getCarnumber());
					contentRow.createCell(4).setCellValue(data.getTerminal());
					contentRow.createCell(5).setCellValue(data.getSaddress());
					contentRow.createCell(6).setCellValue(data.getEaddress());
					
					contentRow.createCell(7).setCellValue(data.getStime());
					contentRow.createCell(8).setCellValue(data.getEtime());
					
					contentRow.createCell(9).setCellValue(data.getSumtime());
					contentRow.createCell(10).setCellValue(data.getSummile());
					contentRow.createCell(11).setCellValue(data.getTotalfee());
					
					Integer os = data.getOrdersatus();
					String osStr = "";
					
					if(os == null){
						
					}else if(os == 1){
						osStr = "进行中";
					}else if(os == 2){
						osStr = "完成";
					}
					contentRow.createCell(12).setCellValue(osStr);
					contentRow.createCell(13).setCellValue(data.getCreatetime());
					
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
				logger.error(
						"RideOrderAction的方法 exportExcel执行出错，原因："
								+ e, e);
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 删除顺风车订单
	 * @return
	 */
	public String deleteRideOrder(){
		try {
			initMap();
			String id[] = ids.split(",");
			result.put("su", 0);
			for (int i = 0; i < id.length; i++) {
				int re = rideOrderService.deleteRideOrder(id[i]);
				if (re <= 0) {
					result.put("su", -1);
				}
				int re1 = rideOrderService.deleteRO(id[i]);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RideOrderAction 的方法 deleteRideOrder执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 顺风车乘客列表
	 * selRidePassengerList
	 * @return
	 */
	public String selRidePassengerList(){
		try {
			initMap();
			if (ridePassenger == null) {
				ridePassenger = new RidePassenger();
			}
			if (StringUtils.isNotEmty(s_time)) {
				ridePassenger.setStime(URLDecoder.decode(s_time, "UTF-8").trim());
			}
			if (StringUtils.isNotEmty(e_time)) {
				ridePassenger.setEtime(URLDecoder.decode(e_time, "UTF-8").trim());
			}
			if (!StringUtils.isEmty(ids)) {
				String[] datas = ids.split(",");
				for (int j = 0; j < datas.length; j++) {
					ridePassenger.getIds().add(datas[j]);
				}
			}
			
			ridePassenger.setUserid(SessionUtils.getUserId());
			
			result = rideOrderService.selRidePassengerList(this.getPage(), this.getLimit(), ridePassenger);
			List<RidePassenger> list = (List<RidePassenger>)result.get("list");
			if(list != null && list.size() > 0){
				for(RidePassenger info : list){
					if(StringUtils.isNotEmty(info.getSummile())){
						info.setSummile(info.getSummile()+"KM");
					}
					if(StringUtils.isNotEmty(info.getTotalfee())){
						info.setTotalfee(info.getTotalfee()+"元");
					}
					
					
					if(StringUtils.isNotEmty(info.getStime()) && StringUtils.isNotEmty(info.getEtime())){
						int triptime = (int) (DateUtil.dateDiff(info.getEtime(),info.getStime())/1000);
						int hour = triptime/(60*60);
						int min = (triptime%(60*60))/60;
						int secord = (triptime%(60*60))%60;
						String longtimestr = ""; 
						if(hour != 0){
							longtimestr += hour+"时";
						}
						if(min != 0){
							longtimestr += min+"分";
						}else if(hour != 0){
							longtimestr += min+"分";
						}
						if(secord != 0){
							longtimestr += secord+"秒";
						}
						info.setSumtime(longtimestr);
					}
					
					
				}
			}
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RideOrderAction 的方法 selRidePassengerList 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	
	/**
	 * 删除乘客
	 * @return
	 */
	public String deleteRidePassenger(){
		try {
			initMap();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				rideOrderService.deleteRidePassenger(Integer.parseInt(id[i]));
			}
			result.put("su", 0);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RideOrderAction 的方法 deleteRidePassenger执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 乘客详情
	 * @return
	 * findPassengerTrip
	 */
	public String findPassengerTrip(){
		try {
			initMap();
			RidePassenger ridePassenger = new RidePassenger();
			if (StringUtils.isNotEmty(id)) {
				ridePassenger.setId(Integer.parseInt(id));
			}
			if (StringUtils.isNotEmty(orderid)) {
				ridePassenger.setOrderid(URLDecoder.decode(orderid, "UTF-8").trim());
			}
			 
			ridePassenger = rideOrderService.findPassengerTrip(ridePassenger);
			if(ridePassenger != null){
					if(StringUtils.isNotEmty(ridePassenger.getSummile())){
						ridePassenger.setSummile(ridePassenger.getSummile()+"KM");
					}
					if(StringUtils.isNotEmty(ridePassenger.getTotalfee())){
						ridePassenger.setTotalfee(ridePassenger.getTotalfee()+"元");
					}
					
					
					if(StringUtils.isNotEmty(ridePassenger.getStime()) && StringUtils.isNotEmty(ridePassenger.getEtime())){
						int triptime = (int) (DateUtil.dateDiff(ridePassenger.getEtime(),ridePassenger.getStime())/1000);
						int hour = triptime/(60*60);
						int min = (triptime%(60*60))/60;
						int secord = (triptime%(60*60))%60;
						String longtimestr = ""; 
						if(hour != 0){
							longtimestr += hour+"时";
						}
						if(min != 0){
							longtimestr += min+"分";
						}else if(hour != 0){
							longtimestr += min+"分";
						}
						if(secord != 0){
							longtimestr += secord+"秒";
						}
						ridePassenger.setSumtime(longtimestr);
					}
					result.put("list", ridePassenger);
					
				}
			success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("RideOrderAction 的方法 findPassengerTrip 执行出错，原因："
					+ e, e);
			return ERROR;
		}
	}
	
	

	public RideOrderService getRideOrderService() {
		return rideOrderService;
	}

	public void setRideOrderService(RideOrderService rideOrderService) {
		this.rideOrderService = rideOrderService;
	}

	public RideOrder getRideOrder() {
		return rideOrder;
	}

	public void setRideOrder(RideOrder rideOrder) {
		this.rideOrder = rideOrder;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public String getDevicenumber() {
		return devicenumber;
	}

	public void setDevicenumber(String devicenumber) {
		this.devicenumber = devicenumber;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String sTime) {
		s_time = sTime;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String eTime) {
		e_time = eTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RidePassenger getRidePassenger() {
		return ridePassenger;
	}

	public void setRidePassenger(RidePassenger ridePassenger) {
		this.ridePassenger = ridePassenger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	
	
	
	
	

}
