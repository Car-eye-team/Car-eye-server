Ext.define("TransactionApp.store.SourceStore",{
	extend:"Ext.data.Store",
    fields: ['id', 'source'],
    data : [
        {"id":"1", "source":"电召"},
        {"id":"2", "source":"网站"},
        {"id":"3", "source":"APP"},
        {"id":"5", "source":"微信"}
    ]
 });//业务来源:1 电召 2 96106网站 3 飞嘀 4 服务窗