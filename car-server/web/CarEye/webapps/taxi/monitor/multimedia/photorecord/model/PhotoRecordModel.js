Ext.define("PhotoRecordApp.model.PhotoRecordModel",{
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
            },{  
                name : 'command',  
                type : 'string'  
            },{  
                name : 'pstime',  
                type : 'string'    
            },{  
                name : 'saveflag',  
                type : 'string' 
            },{  
                name : 'resolutionratio',  
                type : 'string' 
            }, {  
                name : 'picturequality',  
                type : 'string'  
            },{  
                name : 'lighteness',  
                type : 'string'  
            },{  
                name : 'contrast',  
                type : 'string'  
            },{  
                name : 'saturation',  
                type : 'string'  
            },{  
                name : 'chroma',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            },{
                name : 'createtime',  
                type : 'string'
            },{
            	name:'seq',//流水号
            	type:'string'
            },{  
                name : 'result',  
                type : 'string'  
            },{  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });