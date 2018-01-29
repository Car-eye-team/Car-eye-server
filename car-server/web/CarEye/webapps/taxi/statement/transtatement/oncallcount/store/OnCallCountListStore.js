Ext.define("OnCallCountApp.store.OnCallCountListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.OnCallCountModel",
	autoLoad:{ start: 0, limit: 30 },
	pageSize: 30,
	proxy: {
		type: 'ajax',
//		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/oncalltransaction/oncallcountjson/findOnCallCountData.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });