Ext.define("PageSetApp.model.PageSetModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'int'  
            }, {  
                name : 'loginname',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'password',  
                type : 'string'  
            }, {  
                name : 'username',  
                type : 'string'
            }, {  
	            name : 'usersex',  
	            type : 'string'  
            }, {  
	            name : 'mobile',  
	            type : 'string' 
            }, {  
	            name : 'telephone',  
	            type : 'string' 
            }, {  
	            name : 'email',  
	            type : 'string' 
            }, {  
	            name : 'usergroupid',  
	            type : 'int'  ,
	            useNull : true
	        }, {  
                name : 'blocgroupname',  
                type : 'string'  
            }, {  
	            name : 'certificatenumber ',  
	            type : 'int'  
            }, {  
	            name : 'state',  
	            type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'leftpage',  
                type : 'int'  
            }, {  
                name : 'bottompage',  
                type : 'int'  
            }, {  
                name : 'userid',  
                type : 'int' ,
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }],  
    idProperty : 'userid'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 