Ext.define("EvaluateCountApp.store.EvaluateCountDetailsStore",{
	extend:"Ext.data.Store",
	autoLoad:false,
	model : 'EvaluateCountApp.model.EvaluateCountDetailsModel',
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/evaluatecountjson/getEvaluateCountDetails.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });
