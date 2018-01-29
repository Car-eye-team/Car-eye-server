Ext.define("TelBookApp.model.SendRecordModel",{
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
                name : 'blocname',  
                type : 'string'  
            },{  
                name : 'userid',  
                type : 'string'  
            },{  
                name : 'username',  
                type : 'string'  
            },{  
                name : 'telbookid',  
                type : 'string'
            },{  
                name : 'calltype',  
                type : 'string'
            },{  
                name : 'contacts',  
                type : 'string'
            },{  
                name : 'tel',  
                type : 'string'
            },{  
                name : 'remark',  
                type : 'string'
            },{  
                name : 'carnumber',  
                type : 'string'
            },{  
                name : 'opertype',  
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