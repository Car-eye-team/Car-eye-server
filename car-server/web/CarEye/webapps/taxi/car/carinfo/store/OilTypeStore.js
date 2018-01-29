Ext.define("CarInfoApp.store.OilTypeStore",{
	extend:"Ext.data.Store",
	//autoLoad: true,
	fields: ['id', 'oiltype'],
	   //93#汽油、97#汽油、柴油
    data : [
        {"id":"1", "oiltype":"93#汽油"},
        {"id":"2", "oiltype":"97#汽油"},
        {"id":"3", "oiltype":"柴油"}
        
    ]
 });