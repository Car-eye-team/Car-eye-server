Ext.define("CarInfoApp.store.CarColorStore",{
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
        {"id":"7", "carnumbercolor":"绿黄 "},
        {"id":"8", "carnumbercolor":"青绿加亮银 "},
        {"id":"9", "carnumbercolor":"蓝黄 "},
        {"id":"10", "carnumbercolor":"艳绿加银灰 "},
        {"id":"11", "carnumbercolor":"绿色 "},
        {"id":"12", "carnumbercolor":"晶蓝加亮银 "},
        {"id":"13", "carnumbercolor":"灰色 "},
        {"id":"14", "carnumbercolor":"墨绿色 "},
        {"id":"15", "carnumbercolor":"深蓝色 "},
        {"id":"16", "carnumbercolor":"棕色 "},
        {"id":"99", "carnumbercolor":"其他 "}
    ]
 });