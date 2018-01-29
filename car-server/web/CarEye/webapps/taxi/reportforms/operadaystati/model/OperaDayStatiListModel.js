Ext.define("OperaDayStatiApp.model.OperaDayStatiListModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'drivermile',  
                type : 'string'  
            }, {  
                name : 'income',  
                type : 'string'  
            }, {  
                name : 'passengertime',  
                type : 'string'  
            }, {  
                name : 'passengermile',  
                type : 'string'  
            }, {  
                name : 'inlinetime',  
                type : 'string'  
            }, {  
                name : 'passengercount',  
                type : 'string'  
            }, {  
                name : 'waittime',  
                type : 'string'  
            }, {  
                name : 'feetime',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }],  
    idProperty : 'createtime'// 极为重要的配置。关系到表格修改数据的获取 
 });