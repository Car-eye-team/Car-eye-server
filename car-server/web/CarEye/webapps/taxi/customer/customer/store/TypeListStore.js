Ext.define("CustomerApp.store.TypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"CustomerApp.model.TypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/customer/customerjson/selCustomerTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });