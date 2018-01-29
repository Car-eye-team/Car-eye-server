Ext.define("systemFunctionApp.store.MenuLevelStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['menulevel', 'name'],
    data : [
        {"menulevel":"1", "name":"一级"},
        {"menulevel":"2", "name":"二级"},
        {"menulevel":"3", "name":"三级"},
        {"menulevel":"4", "name":"四级"}
    ]
 });