Ext.define("FMS.store.TerminalPositionStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"FMS.model.TerminalPositionInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/terminalpositionjson/terminalPositionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });