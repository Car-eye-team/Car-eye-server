Ext.define("TerminalSetApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"TerminalSetApp.model.CarInfoModel",
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