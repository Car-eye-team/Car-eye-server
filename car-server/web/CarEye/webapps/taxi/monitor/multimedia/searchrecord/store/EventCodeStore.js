Ext.define("SearchRecordApp.store.EventCodeStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'eventcode'],
    data : [
        {"id":"0", "eventcode":"平台下发指令"},
        {"id":"1", "eventcode":"定时动作"},
        {"id":"2", "eventcode":"抢劫报警触发"},
        {"id":"3", "eventcode":"碰撞侧翻报警触发"},
        {"id":"4", "eventcode":"司机上班签到"},
        {"id":"5", "eventcode":"司机下班签退"},
        {"id":"6", "eventcode":"空车转重车"},
        {"id":"7", "eventcode":"重车转空车"}
    ]
 });