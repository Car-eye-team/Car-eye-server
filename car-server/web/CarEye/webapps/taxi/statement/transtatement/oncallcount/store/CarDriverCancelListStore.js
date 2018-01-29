Ext.define("OnCallCountApp.store.CarDriverCancelListStore",{
	extend:"Ext.data.Store",
	model:"OnCallCountApp.model.CarDriverCancelModel",
	autoLoad: true,
	pageSize: 18,
	autoLoad: { start: 0, limit: 18 },
	proxy: {
		type: 'ajax',
		//timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/transtatementjson/selectCarDriverCancelList.action?wy=',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });