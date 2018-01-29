Ext.define('AdvertManageApp.store.VersionStore', {
	extend : "Ext.data.Store",
	model : 'AdvertManageApp.model.AdvertVerModel',
	autoLoad :true,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/advertmanagejson/getVersionList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list'
		}
	}
});
