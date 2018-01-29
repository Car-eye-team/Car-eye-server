Ext.define("PhotoRecordApp.store.MediaListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"PhotoRecordApp.model.MultiMediaInfoModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/multimedia/multimediajson/findMultiMediaByMSId.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });