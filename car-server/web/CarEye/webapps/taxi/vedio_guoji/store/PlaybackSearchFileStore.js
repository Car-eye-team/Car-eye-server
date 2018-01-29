Ext.define("VedioGuojiApp.store.PlaybackSearchFileStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"VedioGuojiApp.model.PlaybackSearchFileModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/vediojson/playbackSearchFile.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });