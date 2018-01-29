Ext.define("CarrealtimeApp.store.GpsFlagStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":"0", "value":"未定位"},
        {"id":"1", "value":"已定位"},
    ]
 });