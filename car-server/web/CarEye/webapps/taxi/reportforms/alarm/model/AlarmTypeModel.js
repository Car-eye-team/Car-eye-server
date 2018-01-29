Ext.define("AlarmApp.model.AlarmTypeModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'alarmkey',  
                type : 'string'  
            }, {  
                name : 'alarmvalue',  
                type : 'int'  
            }, {  
                name : 'alarmname',  
                type : 'string'  
            }]
 });