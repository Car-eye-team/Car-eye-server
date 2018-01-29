Ext.define("CarTypeApp.store.CarTypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model : 'CarTypeApp.model.CarTypeModel',
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/cartypejson/queryPageCarTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });