Ext.define("CarInfoApp.store.CarStatesStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'carstatus'],
         //1在线2离线3 熄火4 行驶 5停车6报警
    data : [
        {"id":"7", "carstatus":"在线"},
        {"id":"1", "carstatus":"长期离线"},
        {"id":"2", "carstatus":"离线"},
        {"id":"3", "carstatus":"熄火"},
        {"id":"5", "carstatus":"行驶"},
        {"id":"4", "carstatus":"停车 "},
        {"id":"6", "carstatus":"报警 "},
        {"id":"8", "carstatus":"未定位 "}
        
    ]
 });