Ext.define("ComplaintCountApp.model.ComplaintCountModel",{
	extend : 'Ext.data.Model',  
     fields : [{  
                name : 'blocid',  
                type : 'string'  
            },{  
                name : 'blocname',  
                type : 'string'  
            }, {
            	name : 'carnumber',  
                type : 'string'  
            }, {
            	name : 'count',  
                type : 'string'  
            }, {
	            name : 'datetime',  
	            type : 'string' 
            }],  
    idProperty : 'datetime'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 