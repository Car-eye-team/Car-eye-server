Ext.define("CarInfoApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CarInfoApp.model.CarInfoModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/queryCarInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });