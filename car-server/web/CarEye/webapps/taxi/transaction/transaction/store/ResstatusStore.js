Ext.define("TransactionApp.store.ResstatusStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'resstatus'],
    data : [
        {"id":"0", "resstatus":"未抢答"},
        {"id":"1", "resstatus":"抢答"}
    ]
 });//是否抢答 :0 未抢答 1 抢答 默认 0