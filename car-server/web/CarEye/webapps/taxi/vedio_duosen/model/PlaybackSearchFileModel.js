Ext.define("VedioDuosenApp.model.PlaybackSearchFileModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
    			name : 'eid',  
	            type : 'int',
	            useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, { 
	            name : 'id',  
	            type : 'int',
	            useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
	            name : 'filename',  
	            type : 'String'
            }, {
            	name : 'videotime',  
	            type : 'String'
            }, { 
	            name : 'splaysec',  
	            type : 'String'
            }, {  
	            name : 'eplaysec',  
	            type : 'String'
            }],
     idProperty : 'eid'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 