/**
* Description: car-eye车辆管理平台
* 文件名：UserLoginAction.java
* 版本信息：1.0
* 日期：2014-2-24
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.common.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.common.domain.EntryParam;
import com.careye.common.domain.TerminalParameter;
import com.careye.common.service.MenuTreeService;
import com.careye.common.service.TerminalDeviceInfoService;
import com.careye.common.service.UserLoginService;
import com.careye.constant.Constant;
import com.careye.constant.WebConstants;
import com.careye.mq.HandleUtil;
import com.careye.mq.ToolsUtil;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.SysAuthLoginLog;
import com.careye.system.domain.UserCar;
import com.careye.system.service.UserService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

import common.Logger;

/**
 * @项目名称：DSTAXI
 * @类名称：TerminalParamAction
 * @类描述：终端参数设置
 * @创建人：zhangrong
 * @创建时间：2015-10-2 下午04:10:05
 * @修改人：zhangrong
 * @修改时间：2015-10-2 下午04:10:05
 * @修改备注：
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class TerminalParamAction extends BasePageAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TerminalParamAction.class);
	
	private TerminalDeviceInfoService terminalDeviceInfoService;;
	private CarService carService;
	
	private TerminalParameter data;
	private TerminalParameter terminalInfo;
	private EntryParam entryParam;
	
	private String carids; 
	private String carid; 
	private String items;
	private String carnumber;
	
	private Map result;
	private String success;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	
    /**
     * 设置终端参数
     */
    public String terminalParmSet() {

        try {
            if (terminalInfo == null) {
            	terminalInfo = new TerminalParameter();
            }
            
            CarInfo carInfo = new CarInfo();
            if(StringUtils.isEmty(carids)){
            	return ERROR;
            }
            String idsarr [] = carids.split(",");
			for (int i = 0; i < idsarr.length; i++) {
				carInfo = carService.getCarInfoCarId(Integer.parseInt(idsarr[i]));
				if(carInfo != null){
		        	terminalInfo.setSimCode(carInfo.getTerminal());
		        	List<Integer> terList = new LinkedList<Integer>();
		        	if(items != null && !items.equals("") && !items.equals("null")){
			            JSONArray itemsArr = JSONArray.fromObject(items);
						for (int j = 0; j < itemsArr.size(); j++) {
							JSONObject jsonObj = itemsArr.getJSONObject(j);
							entryParam=(EntryParam)JSONObject.toBean(jsonObj,EntryParam.class);
							terminalInfo = ToolsUtil.setTerminalInfo(entryParam.getId(), entryParam.getValue(), terminalInfo);
							terList.add(j, entryParam.getId());
						}
						int [] terArr = new int [terList.size()];
						for(int j=0;j<terList.size();j++){
							terArr[j] = terList.get(j);
						}
						terminalInfo.setTerminal(terArr);
						
						HandleUtil.setTerminalParam(carInfo.getUsertype(),terminalInfo,HandleUtil.getSerialId(),carnumber);
						
		            }
		        	
				}
			}

        } catch (Exception e) {
        	logger.error("TerminalParamAction 的方法 terminalParmSet执行出错，原因：" + e, e);
            e.printStackTrace();
        }

        return SUCCESS;

    }
    
    /**
     * 查询终端参数
     */
    public String queryTerminalParm() {
    	
    	try {
    		initMap();
    		if(StringUtils.isEmty(carid)){
    			return ERROR;
    		}
    		
    		//下发查询指令
    		CarInfo carInfo = carService.getCarInfoCarId(Integer.parseInt(carid));
    		HandleUtil.queryTerminalParm(carInfo.getUsertype(), carInfo.getTerminal(), carInfo.getId());
    		
    		Thread.sleep(Constant.TERMINALPARAM_TIMEOUT);
    		
    		//取出终端参数记录返回
    		data = terminalDeviceInfoService.getTerminalParam(carInfo.getId());
    		if(data == null){
    			result.put("su", 0);
    			data = new TerminalParameter();
    		}else{
    			result.put("su", 1);
    		}
    		this.success = "true";
    	} catch (Exception e) {
    		logger.error("TerminalParamAction 的方法 queryTerminalParm执行出错，原因：" + e, e);
    		e.printStackTrace();
    	}
    	
    	return SUCCESS;
    	
    }

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public TerminalParameter getData() {
		return data;
	}

	public void setData(TerminalParameter data) {
		this.data = data;
	}

	public TerminalParameter getTerminalInfo() {
		return terminalInfo;
	}

	public void setTerminalInfo(TerminalParameter terminalInfo) {
		this.terminalInfo = terminalInfo;
	}

	public EntryParam getEntryParam() {
		return entryParam;
	}

	public void setEntryParam(EntryParam entryParam) {
		this.entryParam = entryParam;
	}

	public String getCarids() {
		return carids;
	}

	public void setCarids(String carids) {
		this.carids = carids;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
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

	public TerminalDeviceInfoService getTerminalDeviceInfoService() {
		return terminalDeviceInfoService;
	}

	public void setTerminalDeviceInfoService(
			TerminalDeviceInfoService terminalDeviceInfoService) {
		this.terminalDeviceInfoService = terminalDeviceInfoService;
	}

}





