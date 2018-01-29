Ext.define('AppManageApp.store.AppVersionListStore', {
	extend : "Ext.data.Store",
	model : 'AppManageApp.model.AppVersionModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/appmanagejson/appVersionList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
