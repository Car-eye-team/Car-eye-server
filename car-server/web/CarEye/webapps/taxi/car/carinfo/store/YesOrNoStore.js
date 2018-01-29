Ext.define("CarInfoApp.store.YesOrNoStore",{
	extend:"Ext.data.Store",
//	autoLoad: true,
	fields: ['id', 'type'],
         //1 蓝色 2 黄色 3 黑色 4 白色 9 其他
    data : [
        {"id":"是", "type":"是"},
        {"id":"否", "type":"否"}
    ]
 });