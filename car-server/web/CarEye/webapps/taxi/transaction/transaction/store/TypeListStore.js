Ext.define("TransactionApp.store.TypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"TransactionApp.model.TypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/transaction/transactionjson/selTransactionTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });