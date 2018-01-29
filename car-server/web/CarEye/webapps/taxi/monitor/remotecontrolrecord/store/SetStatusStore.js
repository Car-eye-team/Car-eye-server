Ext.define("RemoteControlRecordApp.store.SetStatusStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'setstatus'],
    data : [
        {"id":"1", "setstatus":"成功"},
        {"id":"2", "setstatus":"失败"}
        
    ]
 });