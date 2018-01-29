Ext.define("CarLocationApp.store.DistrictStore",{
	extend:"Ext.data.Store",
	model:"CarLocationApp.model.CityInfoModel",
//	autoLoad: true,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/commonjson/getDistrictList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });