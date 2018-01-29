Ext.define("CarDriverApp.store.PoliticalStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'political'],
    data : [
        {"id":'1', "political":"党员"},
        {"id":'2', "political":"团员"},
        {"id":'3', "political":"群众"},
        {"id":'4', "political":"九三学社"},
        {"id":'99', "political":"其它"}
        
    ]
 });