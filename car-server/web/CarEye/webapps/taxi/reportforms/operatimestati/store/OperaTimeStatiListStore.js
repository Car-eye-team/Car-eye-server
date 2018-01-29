Ext.define("OperaTimeStatiApp.store.OperaTimeStatiListStore",{
	extend:"Ext.data.Store",
	model:"OperaTimeStatiApp.model.OperaTimeStatiListModel",
	autoLoad: false,
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getOperaTimeStatiList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });