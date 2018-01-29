Ext.define("userApp.model.UserModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },
            {  
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
	            name : 'telphone',  
	            type : 'string' 
            }, {  
	            name : 'email',  
	            type : 'string' 
            }, {  
	            name : 'blocgroupid',  
	            type : 'int'
	        }, {  
                name : 'blocgroupname',  
                type : 'string'  
            }, {  
	            name : 'cardnumber',  
	            type : 'string'  
            }, {  
	            name : 'state',  
	            type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'complaintel',  
                type : 'string'  
            }, {  
                name : 'mgrname',  
                type : 'string'  
            }, {  
                name : 'mgrphone',  
                type : 'string'  
            }, {  
                name : 'repairname',  
                type : 'string'  
            }, {  
                name : 'repairphone',  
                type : 'string'  
            }, {  
                name : 'supporttel',  
                type : 'string'  
            }, {  
                name : 'servicetel',  
                type : 'string'  
            }, {  
                name : 'cardnumber',  
                type : 'string'  
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'runnumber',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
