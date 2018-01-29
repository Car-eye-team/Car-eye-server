Ext.define("CarTrackApp.store.CycleStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'name'],
    data : [
        {"type":"1","name":"查询最近三天史数据"},
        {"type":"2","name":"查询最近两天史数据"},
        {"type":"3","name":"查询最近一天史数据"}
    ]
 });