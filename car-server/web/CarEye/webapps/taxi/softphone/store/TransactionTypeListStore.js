Ext.define("SoftPhoneApp.store.TransactionTypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"SoftPhoneApp.model.TransactionTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/transaction/transactionjson/selTransactionTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });