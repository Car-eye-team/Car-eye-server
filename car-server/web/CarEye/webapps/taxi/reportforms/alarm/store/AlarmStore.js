Ext.define("AlarmApp.store.AlarmStore",{
	extend:"Ext.data.Store",
	model:"AlarmApp.model.AlarmModel",
	autoLoad: { start: 0, limit: 30 },
	pageSize: 30,
	proxy: {
		type: 'ajax',
		timeout:90000,
		url: window.BIZCTX_PATH + '/reportformsjson/getAlarmList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list',
			totalProperty: 'result.totalCount'
		}
	}
 });