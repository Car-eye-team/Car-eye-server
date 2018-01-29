Ext.define("CarConditionApp.store.CarConditionStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"CarConditionApp.model.CarConditionModel",
	pageSize: 31,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carconditionjson/getCarConditionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });