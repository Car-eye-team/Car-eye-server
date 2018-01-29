Ext.define("CarLocationApp.store.CarUseStore",{
	extend:"Ext.data.Store",
	model : 'CarLocationApp.model.CarUseModel',
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/getCarUseList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });