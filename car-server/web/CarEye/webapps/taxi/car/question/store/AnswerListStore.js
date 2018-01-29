Ext.define("QuestionApp.store.AnswerListStore",{
	extend:"Ext.data.Store",
	model:"QuestionApp.model.AnswerModel",
	autoLoad:false,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/questionjson/queryAnswerList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });

