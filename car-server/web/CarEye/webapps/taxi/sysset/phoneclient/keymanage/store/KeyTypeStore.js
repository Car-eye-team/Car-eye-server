Ext.define("KeyManageApp.store.KeyTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"KeyManageApp.model.AppTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/keyjson/selAppTypeList.action', // 请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });