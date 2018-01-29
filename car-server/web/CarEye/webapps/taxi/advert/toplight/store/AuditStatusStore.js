Ext.define("TopLightApp.store.AuditStatusStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'type'],
    data : [
        {"id":"0", "type":"未审核"},
        {"id":"1", "type":"审核通过"},
        {"id":"2", "type":"审核不通过"}
    ]
 });