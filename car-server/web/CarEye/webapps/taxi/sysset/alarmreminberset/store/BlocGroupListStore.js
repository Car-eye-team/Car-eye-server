Ext.define("WindowSetApp.store.BlocGroupListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"WindowSetApp.model.BlocGroupModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/userGroupJson/queryUserGroupList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });