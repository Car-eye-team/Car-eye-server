Ext.define("OrgazicationDeptApp.model.OrgazicationDeptModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
    		}, {  
                name : 'clobname',  
                type : 'string'  
            }
            , {  
                name : 'parentid',  
                type : 'string'  
            }, {  
                name : 'contract',  
                type : 'int'  
            }, {  
                name : 'tel',  
                type : 'string'  
            }, {  
                name : 'address',  
                type : 'string'  
            }, {  
                name : 'userid',  
                type : 'string'  
            },{  
                name : 'remark',  
                type : 'string'  
            },{  
                name : 'createtime',  
                type : 'string'  
            },{  
                name : 'blocid',  
                type : 'int'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'ec_nature',  
                type : 'int'  
            }],
        idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 