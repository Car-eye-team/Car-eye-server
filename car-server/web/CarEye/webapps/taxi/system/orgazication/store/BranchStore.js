Ext.define("OrgazicationDeptApp.store.BranchStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'value'],
    data : [
        {"type":"0", "value":"是"},
        {"type":"1", "value":"否"}
    ]
 });