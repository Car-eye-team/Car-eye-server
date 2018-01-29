Ext.define('AdvertManageApp.store.AdvertVerStore', {
	extend : "Ext.data.Store",
	model : 'AdvertManageApp.model.AdvertVerModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
//	autoLoad : false,
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/advertmanagejson/getAdvertVerList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
