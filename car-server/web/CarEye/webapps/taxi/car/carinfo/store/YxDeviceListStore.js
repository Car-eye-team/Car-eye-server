Ext.define("CarInfoApp.store.YxDeviceListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"CarInfoApp.model.YxDeviceInfoModel",
	proxy: {
		type: 'ajax',
		timeout:300000,
		url: window.BIZCTX_PATH + '/carjson/selectYxDeviceList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });