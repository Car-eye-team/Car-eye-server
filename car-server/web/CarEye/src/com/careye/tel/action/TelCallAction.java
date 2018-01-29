/**
* Description: 多森商用车平台
* 文件名：TelCallAction.java
* 版本信息：1.0
* 日期：2014-5-26
* Copyright car-eye 车辆管理平台 Copyright (c) 2014
* 版权所有
*/
package com.careye.tel.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.car.domain.CarInfo;
import com.careye.car.service.CarService;
import com.careye.mq.HandleUtil;
import com.careye.system.domain.BlocUser;
import com.careye.tel.domain.TelCall;
import com.careye.tel.domain.TelCallSend;
import com.careye.tel.service.TelCallService;
import com.careye.utils.SessionUtils;

import common.Logger;

/**
 * @项目名称：FMS
 * @类名称：TelCallAction
 * @类描述：
 * @创建人：lxh
 * @创建时间：2014-5-26 下午01:47:32
 * @修改人：lxh
 * @修改时间：2014-5-26 下午01:47:32
 * @修改备注：
 * @version 1.0
 */
public class TelCallAction extends BasePageAction{
	private static final Logger logger = Logger.getLogger(TelCallAction.class);
	private TelCallService telCallService;
	private CarService carService;
	private TelCall telCall;
	private TelCallSend telCallSend;
	private HandleUtil handleUtil;
	private CarInfo carInfo;
	
	private Map result;
	private String success;
	private List list;
	private String ids;
	private String tes;
	private BlocUser user;
	private int su;
	
	private String carnumber;
	private String tel;
	private String terminal;
	private String deptid;
	private String stime;
	private String etime;
	private String flag;
	
	public void initMap() {
		if(result == null) {
			result = new HashMap();
		}
	}
	/**
	 * 分页查询车辆信息
	 * @return
	 */
	public String queryCarInfoList() {
		
		try {
			initMap();
			if(carInfo==null){
				carInfo=new CarInfo();
			}
			
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				carInfo.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(terminal!=null&&!terminal.equals("")&&!terminal.equals("null")){
				carInfo.setTerminal(URLDecoder.decode(terminal,"UTF-8"));
			}
			if(deptid!=null&&!deptid.equals("")&&!deptid.equals("null")){
				carInfo.setBlocid(Integer.parseInt(deptid));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				carInfo.setUserid(SessionUtils.getUserId());
			}
			result=carService.selectCheckCarInfo(this.getPage(),this.getLimit(), carInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelCallAction的方法 queryCarInfoList执行出错，原因：" + e, e);
			return ERROR;
		}
	}

	/**
	 * 分页查询电话回拨消息发送记录
	 * @return
	 */
	public String queryTelCallListTelCallSend() {
		
		try {
			initMap();
			if(telCallSend==null){
				telCallSend=new TelCallSend();
			}
			if(stime!=null&&!stime.equals("")&&!stime.equals("null")){
				telCallSend.setStime(URLDecoder.decode(stime,"UTF-8"));
			}
			if(etime!=null&&!etime.equals("")&&!etime.equals("null")){
				telCallSend.setEtime(URLDecoder.decode(etime,"UTF-8"));
			}
			if(flag!=null&&!flag.equals("")&&!flag.equals("null")){
				telCallSend.setFlag(Integer.parseInt(flag));
			}
			if(tel!=null&&!tel.equals("")&&!tel.equals("null")){
				telCallSend.setTel(URLDecoder.decode(tel,"UTF-8"));
			}
			if(carnumber!=null&&!carnumber.equals("")&&!carnumber.equals("null")){
				telCallSend.setCarnumber(URLDecoder.decode(carnumber,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				telCallSend.setUserid(SessionUtils.getUserId());
			}
			result=telCallService.selectCheckTelCallTelCallSend(this.getPage(),this.getLimit(), telCallSend);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelCallAction的方法 queryTelCallListTelCallSend执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 分页查询电话回拨消息
	 * @return
	 */
	public String queryTelCallList() {
		
		try {
			initMap();
			if(telCall==null){
				telCall=new TelCall();
			}
			
			if(tel!=null&&!tel.equals("")&&!tel.equals("null")){
				telCall.setTel(URLDecoder.decode(tel,"UTF-8"));
			}
			if(SessionUtils.getUser() == null){
				return SUCCESS;
			}
			if(!SessionUtils.getUser().getLoginname().equals("admin")){
				telCall.setUserid(SessionUtils.getUserId());
			}
			result=telCallService.selectCheckTelCall(this.getPage(),this.getLimit(), telCall);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TelCallAction的方法 queryTelCallList执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	/**
	 * 添加或修改电话回拨消息
	 * @param telCall
	 * @return
	 */
	public String saveTelCall(){
		try {
			initMap();
			if(telCall==null){
				telCall=new TelCall();
			}
//			this.user = (User) ServletActionContext.getRequest().getSession().getAttribute(WebConstants.LOGIN_USER);	
			this.user = SessionUtils.getUser();
			telCall.setUserid(SessionUtils.getUserId());
			telCall.setDeptid(this.user.getBlocid());
			int c=telCallService.queryTelIsExist(telCall);
			if(c>0){
				result.put("su", -2);
			}else{
				if(telCall.getId()==null){
					int count = telCallService.insertTelCall(telCall);
					if(count<=0){
						result.put("su", -1);
					}else{
						result.put("su", 1);
					}
				}else{
					int re=telCallService.updateTelCall(telCall);
					if(re<=0){
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
			logger.error("TelCallAction的方法 saveTelCall执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 回拨电话
	 * @param telCall
	 * @return
	 */
	public String CheckTelCall(){
		try {
			initMap();
			if(telCall==null){
				telCall=new TelCall();
			}
			if(telCallSend==null){
				telCallSend=new TelCallSend();
			}
			String id[] = tes.split(",");
			List<CarInfo> list=this.SelectCarInfo();
				for (CarInfo car: list) {
					for (int i = 0; i < id.length; i++) {
						//下发
						telCall=telCallService.selectTelCallById(Integer.parseInt(id[i]));
						int seq=HandleUtil.getSerialId(); 
						String data=handleUtil.telephoneCallBack(car.getUsertype(),car.getTerminal(),telCall.getFlag(),telCall.getTel(),seq,car.getCarnumber());
						telCallSend.setSeq(seq);
						telCallSend.setDeptid(telCall.getDeptid());
						telCallSend.setUserid(SessionUtils.getUserId());
						telCallSend.setTel(telCall.getTel());
						telCallSend.setCalltype(telCall.getCalltype());
						telCallSend.setFlag(telCall.getFlag());
						telCallSend.setMsgid(telCall.getId());
						telCallSend.setCarnumber(car.getCarnumber());
						telCallSend.setData(data);
						telCallService.insertTelCallTelCallSend(telCallSend);
					}	
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TelCallAction的方法 CheckTelCall执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	/**
	 * 查询车辆信息
	 * @param telCall
	 * @return
	 */
	public List<CarInfo> SelectCarInfo(){
		try {
			initMap();
			List<CarInfo> list=new ArrayList<CarInfo>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				list.add(carService.getCarInfoCarId(Integer.parseInt(id[i])));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TelCallAction的方法 SelectCarInfo执行出错，原因：" + e, e);
			return null;
		}
	}
	
	/**
	 * 删除电话回拨消息
	 * @param telCall
	 * @return
	 */
	public String deleteTelCall(){
		try {
			initMap();
			if(telCall==null){
				telCall=new TelCall();
			}
			if(telCallSend==null){
				telCallSend=new TelCallSend();
			}
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				telCallService.deleteTelCall(Integer.parseInt(id[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TelCallAction的方法 deleteTelCall执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	public TelCallService getTelCallService() {
		return telCallService;
	}
	public void setTelCallService(TelCallService telCallService) {
		this.telCallService = telCallService;
	}
	public CarService getCarService() {
		return carService;
	}
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	public TelCall getTelCall() {
		return telCall;
	}
	public void setTelCall(TelCall telCall) {
		this.telCall = telCall;
	}
	public TelCallSend getTelCallSend() {
		return telCallSend;
	}
	public void setTelCallSend(TelCallSend telCallSend) {
		this.telCallSend = telCallSend;
	}
	public HandleUtil getHandleUtil() {
		return handleUtil;
	}
	public void setHandleUtil(HandleUtil handleUtil) {
		this.handleUtil = handleUtil;
	}
	public CarInfo getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
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
	public String getTes() {
		return tes;
	}
	public void setTes(String tes) {
		this.tes = tes;
	}
	
	public int getSu() {
		return su;
	}
	public void setSu(int su) {
		this.su = su;
	}
	public String getCarnumber() {
		return carnumber;
	}
	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BlocUser getUser() {
		return user;
	}
	public void setUser(BlocUser user) {
		this.user = user;
	}

	
	
}
