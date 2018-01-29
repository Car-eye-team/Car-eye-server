Ext.define("CarPhotoApp.store.TypeStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'name'],
    data : [
        {"type":"0", "name":"平台下发指令"},
        {"type":"1", "name":"定时动作"},
        {"type":"2", "name":"抢劫报警触发"},
        {"type":"3", "name":"碰撞侧翻报警触发"},
        {"type":"4", "name":"司机上班签到"},
        {"type":"5", "name":"司机下班签退"},
        {"type":"6", "name":"空车转重车"},
        {"type":"7", "name":"重车转空车"}
    ]
 });