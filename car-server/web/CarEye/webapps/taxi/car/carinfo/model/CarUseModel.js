Ext.define("CarInfoApp.model.CarUseModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'string'  
            },{  
                name : 'usename',  
                type : 'string' 
            },{  
	            name : 'createtime',  
	            type : 'string' 
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 