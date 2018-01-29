Ext.define("OnCallCountApp.store.CusBreachListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.CusBreachModel",
	autoLoad: true,
	pageSize:18,
	autoLoad: { start: 0, limit: 18 },
	proxy: {
		type: 'ajax',
		//timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/transtatementjson/selectCusBreachList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });