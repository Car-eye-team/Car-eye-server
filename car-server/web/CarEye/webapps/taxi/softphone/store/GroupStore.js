Ext.define("SoftPhoneApp.store.GroupStore",{
	extend:"Ext.data.Store",
    fields: ['type', 'value'],
    data : [
        {"type":0, "value":"否"},
        {"type":1, "value":"是"}
    ]
 });//是否抢答 :0 未抢答 1 抢答 默认 0