Ext.define("CustomerApp.model.CustomerModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int'  
            },{  
                name : 'typeid',  
                type : 'int'  
            },{  
                name : 'cname',  
                type : 'string'  
            },{  
                name : 'sex',  
                type : 'string'  
            },{  
                name : 'tel',  
                type : 'string'  
            },{  
                name : 'phone',  
                type : 'string'  
            },{  
                name : 'postaddr',  
                type : 'string'  
            },{  
                name : 'postalcode',  
                type : 'string'  
            },{  
                name : 'flag',  
                type : 'int'  
            },{  
                name : 'source',  
                type : 'int'  
            },{  
                name : 'userid',  
                type : 'int'  
            }, {  
                name : 'username',  
                type : 'string'  
            },{  
                name : 'remark',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            },{  
                name : 'ids',  
                type : 'string'
            },{  
                name : 'typename',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
