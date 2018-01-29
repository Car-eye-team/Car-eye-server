Ext.define("CarInfoApp.store.ElectagstatusStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'type'],
    data : [
        {"id":"已安装", "type":"已安装"},
        {"id":"未安装", "type":"未安装"}
    ]
 });