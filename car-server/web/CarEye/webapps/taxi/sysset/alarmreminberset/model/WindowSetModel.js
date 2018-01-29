Ext.define("WindowSetApp.model.WindowSetModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'userid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'int'  ,
                useNull : true
            }, {  
                name : 'loginname',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            },  {  
	            name : 'blocgroupid',  
	            type : 'int'  ,
	            useNull : true
	        }, {  
                name : 'blocgroupname',  
                type : 'string'  
            }, {  
	            name : 'certificatenumber ',  
	            type : 'int'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'type',  
                type : 'int'  
            }],  
    idProperty : 'userid'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 