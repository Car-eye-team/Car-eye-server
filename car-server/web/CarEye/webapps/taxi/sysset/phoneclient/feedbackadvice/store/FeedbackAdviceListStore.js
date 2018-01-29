Ext.define('FeedbackAdviceApp.store.FeedbackAdviceListStore', {
	extend : "Ext.data.Store",
	model : 'FeedbackAdviceApp.model.FeedbackAdviceModel',
	autoLoad : {
		start : 0,
		limit : 30
	},
	pageSize : 30,
	proxy : {
		type : 'ajax',
		 url : window.BIZCTX_PATH + '/feedbackadvicejson/feedbackadviceList.action', // 请求的服务器地址
		reader : {
			type : 'json',
			root : 'result.list',
			totalProperty : 'result.totalCount'
		}
	}
});
