Ext.define("CommondLogApp.store.StatusStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'status'],
    data : [
        {"id":"0", "status":"成功/确认"},
        {"id":"1", "status":"失败"},
        {"id":"2", "status":"消息有误"},
        {"id":"3", "status":"不支持"}
    ]
 });