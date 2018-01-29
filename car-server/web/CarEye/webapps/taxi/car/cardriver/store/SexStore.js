Ext.define("CarDriverApp.store.SexStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'sex'],
    data : [
        {"id":'1', "sex":"男"},
        {"id":'2', "sex":"女"}
        
    ]
 });