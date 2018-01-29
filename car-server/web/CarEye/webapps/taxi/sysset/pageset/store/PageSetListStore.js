Ext.define("PageSetApp.store.PageSetListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit:30 },
	model:"PageSetApp.model.PageSetModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/system/userjson/findPageSetList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });