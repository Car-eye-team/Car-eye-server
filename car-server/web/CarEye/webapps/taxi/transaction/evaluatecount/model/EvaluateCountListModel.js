Ext.define("EvaluateCountApp.model.EvaluateCountListModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'string'  
            }, {  
                name : 'carid',  
                type : 'string'  
            }, {  
                name : 'datetime',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'count1',  
                type : 'string'  
            }, {  
                name : 'count2',  
                type : 'string'  
            }, {  
                name : 'count3',  
                type : 'string'  
            }, {  
                name : 'count4',  
                type : 'string'  
            }],  
    idProperty : 'datetime'// 极为重要的配置。关系到表格修改数据的获取 
 });