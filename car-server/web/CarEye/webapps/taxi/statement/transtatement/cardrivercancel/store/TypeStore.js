Ext.define("CarDriverCancelApp.store.TypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'type'],
    data : [
         {"id":'2', "type":"全部"},
         {"id":'1', "type":"违约"},
         {"id":'0', "type":"不违约"}
    ]
 });