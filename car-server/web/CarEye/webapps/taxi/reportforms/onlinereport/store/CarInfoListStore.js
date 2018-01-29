Ext.define("OnlineReportApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"OnlineReportApp.model.CarInfoModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/onlinereportjson/findCarInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });