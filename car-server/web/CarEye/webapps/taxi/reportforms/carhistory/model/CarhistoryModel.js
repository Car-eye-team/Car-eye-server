Ext.define("CarhistoryApp.model.CarhistoryModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'int',
                useNull : true
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'an',  
                type : 'string'  
            }, {  
                name : 'anvalue',  
                type : 'int'  
            }, {  
                name : 'lng',  
                type : 'string'  
            }, {  
                name : 'lat',  
                type : 'string'  
            }, {  
                name : 'altitude',  
                type : 'string'  
            }, {  
                name : 'speed',  
                type : 'string'  
            }, {  
                name : 'direction',  
                type : 'string'  
            }, {  
                name : 'address',  
                type : 'string'  
            }, {  
                name : 'province',  
                type : 'string'  
            }, {  
                name : 'city',  
                type : 'string'  
            }, {  
                name : 'district',  
                type : 'string'  
            }, {  
                name : 'blng',  
                type : 'string'  
            }, {  
                name : 'blat',  
                type : 'string'  
            }, {  
                name : 'address',  
                type : 'string'  
            }, {  
                name : 'remark',  
                type : 'string'  
            }, {  
                name : 'carhistorytime',  
                type : 'string'  
            },{  
                name : 'sn',  
                type : 'string'  
            },{
                name : 'snvalue',  
                type : 'int' 
            },{  
                name : 'carstatus',  
                type : 'string' 
            },{  
                name : 'createtime',  
                type : 'string' 
            },{  
                name : 'gpstime',  
                type : 'string' 
            },{  
                name : 'gpsflag',  
                type : 'string' 
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });