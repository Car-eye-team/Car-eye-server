Ext.define("RegionSetApp.store.ClevelStore",{
	extend:"Ext.data.Store",
	fields: ['clevel', 'regionlevel'],
    data : [
        {"clevel":1, "regionlevel":"省级"},
        {"clevel":2, "regionlevel":"市级"},
        {"clevel":3, "regionlevel":"县级"}
    ]
 });