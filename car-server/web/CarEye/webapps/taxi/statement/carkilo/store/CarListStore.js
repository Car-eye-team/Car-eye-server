Ext.define("CarKiloApp.store.CarListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CarKiloApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:300000,
		url: window.BIZCTX_PATH + '/carjson/selectCarList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });