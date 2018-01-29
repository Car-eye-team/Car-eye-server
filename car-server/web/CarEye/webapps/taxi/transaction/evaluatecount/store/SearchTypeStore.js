Ext.define("EvaluateCountApp.store.SearchTypeStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'searchtype'],
    data : [
        {"id":"1", "searchtype":"按月统计"},
        {"id":"2", "searchtype":"按年统计 "}
    ]
 });