Ext.define("TextInfoApp.store.OperTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['id', 'opertype'],
    data : [
        {"id":"1", "opertype":"下发"},
        {"id":"2", "opertype":"删除"}
        
    ]
 });