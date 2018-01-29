Ext.define("userGroupApp.store.BlocInfoStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"userGroupApp.model.UserModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/orgazicationdeptjson/selectorgazicationDeptList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });