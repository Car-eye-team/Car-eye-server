Ext.define("OrgazicationDeptApp.store.CompanyListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"OrgazicationDeptApp.model.CompanyModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/setjson/findAllCompanyList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });