Ext.define('TopLightApp.store.VersionStore', {
	extend : "Ext.data.Store",
	model : 'TopLightApp.model.AdvertVerModel',
	autoLoad :true,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/toplightjson/getVersionList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list'
		}
	}
});
