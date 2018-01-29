Ext.define("OperaDayStatiApp.store.OperaDayStatiListStore",{
	extend:"Ext.data.Store",
	model:"OperaDayStatiApp.model.OperaDayStatiListModel",
	autoLoad: false,
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getOperaDayStatiList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });