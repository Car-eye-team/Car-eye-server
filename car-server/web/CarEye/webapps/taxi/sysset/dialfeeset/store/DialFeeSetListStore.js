Ext.define("DialFeeSetApp.store.DialFeeSetListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model:"DialFeeSetApp.model.DialFeeSetModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/dialfeesetjson/queryDialFeeSetList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });