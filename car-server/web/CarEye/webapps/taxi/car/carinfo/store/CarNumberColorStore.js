Ext.define("CarInfoApp.store.CarNumberColorStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'carnumbercolor'],
         //1 蓝色 2 黄色 3 黑色 4 白色 9 其他
    data : [
        {"id":"1", "carnumbercolor":"蓝色"},
        {"id":"2", "carnumbercolor":"黄色"},
        {"id":"3", "carnumbercolor":"黑色"},
        {"id":"4", "carnumbercolor":"白色 "},
        {"id":"5", "carnumbercolor":"红色 "},
        {"id":"6", "carnumbercolor":"紫色 "},
        {"id":"9", "carnumbercolor":"其他 "}
    ]
 });