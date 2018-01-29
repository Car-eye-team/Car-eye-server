Ext.define("QuestionApp.model.AnswerModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'answerid',  
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
                name : 'qid',  
                type : 'string'  
            }, {  
                name : 'answer',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'
            }],  
    idProperty : 'answerid'// 极为重要的配置。关系到表格修改数据的获取 
 });