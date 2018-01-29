Ext.define("CarInfoApp.store.CarNumberTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'carnumbertype'],
         //黑牌、蓝牌、黄牌
    data : [
        {"id":"1", "carnumbertype":"黑牌"},
        {"id":"2", "carnumbertype":"蓝牌"},
        {"id":"3", "carnumbertype":"黄牌"}
        
    ]
 });