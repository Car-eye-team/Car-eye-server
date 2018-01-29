Ext.define("CommondLogApp.store.CommondLogListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 50 },
	model:"CommondLogApp.model.CommondLogModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/commondlogjson/getCommondLogList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });