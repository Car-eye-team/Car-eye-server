Ext.define("AppManageApp.store.AppVersionStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"AppManageApp.model.AppTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/appmanagejson/selAppVersionList.action', // 请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });