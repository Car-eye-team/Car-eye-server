Ext.define("OnCallCountApp.store.TransactionListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.TransactionModel",
	autoLoad:{ start: 0, limit: 18 },
	pageSize: 18,
	proxy: {
		type: 'ajax',
//		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/oncalltransaction/oncallcountjson/findTransactionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });