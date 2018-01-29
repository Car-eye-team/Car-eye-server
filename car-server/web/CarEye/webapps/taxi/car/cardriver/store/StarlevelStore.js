Ext.define("CarDriverApp.store.StarlevelStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'starlevel'],
         //
    data : [
    	{"id":"0", "starlevel":"未评定"},
        {"id":"1", "starlevel":"一星"},
        {"id":"2", "starlevel":"二星"},
        {"id":"3", "starlevel":"三星"},
        {"id":"4", "starlevel":"四星"},
        {"id":"5", "starlevel":"五星"}
    ]
 });