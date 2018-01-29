Ext.define("SoftPhoneApp.store.TraTypeStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'tratype'],
    data : [
        {"id":0, "tratype":"即派订单"},
        {"id":1, "tratype":"预约订单"}
    ]
 });//业务类型 :0,即派订单 1，预约订单