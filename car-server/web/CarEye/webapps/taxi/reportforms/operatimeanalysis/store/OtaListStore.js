Ext.define("OtaApp.store.OtaListStore",{
	extend:"Ext.data.Store",
	model:"OtaApp.model.OtaListModel",
	autoLoad: false,
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getOtaList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
//			totalProperty: 'result.totalCount'
		}
	}
 });