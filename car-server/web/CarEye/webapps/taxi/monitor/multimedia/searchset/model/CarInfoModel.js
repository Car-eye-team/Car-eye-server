Ext.define("SearchSetApp.model.CarInfoModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'carid',  
                type : 'int',  
                useNull : true 
		    }, {  
		        name : 'blocid',  
		        type : 'string'  
		    }, {  
		        name : 'blocname',  
		        type : 'string'  
		    }, {  
		        name : 'terminal',  
		        type : 'string'  
		    }, {  
		        name : 'carnumber',  
		        type : 'string' 
            }],  
    idProperty : 'carid' 
 });