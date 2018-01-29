Ext.define("TextInfoApp.store.TextInfoTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"TextInfoApp.model.TextInfoTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/textinfojson/getTextInfoTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });