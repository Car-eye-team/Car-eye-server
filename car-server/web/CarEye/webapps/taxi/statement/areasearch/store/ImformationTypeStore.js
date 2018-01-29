Ext.define("AreaSetApp.store.ImformationTypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'name'],
    data : [
        {"type":"1", "name":"圆形区域"},
        {"type":"2", "name":"矩形区域"}
//        ,
//        {"type":"3", "name":"多边形区域"}
    ]
 });