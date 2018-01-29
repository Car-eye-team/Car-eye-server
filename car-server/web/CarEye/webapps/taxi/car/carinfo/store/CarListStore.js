Ext.define("CarInfoApp.store.CarListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CarInfoApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:300000,
		url: window.BIZCTX_PATH + '/terminalpositionjson/selectCarList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });