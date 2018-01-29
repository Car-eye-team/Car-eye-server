Ext.define('HostApp.store.VersionStore', {
	extend : "Ext.data.Store",
	model : 'HostApp.model.AdvertVerModel',
	autoLoad :true,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/hostjson/getVersionList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list'
		}
	}
});
