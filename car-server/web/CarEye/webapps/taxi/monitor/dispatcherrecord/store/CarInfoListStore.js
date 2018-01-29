Ext.define("DispatcherRecordApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"DispatcherRecordApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		//timeout:180000,
		url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarList.action',
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });