Ext.define('AdvertManageApp.store.TypeStore', {
	extend : "Ext.data.Store",
	model : 'AdvertManageApp.model.TypeModel',
	autoLoad :true,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/advertmanagejson/getTypeList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list'
		}
	}
});
