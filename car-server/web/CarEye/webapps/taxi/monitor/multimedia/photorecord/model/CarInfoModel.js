Ext.define("PhotoRecordApp.model.CarInfoModel",{
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
    idProperty : 'carid'// 极为重要的配置。关系到表格修改数据的获取 
 });