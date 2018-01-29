Ext.define("GoodsFindApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad:false,
	model:"GoodsFindApp.model.CarInfoModel",
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/poiinfojson/queryCarInfoListByMongo.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });