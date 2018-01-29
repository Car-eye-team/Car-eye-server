Ext.define("CarInfoApp.store.CarUseStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 10 },
	model : 'CarInfoApp.model.CarUseModel',
	pageSize: 10,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/getCarUseList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });