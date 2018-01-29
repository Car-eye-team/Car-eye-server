Ext.define("VideoParamSetApp.store.ProtocolStore",{
	extend:"Ext.data.Store",
	fields: ['id', 'value'],
    data : [
        {"id":"rtsp://", "value":"rtsp"}
    ]
 });