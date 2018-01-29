Ext.define("RemoteControlRecordApp.store.RemoteControlRecordListStore",{
	extend:"Ext.data.Store",
	autoLoad: { start: 0, limit: 30 },
	model:"RemoteControlRecordApp.model.RemoteControlRecordModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/remotecontrolrecord/remotecontrolrecordjson/findPageRemoteControlRecord.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });