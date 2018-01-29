Ext.define("LogInfoApp.store.LogInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model : 'LogInfoApp.model.LogInfoModel',
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/loginfojson/queryLogInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });