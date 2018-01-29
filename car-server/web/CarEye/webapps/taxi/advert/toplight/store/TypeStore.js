Ext.define('TopLightApp.store.TypeStore', {
	extend : "Ext.data.Store",
	model : 'TopLightApp.model.TypeModel',
	autoLoad :true,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/toplightjson/getTypeList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list'
		}
	}
});
