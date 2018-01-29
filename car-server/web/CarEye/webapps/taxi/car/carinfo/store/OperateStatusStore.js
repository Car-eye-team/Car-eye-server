Ext.define("CarInfoApp.store.OperateStatusStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'operatestatus'],
         //1 蓝色 2 黄色 3 黑色 4 白色 9 其他
    data : [
        {"id":"1", "operatestatus":"正常营运"},
        {"id":"2", "operatestatus":"已登记未营运"},
        {"id":"3", "operatestatus":"已转非营运"},
        {"id":"4", "operatestatus":"停运 "}
    ]
 });