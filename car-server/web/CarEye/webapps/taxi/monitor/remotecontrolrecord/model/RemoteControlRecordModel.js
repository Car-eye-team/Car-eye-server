Ext.define("RemoteControlRecordApp.model.RemoteControlRecordModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',
                userNull:true
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'status',  
                type : 'string'  
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'loginname',  
                type : 'string' 
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
            	name : 'remark',  
            	type : 'string'  
            }, {  
            	name : 'type',  
            	type : 'string'  
            }],
        idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取
 });
 
 
 
 
 
 
 
 
 