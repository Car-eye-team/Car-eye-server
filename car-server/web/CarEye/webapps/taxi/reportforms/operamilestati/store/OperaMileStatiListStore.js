Ext.define("OperaMileStatiApp.store.OperaMileStatiListStore",{
	extend:"Ext.data.Store",
	model:"OperaMileStatiApp.model.OperaMileStatiListModel",
	autoLoad: false,
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getOperaMileStatiList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });