Ext.define("ComplaintInfoApp.store.DealStatusStore",{
	extend:"Ext.data.Store",
	fields: ['key', 'value'],  
    data : [
        {"key":"1", "value":"未处理"},
        {"key":"2", "value":"已处理"}
        
    ]
 });