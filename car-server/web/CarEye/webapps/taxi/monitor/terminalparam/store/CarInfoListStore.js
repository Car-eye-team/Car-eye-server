Ext.define("CarParamApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"CarParamApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/textinfojson/queryCarInfoList.action',
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });