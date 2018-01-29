Ext.define("RegionSetApp.model.SzcodeModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'szcode',  
                type : 'string'  
            }, {  
                name : 'cnname',  
                type : 'string'  
            }, {  
                name : 'clevel',  
                type : 'int'  
            }, { 
                name : 'parentcode',  
                type : 'string'  
            }, {  
                name : 'parentname',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string' 
            }, {  
                name : 'areaid',  
                type : 'int',
                useNull:true
            }, {  
                name : 'parentid',  
                type : 'int',
                useNull:true
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });