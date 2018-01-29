Ext.define("QuestionApp.store.QuestionListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"QuestionApp.model.QuestionModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/questionjson/queryQuestionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });