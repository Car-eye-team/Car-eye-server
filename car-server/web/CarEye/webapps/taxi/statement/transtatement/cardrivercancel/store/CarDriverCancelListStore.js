Ext.define("CarDriverCancelApp.store.CarDriverCancelListStore",{
	extend:"Ext.data.Store",
	model:"CarDriverCancelApp.model.CarDriverCancelModel",
	autoLoad: true,
	pageSize: 30,
	autoLoad: { start: 0, limit: 30 },
	proxy: {
		type: 'ajax',
		//timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/transtatementjson/selectCarDriverCancelList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });