Ext.define("AreaSetApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"AreaSetApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/statement/areamorejson/queryCarPositionInfoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });