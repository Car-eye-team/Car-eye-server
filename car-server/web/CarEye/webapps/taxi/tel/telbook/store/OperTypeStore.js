Ext.define("TelBookApp.store.OperTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'opertype'],
    data : [
        {"id":"2", "opertype":"追加"},
        {"id":"1", "opertype":"更新"},
        {"id":"3", "opertype":"修改"},
        {"id":"0", "opertype":"删除所有"}
        
    ]
 });