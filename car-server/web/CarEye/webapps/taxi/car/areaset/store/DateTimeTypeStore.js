Ext.define("AreaSetApp.store.DateTimeTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'name'],
    data : [
        {"type":"1", "name":"每年"},
        {"type":"2", "name":"每月"},
        {"type":"3", "name":"每日"},
        {"type":"4", "name":"每小时"},
        {"type":"5", "name":"每分钟"}
    ]
 });