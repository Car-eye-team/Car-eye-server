Ext.define("DispatcherRecordApp.store.DispatcherRecordListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	autoLoad: true,
	model:"DispatcherRecordApp.model.DispatcherRecordModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/dispatcherrecord/dispatcherrecordjson/queryDispatcherRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });