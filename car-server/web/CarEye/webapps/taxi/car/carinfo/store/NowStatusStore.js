Ext.define("CarInfoApp.store.NowStatusStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'nowstatus'],
         //1正常2注销
    data : [
        {"id":"1", "nowstatus":"正常"},
        {"id":"2", "nowstatus":"注销"}
    ]
 });