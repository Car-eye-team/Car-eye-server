Ext.define('KeyManageApp.store.KeyListStore', {
	extend : "Ext.data.Store",
	model : 'KeyManageApp.model.KeyModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/keyjson/keyList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
