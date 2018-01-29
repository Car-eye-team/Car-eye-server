Ext.define("SearchRecordApp.store.SearchRecordListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model:"SearchRecordApp.model.SearchRecordModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/multimedia/multimediajson/findMultimediaSearchRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });