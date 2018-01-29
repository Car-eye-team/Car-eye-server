Ext.define("userApp.store.UserGroupEditStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"userApp.model.UserGroupModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/userGroupJson/userGroupList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });