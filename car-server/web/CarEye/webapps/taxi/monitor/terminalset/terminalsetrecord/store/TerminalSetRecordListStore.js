Ext.define("TerminalSetRecordApp.store.TerminalSetRecordListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model:"TerminalSetRecordApp.model.TerminalSetRecordModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/remotecontrolrecord/remotecontrolrecordjson/findPageTerminalSetRecord.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });