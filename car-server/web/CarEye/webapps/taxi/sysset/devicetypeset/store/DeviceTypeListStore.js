Ext.define("DeviceTypeApp.store.DeviceTypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model:"DeviceTypeApp.model.DeviceTypeModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/setjson/queryDeviceTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });