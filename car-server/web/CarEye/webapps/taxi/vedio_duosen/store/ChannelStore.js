Ext.define("VedioDuosenApp.store.ChannelStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":'1', "value":"设备通道一"},
        {"id":'2', "value":"设备通道二"}
    ]
 });