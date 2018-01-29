Ext.define("TelBookApp.store.SendRecordListStore",{
	extend:"Ext.data.Store",
	//autoLoad: { start: 0, limit: 15 },
	model:"TelBookApp.model.SendRecordModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/telbookjson/queryCheckTelBookSendList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });