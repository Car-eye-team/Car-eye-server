Ext.define("TextInfoApp.store.SendRecordListStore",{
	extend:"Ext.data.Store",
	//autoLoad: { start: 0, limit: 15 },
	model:"TextInfoApp.model.SendRecordModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/textinfojson/queryTextInfoListSendRecord.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });