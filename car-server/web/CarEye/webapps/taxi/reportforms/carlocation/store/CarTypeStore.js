Ext.define("CarLocationApp.store.CarTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model : 'CarLocationApp.model.CarTypeModel',
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/getCarTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });