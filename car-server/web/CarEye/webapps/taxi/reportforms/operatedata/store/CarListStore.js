Ext.define("OperateDataApp.store.CarListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"OperateDataApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/carjson/selectCarList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });