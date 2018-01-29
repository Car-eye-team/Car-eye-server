Ext.define("VedioGuojiApp.store.FileTypeStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":1, "value":"图片"},
        {"id":2, "value":"录像"}
    ]
 });