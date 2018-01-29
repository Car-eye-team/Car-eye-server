Ext.define("RemoteControlRecordApp.store.CommandTypeStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'commandtype'],
    data : [
        {"id":"1", "commandtype":"车辆控制"},
        {"id":"2", "commandtype":"终端控制"}
        
    ]
 });