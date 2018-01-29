Ext.define("DutyDriverApp.store.DutyDriverListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	autoLoad: true,
	model:"DutyDriverApp.model.DutyDriverModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/cardriverjson/queryDutyCarDriverList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });