Ext.define("AreaSetApp.store.CarPageListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 10 },
	model:"AreaSetApp.model.CarInfoModel",
	pageSize: 10,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/terminalpositionjson/selectCarPageList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });