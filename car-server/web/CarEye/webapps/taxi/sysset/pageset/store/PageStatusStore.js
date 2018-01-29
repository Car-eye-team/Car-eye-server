Ext.define("PageSetApp.store.PageStatusTwoStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":1, "value":"打开"},
        {"id":2, "value":"关闭"}
    ]
 });