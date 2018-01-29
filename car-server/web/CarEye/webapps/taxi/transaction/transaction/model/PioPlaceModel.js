Ext.define("TransactionApp.model.PioPlaceModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'uid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
	            name : 'name',  
	            type : 'String'
            }, {  
	            name : 'address',  
	            type : 'String'
            }, {  
	            name : 'street_id',  
	            type : 'String'
            }, {  
	            name : 'telephone',  
	            type : 'String'
            },  {  
	            name : 'lnglat',  
	            type : 'String'
            }],  
    idProperty : 'uid'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 