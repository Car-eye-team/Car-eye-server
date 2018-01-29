Ext.define("VoiceOrderApp.store.StatusStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'status'],
    data : [
        {"id":"0", "status":"无应答"},
        {"id":"1", "status":"调派中"},
        {"id":"2", "status":"已调派"},
        {"id":"3", "status":"取消"},
        {"id":"4", "status":"超时"},
        {"id":"5", "status":"载客中"},
        {"id":"6", "status":"待支付"},
        {"id":"7", "status":"待评价"},
        {"id":"8", "status":"完成"}
    ]
 });