Ext.define("EvaluateCountApp.store.EvaluateCountListStore",{
	extend:"Ext.data.Store",
	autoLoad:false,
	model : 'EvaluateCountApp.model.EvaluateCountListModel',
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/evaluatecountjson/getEvaluateCountList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });
