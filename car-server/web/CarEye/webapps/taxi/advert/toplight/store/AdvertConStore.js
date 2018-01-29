Ext.define('TopLightApp.store.AdvertConStore', {
	extend : "Ext.data.Store",
	model : 'TopLightApp.model.AdvertConModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
//	autoLoad : false,
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/toplightjson/getAdvertConList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
