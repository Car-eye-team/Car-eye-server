Ext.define("OnlineRateApp.store.TwoCarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"OnlineRateApp.model.CarInfoModel",
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/onlineratejson/findTwoCarInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });