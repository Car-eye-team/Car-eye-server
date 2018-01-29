Ext.define("SoftPhoneApp.store.SexStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'sex'],
    data : [
        {"id":"1", "sex":"男"},
        {"id":"2", "sex":"女"}
    ]
 });