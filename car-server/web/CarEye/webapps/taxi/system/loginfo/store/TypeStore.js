Ext.define("LogInfoApp.store.TypeStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'operattype'],
    data : [
        {"id":"1", "operattype":"添加操作"},
        {"id":"2", "operattype":"修改操作"},
        {"id":"3", "operattype":"删除操作"},
        {"id":"4", "operattype":"导出操作"},
        {"id":"5", "operattype":"查询操作"}
    ]
	
 });