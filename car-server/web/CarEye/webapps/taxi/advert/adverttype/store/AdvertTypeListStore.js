Ext.define('AdvertTypeApp.store.AdvertTypeListStore', {
	extend : "Ext.data.Store",
	model : 'AdvertTypeApp.model.AdvertTypeListModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/adverttypejson/getAdvertTypeList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
