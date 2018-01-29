Ext.define("systemFunctionApp.store.SystemFunctionListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	autoLoad: { start: 0, limit: 30 },
	model:"systemFunctionApp.model.SystemFunctionModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/system/systemfunctionjson/systemFunctionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });