Ext.define("HostApp.model.AdvertConModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },{  
                name : 'positionid',  
                type : 'string' 
            },{  
                name : 'version',  
                type : 'string' 
            },{  
                name : 'dtime',  
                type : 'string' 
            },{  
                name : 'userid',  
                type : 'string' 
            },{  
                name : 'username',  
                type : 'string' 
            },{  
                name : 'reltime',  
                type : 'string' 
            },{  
                name : 'auditstatus',  
                type : 'string' 
            },{  
                name : 'adminid',  
                type : 'string' 
            },{  
                name : 'auditname',  
                type : 'string' 
            },{  
                name : 'audittime',  
                type : 'string' 
            },{  
                name : 'auditremark',  
                type : 'string' 
            },{  
                name : 'onstatus',  
                type : 'string' 
            },{  
                name : 'remark',  
                type : 'string' 
            },{  
                name : 'createtime',  
                type : 'string' 
            },{  
                name : 'position',  
                type : 'string' 
            } 
            ,{  
                name : 'typeid',  
                type : 'string' 
            },{  
                name : 'adname',  
                type : 'string' 
            }
            ,{  
                name : 'linkpath',  
                type : 'string' 
            },{  
                name : 'path',  
                type : 'string' 
            },{  
                name : 'typename',  
                type : 'string' 
            },{  
                name : 'versionid',  
                type : 'int' 
            },{  
                name : 'connumber',  
                type : 'string' 
            }
              ],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 











