Ext.define("CarTrackApp.store.AlarmStore",{
	extend:"Ext.data.Store",
	autoLoad: false,
	model:"CarTrackApp.model.AlarmModel",
	pageSize: 30,
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/reportformsjson/getAlarmList.action?an=a1',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });