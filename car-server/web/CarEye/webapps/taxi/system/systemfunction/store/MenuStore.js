Ext.define("systemFunctionApp.store.MenuStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"systemFunctionApp.model.MenuModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/system/systemfunctionjson/menuList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });