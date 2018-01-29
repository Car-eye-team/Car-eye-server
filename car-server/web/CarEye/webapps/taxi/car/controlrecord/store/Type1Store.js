Ext.define("CarInfoApp.store.Type1Store",{
	extend:"Ext.data.Store",
	fields: ['type', 'value'],
    data : [
        {"type":'0', "value":"恢复"},
        {"type":'1', "value":"断开"}
    ]
 });