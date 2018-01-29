Ext.define("SearchMapApp.store.PoiInfoRecordListStore",{
	extend:"Ext.data.Store",
	model:"SearchMapApp.model.PoiInfoModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/poiinfojson/queryPoiInfoRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });