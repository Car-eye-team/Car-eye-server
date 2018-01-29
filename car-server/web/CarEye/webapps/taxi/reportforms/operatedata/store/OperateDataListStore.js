Ext.define("OperateDataApp.store.OperateDataListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 50 },
	model:"OperateDataApp.model.OperateDataModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/operatedatajson/getOperateDataList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });