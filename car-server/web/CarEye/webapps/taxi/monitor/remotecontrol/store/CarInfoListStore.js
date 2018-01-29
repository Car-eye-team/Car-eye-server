Ext.define("RemoteControlApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"RemoteControlApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/terminalpositionjson/queryCarList.action',
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });