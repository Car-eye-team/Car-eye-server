Ext.define("CarTrackApp.store.AlarmRiStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"CarTrackApp.model.AlarmRiModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/reportformsjson/getAlarmRemindInfoList.action?an=a1',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });