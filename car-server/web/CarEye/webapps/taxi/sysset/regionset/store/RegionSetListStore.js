Ext.define("RegionSetApp.store.RegionSetListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model : 'RegionSetApp.model.RegionSetListModel',
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/regionjson/findPageRegionList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });