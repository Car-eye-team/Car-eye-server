Ext.define("OnCallCountApp.store.TransactionAnswerListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.TransactionAnswerModel",
	autoLoad:{ start: 0, limit: 30 },
	pageSize: 30,
	proxy: {
		type: 'ajax',
//		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/oncalltransaction/oncallcountjson/findTransactionAnswerList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });