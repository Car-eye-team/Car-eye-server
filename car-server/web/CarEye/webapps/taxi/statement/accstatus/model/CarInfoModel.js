Ext.define("AccStatusApp.model.CarInfoModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'carid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'msgid',  
                type : 'int'  ,
                useNull : true
            }, {  
	            name : 'lng',  
	            type : 'String'
            }, {  
	            name : 'lat',  
	            type : 'String'
            }, {  
	            name : 'glng',  
	            type : 'String'
            }, {  
	            name : 'glat',  
	            type : 'String'
            }, {  
	            name : 'altitude',  
	            type : 'String'
            }, {  
	            name : 'terminal',  
	            type : 'String'
            }, {  
	            name : 'carnumber',  
	            type : 'String'
            }, {  
	            name : 'carstatus',  
	            type : 'String'
            }, {  
	            name : 'travelposition',  
	            type : 'String'
            }, {  
	            name : 'speed',  
	            type : 'String'
            }, {  
	            name : 'direction',  
	            type : 'String'
            }, {  
	            name : 'gpstime',  
	            type : 'String'
            }, {  
	            name : 'gpsflagtext',  
	            type : 'String'
            }, {  
	            name : 'address',  
	            type : 'String'
            }, {  
	            name : 'gaddress',  
	            type : 'String'
            }, {  
	            name : 'mileage',  
	            type : 'String'
            }, {  
	            name : 'remark',  
	            type : 'String'
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'acc',  
                type : 'string'  
            }, {  
                name : 'phone',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }]
 });
