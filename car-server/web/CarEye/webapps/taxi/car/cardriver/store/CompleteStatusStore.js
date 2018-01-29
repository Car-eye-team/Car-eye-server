Ext.define("CarDriverApp.store.CompleteStatusStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'completestatus'],
         //1已结业2未结业3未参与
    data : [
        {"id":"1", "completestatus":"已结业"},
        {"id":"2", "completestatus":"未结业"},
        {"id":"3", "completestatus":"未参与"}
    ]
 });