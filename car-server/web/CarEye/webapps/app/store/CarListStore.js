Ext.define("FMS.store.CarListStore",{
	extend:"Ext.data.Store",
	model:"FMS.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/carjson/selectCarList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });