Ext.define("AreaSetApp.store.OperTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'opertype'],
    data : [
        {"id":"1", "opertype":"追加"},
        {"id":"2", "opertype":"更新"},
        {"id":"3", "opertype":"删除"}
        
    ]
 });