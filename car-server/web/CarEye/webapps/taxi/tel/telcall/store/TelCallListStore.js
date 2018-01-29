Ext.define("TelCallApp.store.TelCallListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"TelCallApp.model.TelCallModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/telcalljson/queryTelCallList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });