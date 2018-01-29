Ext.define("CarInfoApp.store.ControlRecordListStore",{
	extend:"Ext.data.Store",
	//autoLoad: { start: 0, limit: 15 },
	model:"CarInfoApp.model.ControlRecordModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/queryCarControlRecordList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });