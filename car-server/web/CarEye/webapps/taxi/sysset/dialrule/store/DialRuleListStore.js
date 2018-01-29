Ext.define("DialRuleApp.store.DialRuleListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model : 'DialRuleApp.model.DialRuleModel',
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/dialrulejson/findRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });