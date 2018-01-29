Ext.define("PaymentParamSetApp.store.CompanyListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"PaymentParamSetApp.model.CompanyModel",
	pageSize: 50,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/setjson/findCompanyList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });