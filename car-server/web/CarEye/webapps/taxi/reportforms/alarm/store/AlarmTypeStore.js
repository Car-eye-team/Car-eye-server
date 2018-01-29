Ext.define("AlarmApp.store.AlarmTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	autoLoad: { start: 0, limit: 30 },
	model:"AlarmApp.model.AlarmTypeModel",
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/reportformsjson/selectAlarmTypeList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });

