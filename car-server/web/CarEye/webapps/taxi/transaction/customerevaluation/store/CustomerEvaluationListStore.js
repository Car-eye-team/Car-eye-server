Ext.define("CustomerEvaluationApp.store.CustomerEvaluationListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 50 },
	model:"CustomerEvaluationApp.model.CustomerEvaluationModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/customerevaluationjson/getCustomerEvaluationList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });