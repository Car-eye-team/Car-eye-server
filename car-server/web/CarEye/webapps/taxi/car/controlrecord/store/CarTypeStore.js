Ext.define("CarInfoApp.store.CarTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model : 'CarInfoApp.model.CarTypeModel',
    proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/cartypejson/selectCarType.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });