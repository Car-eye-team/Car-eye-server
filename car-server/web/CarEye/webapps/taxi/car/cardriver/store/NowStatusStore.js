Ext.define("CarDriverApp.store.NowStatusStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'nowstatus'],
    data : [
        {"id":"1", "nowstatus":"正常"},
        {"id":"2", "nowstatus":"注销"}
        
    ]
 });