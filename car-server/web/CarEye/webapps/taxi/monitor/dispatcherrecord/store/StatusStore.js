Ext.define("DispatcherRecordApp.store.StatusStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'status'],
    data : [
        {"id":"1", "status":"成功"},
        {"id":"2", "status":"失败"}
    ]
 });