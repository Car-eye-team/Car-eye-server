Ext.define("ComplaintCountApp.store.ComplaintCountDetailsStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"ComplaintCountApp.model.ComplaintCountDetailsModel",
	pageSize: 15,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/complaintjson/getComplaintCountDetails.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });