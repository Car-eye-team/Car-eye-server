Ext.define("systemFunctionApp.model.SystemFunctionModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'menuid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'parentmenuid',  
                type : 'int'  ,
                useNull : true
            }, {  
                name : 'parentmenuname',  
                type : 'string'  
            }, {  
                name : 'menuname',  
                type : 'string'  
            }, {  
                name : 'menuaddr',  
                type : 'string'
            }, {  
	            name : 'iconcls',  
	            type : 'string' 
            }, {  
	            name : 'ifactivate',  
	            type : 'string' 
            }, {  
	            name : 'menusort',  
	            type : 'int'  ,
                useNull : true
            }, {  
	            name : 'createperson',  
	            type : 'int'  ,
                useNull : true
	        }, {  
                name : 'createpersonname',  
                type : 'string'  
	        }, {  
                name : 'medetype',  
                type : 'string'  
	        }, {  
                name : 'funcname',  
                type : 'string'  
	        }, {  
                name : 'menutype',  
                type : 'string'  
	        }, {  
                name : 'menulevel',  
                type : 'string'  
	        }, {  
                name : 'module',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }],  
    idProperty : 'menuid'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 