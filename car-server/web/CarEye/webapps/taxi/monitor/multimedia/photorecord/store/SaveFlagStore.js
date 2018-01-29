Ext.define("PhotoRecordApp.store.SaveFlagStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'saveflag'],
    data : [
        {"id":"0", "saveflag":"实时上传"},
        {"id":"1", "saveflag":"保存"}
    ]
 });