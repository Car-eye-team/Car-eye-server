Ext.define("HostApp.store.OnStatusStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'type'],
    data : [
        {"id":1, "type":"上架"},
        {"id":2, "type":"下架"}
    ]
 });