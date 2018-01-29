Ext.define("TerminalFunCountApp.store.TerminalFunCountListStore",{
	extend:"Ext.data.Store",
	model:"TerminalFunCountApp.model.TerminalFunCountModel",
	autoLoad: true,
	pageSize: 30,
	autoLoad: { start: 0, limit: 30 },
	proxy: {
		type: 'ajax',
		//timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/transtatementjson/selectTerminalFunCountList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });