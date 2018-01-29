Ext.define("VoiceOrderApp.store.CarpoolStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'carpool'],
    data : [
        {"id":"0", "carpool":"不合乘"},
        {"id":"1", "carpool":"合乘"}
    ]
 });//是否合乘 :0 不合乘 1 合乘