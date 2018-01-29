Ext.define("TelCallApp.store.CallTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'calltype'],
    data : [
        {"id":"1", "calltype":"呼入"},
        {"id":"2", "calltype":"呼出"},
        {"id":"3", "calltype":"呼入/呼出"}
        
    ]
 });