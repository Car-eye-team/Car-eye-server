Ext.define("AreaSetApp.store.AreaSetListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"AreaSetApp.model.AreaSetModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/statement/areamorejson/queryAreaMoreList.action',  
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });