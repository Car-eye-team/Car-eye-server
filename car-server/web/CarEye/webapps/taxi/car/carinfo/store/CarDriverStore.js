Ext.define("CarInfoApp.store.CarDriverStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CarInfoApp.model.CarDriverModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/selectCarDriverList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });