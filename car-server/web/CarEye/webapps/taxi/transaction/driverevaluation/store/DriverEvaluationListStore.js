Ext.define("DriverEvaluationApp.store.DriverEvaluationListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 50 },
	model:"DriverEvaluationApp.model.DriverEvaluationModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/driverevaluationjson/getDriverEvaluationList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });