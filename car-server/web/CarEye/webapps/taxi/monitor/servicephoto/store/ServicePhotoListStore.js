Ext.define("PhotoSetApp.store.ServicePhotoListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 20 },
	model:"PhotoSetApp.model.CarInfoModel",
	proxy: {
		type: 'ajax',
		timeout:180000,
		url: window.BIZCTX_PATH + '/cardriverjson/queryServicePhotoList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });