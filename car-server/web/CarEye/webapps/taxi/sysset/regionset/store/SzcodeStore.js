Ext.define("RegionSetApp.store.SzcodeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model : 'RegionSetApp.model.SzcodeModel',
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/regionjson/findParentRegionListByClevel.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });