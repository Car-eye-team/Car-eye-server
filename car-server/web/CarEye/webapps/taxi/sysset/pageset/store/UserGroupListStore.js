Ext.define("PageSetApp.store.UserGroupListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"PageSetApp.model.UserGroupModel",
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