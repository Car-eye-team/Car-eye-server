Ext.define("VoiceOrderApp.store.TraTypeStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'tratype'],
    data : [
        {"id":'0', "tratype":"即时召车"},
        {"id":'1', "tratype":"预约召车"},
        {"id":'2', "tratype":"车辆指派"}
    ]
 });//业务类型 :0,即派订单 1，预约订单