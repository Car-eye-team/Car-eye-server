Ext.define('AppManageApp.store.AppTypeListStore', {
	extend : "Ext.data.Store",
	model : 'AppManageApp.model.AppTypeModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/appmanagejson/appTypeList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
