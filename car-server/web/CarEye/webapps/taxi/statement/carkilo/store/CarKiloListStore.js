Ext.define("CarKiloApp.store.CarKiloListStore",{
	extend:"Ext.data.Store",
	model:"CarKiloApp.model.CarKiloModel",
	proxy: {
		type: 'ajax',
		timeout: 600000,
		url: window.BIZCTX_PATH + '/statement/statementjson/selectCheckCarKilo.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });