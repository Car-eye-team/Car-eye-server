Ext.define("CustomerApp.store.CustomerListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	autoLoad: true,
	model:"CustomerApp.model.CustomerModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/customer/customerjson/queryCustomerList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });