Ext.define("CarInfoApp.store.DeviceTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CarInfoApp.model.DeviceTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/getDeviceTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });