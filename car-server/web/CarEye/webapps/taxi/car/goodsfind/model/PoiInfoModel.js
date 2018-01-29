Ext.define("GoodsFindApp.model.PoiInfoModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'deptid',  
                type : 'string'  
            }, {  
                name : 'deptname',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'lng',  
                type : 'string'  
            }, {  
                name : 'lat',  
                type : 'string'  
            }, {  
                name : 'poiname',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'result',  
                type : 'int'  
            }, {  
                name : 'mile',  
                type : 'int'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });