Ext.define("CarhistoryApp.store.CarStatusStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":"7", "value":"在线"},
        {"id":"1", "value":"长时间离线"},
        {"id":"2", "value":"离线"},
        {"id":"3", "value":"熄火"},
        {"id":"5", "value":"行驶"},
        {"id":"4", "value":"停车"},
        {"id":"6", "value":"报警"},
        {"id":"8", "value":"未定位"}
    ]
 });