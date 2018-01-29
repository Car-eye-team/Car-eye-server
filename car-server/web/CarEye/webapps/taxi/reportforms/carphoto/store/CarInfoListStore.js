Ext.define("CarPhotoApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 20 },
	pageSize: 20,
	model:"CarPhotoApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/textinfojson/queryCarInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });