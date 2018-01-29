Ext.define("CarUseApp.store.CarUseListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model : 'CarUseApp.model.CarUseModel',
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carusejson/queryPageCarUseList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });