Ext.define("DialRuleApp.store.LastDialRuleStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model : 'DialRuleApp.model.DialRuleModel',
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/dialrulejson/findLastDialRule.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.dialRule'
		}
	}
 });