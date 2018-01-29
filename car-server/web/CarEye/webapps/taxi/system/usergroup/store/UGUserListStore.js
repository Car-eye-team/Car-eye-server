Ext.define("userGroupApp.store.UGUserListStore",{
	extend:"Ext.data.Store",
	//autoLoad: { start: 0, limit: 10 },
	model:"userGroupApp.model.UserModel",
	pageSize: 10,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/userGroupJson/queryUserList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });