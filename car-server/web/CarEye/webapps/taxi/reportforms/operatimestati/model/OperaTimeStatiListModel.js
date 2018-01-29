Ext.define("OperaTimeStatiApp.model.OperaTimeStatiListModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'inlinetime',  
                type : 'string'  
            }, {  
                name : 'passengertime',  
                type : 'string'  
            }, {  
                name : 'drivertime1',  
                type : 'string'  
            }, {  
                name : 'inlinetimepercent',  
                type : 'string'  
            }, {  
                name : 'passengertime1',  
                type : 'string'  
            }, {  
                name : 'passengertimepercent',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }],  
    idProperty : 'createtime'// 极为重要的配置。关系到表格修改数据的获取 
 });