Ext.define("DialFeeSetApp.model.DialFeeSetModel",{
	extend : 'Ext.data.Model',  
     fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },{  
                name : 'dialfee',  
                type : 'string'  
            },{  
                name : 'effecttime',  
                type : 'string'  
            },{  
                name : 'username',  
                type : 'string'  
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
	            name : 'createtime',  
	            type : 'string' 
            }, {  
	            name : 'username',  
	            type : 'string' 
            }, {  
	            name : 'oilcost',  
	            type : 'string' 
            }, {  
	            name : 'discount',  
	            type : 'string' 
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 

 
 