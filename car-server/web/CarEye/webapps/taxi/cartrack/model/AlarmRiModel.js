Ext.define("CarTrackApp.model.AlarmRiModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'deptid',  
                type : 'int',
                useNull : true
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'deptname',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'an',  
                type : 'string'  
            }, {  
                name : 'remark',  
                type : 'string'  
            }, {  
                name : 'content',  
                type : 'string'  
            },{  
                name : 'createtime',  
                type : 'string'  
            }
            
            ,{  
                name : 'lng',  
                type : 'string'  
            },{  
                name : 'lat',  
                type : 'string'  
            },{  
                name : 'blng',  
                type : 'string'  
            },{  
                name : 'blat',  
                type : 'string'  
            },{  
                name : 'glng',  
                type : 'string'  
            },{  
                name : 'glat',  
                type : 'string'  
            },{  
                name : 'gaddress',  
                type : 'string'  
            },{  
                name : 'address',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });