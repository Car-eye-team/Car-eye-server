Ext.define("QuestionApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"QuestionApp.model.CarInfoModel",
	pageSize: 15,
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