Ext.define("AreaSetApp.store.AreaSendRecordListStore",{
	extend:"Ext.data.Store",
	model:"AreaSetApp.model.AreaSetModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/areasetjson/queryAreaSendRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });