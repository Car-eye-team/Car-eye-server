Ext.define("QuestionApp.model.SendRecordModel",{
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
            },  {  
                name : 'username',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'qid',  
                type : 'string'  
            }, {  
                name : 'question',  
                type : 'string'  
            }, {  
                name : 'answer',  
                type : 'string'  
            }, {  
                name : 'aid',  
                type : 'string'  
            }, {  
                name : 'emergency',  
                type : 'string'  
            }, {  
                name : 'lcd',  
                type : 'string'  
            }, {  
                name : 'tts',  
                type : 'string'  
            }, {  
                name : 'adv',  
                type : 'string'  
            },{  
                name : 'action',  
                type : 'string'
            },{  
                name : 'dist',  
                type : 'string'
            },{  
                name : 'content',  
                type : 'string'
            },{  
                name : 'carnumber',  
                type : 'string'
            },{  
                name : 'seq',  
                type : 'string'
            },{  
                name : 'result',  
                type : 'string'
            },{  
                name : 'data',  
                type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });