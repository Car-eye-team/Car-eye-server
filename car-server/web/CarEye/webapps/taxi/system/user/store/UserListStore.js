Ext.define("userApp.store.UserListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 15 },
	model:"userApp.model.UserModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/system/userjson/userList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });