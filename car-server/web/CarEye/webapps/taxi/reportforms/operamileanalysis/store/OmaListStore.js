Ext.define("OmaApp.store.OmaListStore",{
	extend:"Ext.data.Store",
	model:"OmaApp.model.OmaListModel",
	autoLoad: { start: 0, limit: 30 },
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getOmaList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });