 Ext.define("GoodsFindApp.store.CarHistoryPositionStore",{
	extend:"Ext.data.Store",
	autoLoad:false,
	model:"GoodsFindApp.model.CarHistoryPositioModel",
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/yxDevicePositionjson/queryHistoryPosition.action',  
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });