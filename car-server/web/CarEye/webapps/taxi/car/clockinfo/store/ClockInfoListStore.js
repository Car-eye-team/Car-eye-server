Ext.define("ClockInfoApp.store.ClockInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 50 },
	model:"ClockInfoApp.model.ClockInfoModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/clockjson/getClockInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });