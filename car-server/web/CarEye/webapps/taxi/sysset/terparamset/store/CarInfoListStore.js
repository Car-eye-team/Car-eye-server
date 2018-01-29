Ext.define("TerParamSetApp.store.CarInfoListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"TerParamSetApp.model.CarInfoModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/poiinfojson/queryDsobdParamSetList.action',
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });