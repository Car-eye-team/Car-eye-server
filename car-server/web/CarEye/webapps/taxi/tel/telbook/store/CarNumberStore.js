Ext.define("TelBookApp.store.CarNumberStore",{
	extend:"Ext.data.Store",
	model:"TelBookApp.model.CarNumberModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/selectCarNumberList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });