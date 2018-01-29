Ext.define("AccStatusApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"AccStatusApp.model.CarInfoModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/statement/statementjson/selectCarPosition.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });