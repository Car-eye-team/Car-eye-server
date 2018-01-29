Ext.define('HostApp.store.AdvertVerStore', {
	extend : "Ext.data.Store",
	model : 'HostApp.model.AdvertVerModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
//	autoLoad : false,
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/hostjson/getAdvertVerList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
