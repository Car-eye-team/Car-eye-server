Ext.define("PhotoSetApp.model.CarInfoModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true 
		    }, {  
		        name : 'carid',  
		        type : 'int'  
		    }, {  
		        name : 'blocid',  
		        type : 'string'  
		    }, {  
		        name : 'driverid',  
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
		    }, {  
		        name : 'photo',  
		        type : 'string' 
		    }, {
		        name : 'name',  
		        type : 'string' 
		    }, {
		        name : 'drivername',  
		        type : 'string' 
		    }, {
		        name : 'servicenumber',  
		        type : 'string' 
		    }, {
		        name : 'validity',  
		        type : 'string' 
		    }, {  
		        name : 'picturepath',  
		        type : 'string' 
		    }, {  
		        name : 'zjstatus',  
		        type : 'string' 
		    }, {  
		        name : 'starlevel',  
		        type : 'string' 
		    }, {  
		        name : 'version',  
		        type : 'string' 
		    }, {  
		        name : 'validity',  
		        type : 'string' 
            }],  
    idProperty : 'id' 
 });