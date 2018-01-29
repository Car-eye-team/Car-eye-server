Ext.define("OnCallCountApp.model.SendSuccessCountModel",{
	extend : 'Ext.data.Model',  
    fields : [{
    			name:'id',
    			type:'int',
    			useNull:true
    		},{  
    			name : 'blocname',  
    			type : 'string'
    		},{  
    			name : 'carnumber',  
    			type : 'string'
    		},{  
    			name : 'drivername',  
    			type : 'string'
            }, {  
                name : 'terminal',  
                type : 'string'  
            },{
            	name:'orderid',
            	type:'string'
            },{
            	name:'createtime',
            	type:'string'
            }]
 });
 
 
 
 
 
 
 
 
 