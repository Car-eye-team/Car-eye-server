Ext.define("SendSuccessCountApp.store.SendSuccessCountListStore",{
	extend:"Ext.data.Store",
	model:"SendSuccessCountApp.model.SendSuccessCountModel",
	autoLoad: { start: 0, limit: 30 },
	pageSize: 30,
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