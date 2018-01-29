Ext.define("CarTrackApp.store.RateStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields: ['type', 'name'],
    data : [
        {"type":"100","name":"很快"},
        {"type":"300","name":"快"},
        {"type":"600","name":"比较快"},
        {"type":"1000","name":"较慢"},
        {"type":"1800","name":"慢"},
        {"type":"3000","name":"很慢"}
    ]
 });