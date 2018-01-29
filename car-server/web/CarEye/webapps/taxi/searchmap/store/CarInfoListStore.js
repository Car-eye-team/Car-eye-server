Ext.define("SearchMapApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 20 },
	model:"SearchMapApp.model.CarInfoModel",
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/textinfojson/queryCarInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });