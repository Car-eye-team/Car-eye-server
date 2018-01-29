Ext.define("CarInfoApp.store.Type2Store",{
	extend:"Ext.data.Store",
	fields: ['type', 'value'],
    data : [
        {"type":'0', "value":"解锁"},
        {"type":'1', "value":"加锁"}
    ]
 });