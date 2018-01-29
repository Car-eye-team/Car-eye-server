Ext.define("AreaSetApp.store.CarListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"AreaSetApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/terminalpositionjson/selectCarList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });