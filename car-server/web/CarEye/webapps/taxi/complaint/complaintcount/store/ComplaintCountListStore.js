Ext.define("ComplaintCountApp.store.ComplaintCountListStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"ComplaintCountApp.model.ComplaintCountModel",
	pageSize: 31,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/complaintjson/getComplaintCountList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });