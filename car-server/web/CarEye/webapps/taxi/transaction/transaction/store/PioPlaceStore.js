Ext.define("TransactionApp.store.PioPlaceStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"TransactionApp.model.PioPlaceModel",
	proxy: {
		//type: 'ajax',
		//timeout:180000,
		//url: window.BIZCTX_PATH + '/poiinfojson/getResultByCity.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });