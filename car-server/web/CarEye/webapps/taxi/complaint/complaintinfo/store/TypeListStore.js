Ext.define("ComplaintInfoApp.store.TypeListStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	model:"ComplaintInfoApp.model.TypeModel",
	proxy: {
		type: 'ajax',
		timeout:300000,
		url: window.BIZCTX_PATH + '/complaintjson/getAllComplaintTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });