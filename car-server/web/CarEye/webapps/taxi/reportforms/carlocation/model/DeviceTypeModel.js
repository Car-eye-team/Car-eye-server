Ext.define("CarLocationApp.model.DeviceTypeModel",{
	extend : 'Ext.data.Model',
   	fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'typeid',  
                type : 'int'  
            }, {  
                name : 'typename',  
                type : 'string'  
            }]
 });