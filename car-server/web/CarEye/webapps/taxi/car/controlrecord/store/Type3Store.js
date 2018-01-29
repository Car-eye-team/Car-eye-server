Ext.define("CarInfoApp.store.Type3Store",{
	extend:"Ext.data.Store",
	fields: ['type', 'value'],
    data : [
        {"type":'0', "value":"恢复"},
        {"type":'1', "value":"锁定"}
    ]
 });