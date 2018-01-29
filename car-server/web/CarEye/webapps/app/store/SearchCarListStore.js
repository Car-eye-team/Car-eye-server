Ext.define("FMS.store.SearchCarListStore",{
	extend:"Ext.data.Store",
	model:"FMS.model.CarstatusModel",
	autoLoad:false,
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/reportformsjson/getCarLocationList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });