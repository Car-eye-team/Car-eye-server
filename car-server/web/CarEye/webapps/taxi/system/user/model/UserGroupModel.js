Ext.define("userApp.model.UserGroupModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'blocgroupid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocgroupname',  
                type : 'string'  
            }, {  
                name : 'usergroupdesc',  
                type : 'string'  
            }, {  
                name : 'username',  
                type : 'string'  
            },{  
                name : 'userid',  
                type : 'int',
                useNull : true
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'blocid',  
                type : 'int'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }],  
    idProperty : 'usergroupid'// 极为重要的配置。关系到表格修改数据的获取 
 });