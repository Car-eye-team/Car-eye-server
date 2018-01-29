Ext.define("OnCallCountApp.store.InSystemListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.InSystemModel",
	autoLoad:{ start: 0, limit: 18 },
	pageSize: 18,
	proxy: {
		type: 'ajax',
//		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/oncalltransaction/oncallcountjson/findInSystemList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });