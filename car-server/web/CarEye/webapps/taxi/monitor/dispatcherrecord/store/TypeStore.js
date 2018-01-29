Ext.define("DispatcherRecordApp.store.TypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'type'],
    data : [
        {"id":"1", "type":"终端TTS语音播报"},
        {"id":"2", "type":"终端显示不播报"}
        
    ]
 });