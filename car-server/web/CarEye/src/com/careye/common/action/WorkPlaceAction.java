/**
 * 
 */
package com.careye.common.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.careye.base.action.BasePageAction;
import com.careye.common.domain.WorkPlace;
import com.careye.common.service.OcsWorkPlaceService;
import com.careye.common.service.WorkPlaceService;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;

import common.Logger;


/**
 * @author Administrator
 *
 */
public class WorkPlaceAction extends BasePageAction{
	
private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(WorkPlaceAction.class);
	
	private WorkPlaceService workPlaceService;
	private OcsWorkPlaceService ocsWorkPlaceService;
	private WorkPlace data;
	private WorkPlace dataBasicData;
	private WorkPlace dataOnlineSta;
	private WorkPlace dataOperationSta;
	private WorkPlace dataServiceQuality;
	private WorkPlace dataocs;
	private String success;
	
	
	private Map result;
	
	public void initMap() {
		if (result == null) {
			result = new HashMap();
		}
	}
	
	/**
	 * 一次加在全部数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadMyData() {
		try {
			initMap();
			if(SessionUtils.getUser() == null){
				return ERROR;
			}
			Map map = new HashMap();
			data = new WorkPlace();
			map.put("userid", SessionUtils.getUserId());
			map.put("createtime", DateUtil.getSQLDate());
			
			//基础数据
			dataBasicData = workPlaceService.loadBasicData(map);
			if(dataBasicData == null){
				dataBasicData = new WorkPlace();
			}
			data.setBloccount(dataBasicData.getBloccount());
			data.setCarcount(dataBasicData.getCarcount());
			data.setDrivercount(dataBasicData.getDrivercount());
			
			//在线统计
			dataOnlineSta = workPlaceService.loadOnlineSta(map);
			if(dataOnlineSta == null){
				dataOnlineSta = new WorkPlace();
			}
			DecimalFormat df = new DecimalFormat("0.00");
			double dboc = 100*(Double.parseDouble(dataOnlineSta.getOnlinecar())/(Double.parseDouble(dataOnlineSta.getOnlinecar())+Double.parseDouble(dataOnlineSta.getUnonlinecar())));
			data.setOnlinecount(df.format(dboc));
			double kz = 100*(Double.parseDouble(dataOnlineSta.getZhongcar())/(Double.parseDouble(dataOnlineSta.getZhongcar())+Double.parseDouble(dataOnlineSta.getKongcar())));
			data.setKzcount(df.format(kz));
			data.setOnlinecar(dataOnlineSta.getOnlinecar());
			data.setUnonlinecar(dataOnlineSta.getUnonlinecar());
			data.setZhongcar(dataOnlineSta.getZhongcar());
			data.setKongcar(dataOnlineSta.getKongcar());
			
			
			//营运统计
			dataOperationSta = workPlaceService.loadOperationSta(map);
			if(dataOperationSta == null){
				dataOperationSta = new WorkPlace();
			}
			data.setDialordercount(dataOperationSta.getDialordercount());
			data.setCustelcount(dataOperationSta.getCustelcount());
//			data.setOperaincome(dataOperationSta.getOperaincome());
			data.setCashpaymoney(df.format(Double.parseDouble(dataOperationSta.getCashpaymoney())));
			data.setCashpaycount(dataOperationSta.getCashpaycount());
			
			//运营统计 dsocs部分
			dataocs = ocsWorkPlaceService.loadOperationStaForOcs(map);
			if(dataocs == null){
				dataocs = new WorkPlace();
			}
			data.setAlipaymoney(df.format(Double.parseDouble(dataocs.getAlipaymoney())));
			data.setAlipaycount(dataocs.getAlipaycount());
			
			
			//微信支付的单位为分    转化为  元   
			String wxmoney = df.format(Double.parseDouble(dataocs.getWeixinmoney())/100);
			
			data.setWeixinmoney(wxmoney);
			data.setWeixincount(dataocs.getWeixincount());
			
			double oi1 = Double.parseDouble(dataOperationSta.getCashpaymoney())
						+Double.parseDouble(dataocs.getAlipaymoney())
						+Double.parseDouble(wxmoney);
			data.setOperaincome(df.format(oi1));
			
			//加载服务质量
			dataServiceQuality = workPlaceService.loadServiceQuality(map);
			if(dataServiceQuality == null){
				dataServiceQuality = new WorkPlace();
			}
			data.setComplaintcount(dataServiceQuality.getComplaintcount());
			data.setCusgoodcount(dataServiceQuality.getCusgoodcount());
			data.setCussecondarycount(dataServiceQuality.getCussecondarycount());
			data.setCuspoorcount(dataServiceQuality.getCuspoorcount());
			
			this.success = "true";
			return SUCCESS;
		} catch (Exception e) {
			logger.error("WorkPlaceAction 的方法 loadMyData执行出错，原因：" + e, e);
			return ERROR;
		}
	}
	
	
	public WorkPlaceService getWorkPlaceService() {
		return workPlaceService;
	}

	public void setWorkPlaceService(WorkPlaceService workPlaceService) {
		this.workPlaceService = workPlaceService;
	}

	public WorkPlace getData() {
		return data;
	}

	public void setData(WorkPlace data) {
		this.data = data;
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

	public OcsWorkPlaceService getOcsWorkPlaceService() {
		return ocsWorkPlaceService;
	}

	public void setOcsWorkPlaceService(OcsWorkPlaceService ocsWorkPlaceService) {
		this.ocsWorkPlaceService = ocsWorkPlaceService;
	}

	public WorkPlace getDataocs() {
		return dataocs;
	}

	public void setDataocs(WorkPlace dataocs) {
		this.dataocs = dataocs;
	}

	public WorkPlace getDataBasicData() {
		return dataBasicData;
	}

	public void setDataBasicData(WorkPlace dataBasicData) {
		this.dataBasicData = dataBasicData;
	}

	public WorkPlace getDataOnlineSta() {
		return dataOnlineSta;
	}

	public void setDataOnlineSta(WorkPlace dataOnlineSta) {
		this.dataOnlineSta = dataOnlineSta;
	}

	public WorkPlace getDataOperationSta() {
		return dataOperationSta;
	}

	public void setDataOperationSta(WorkPlace dataOperationSta) {
		this.dataOperationSta = dataOperationSta;
	}

	public WorkPlace getDataServiceQuality() {
		return dataServiceQuality;
	}

	public void setDataServiceQuality(WorkPlace dataServiceQuality) {
		this.dataServiceQuality = dataServiceQuality;
	}
	
	
	
	
}
