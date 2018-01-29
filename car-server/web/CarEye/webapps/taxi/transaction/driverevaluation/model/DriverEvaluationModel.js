Ext.define("DriverEvaluationApp.model.DriverEvaluationModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'orderid',  
                type : 'string' 
            }, {  
                name : 'slevel',  
                type : 'string' 
            }, {  
                name : 'content',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'did',  
                type : 'string'  
            },{  
                name : 'dname',  
                type : 'string'
            },{  
                name : 'dphone',  
                type : 'string'
            }, {  
                name : 'cid',  
                type : 'string'
            },{  
                name : 'cname',  
                type : 'string'
            },{  
                name : 'cphone',  
                type : 'string'
            }, {  
                name : 'saddress',  
                type : 'string'
            }, {  
                name : 'eaddress',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 
 
 
 
 