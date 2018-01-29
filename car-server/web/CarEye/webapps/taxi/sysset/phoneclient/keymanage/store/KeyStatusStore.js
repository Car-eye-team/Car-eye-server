Ext.define("KeyManageApp.store.KeyStatusStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'status'],
    data : [
        {"id":"1", "status":"启用"},
        {"id":"0", "status":"停用"}
    ]
	
 });