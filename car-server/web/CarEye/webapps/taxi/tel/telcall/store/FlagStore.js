Ext.define("TelCallApp.store.FlagStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'flag'],
    data : [
        {"id":"0", "flag":"普通通话"},
        {"id":"1", "flag":"监听"}
        
    ]
 });