Ext.define("OnCallCountApp.store.SendSuccessCountListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.SendSuccessCountModel",
	autoLoad: { start: 0, limit: 18 },
	pageSize: 18,
	proxy: {
		type: 'ajax',
//		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/oncalltransaction/answercountjson/findPageSendSuccessList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });