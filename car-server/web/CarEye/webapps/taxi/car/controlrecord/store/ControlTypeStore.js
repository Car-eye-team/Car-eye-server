Ext.define("CarInfoApp.store.ControlTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'controltype'],
    data : [
        {"id":"1", "controltype":"加锁"},
        {"id":"2", "controltype":"解锁"}
    ]
 });