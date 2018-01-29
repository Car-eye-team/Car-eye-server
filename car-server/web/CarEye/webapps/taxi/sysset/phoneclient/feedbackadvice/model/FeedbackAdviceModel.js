Ext.define("FeedbackAdviceApp.model.FeedbackAdviceModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },{  
                name : 'createtime',  
                type : 'string'
            },{  
                name : 'version',  
                type : 'string'
            },{  
                name : 'typeid',  
                type : 'string'
            },{  
                name : 'feedbackcontent',  
                type : 'string'
            },{  
                name : 'userid',  
                type : 'string'
            },{  
                name : 'dealcontent',  
                type : 'string'
            },
            {  
                name : 'status',  
                type : 'string'
            },{  
                name : 'typename',  
                type : 'string'
            },{  
                name : 'versionname',  
                type : 'string'
            },{  
                name : 'creater',  
                type : 'string' 
            }
              ],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 