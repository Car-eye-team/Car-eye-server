Ext.define("OperaMileStatiApp.model.OperaMileStatiListModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'drivermile',  
                type : 'string'  
            }, {  
                name : 'passengermile',  
                type : 'string'  
            }, {  
                name : 'drivermile1',  
                type : 'string'  
            }, {  
                name : 'drivermilepercent',  
                type : 'string'  
            }, {  
                name : 'passengermile1',  
                type : 'string'  
            }, {  
                name : 'passengermilepercent',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }],  
    idProperty : 'createtime'// 极为重要的配置。关系到表格修改数据的获取 
 });