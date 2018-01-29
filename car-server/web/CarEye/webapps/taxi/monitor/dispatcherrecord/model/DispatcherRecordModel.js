Ext.define("DispatcherRecordApp.model.DispatcherRecordModel",{
	extend : 'Ext.data.Model',  
    fields : [
    	{ name : 'id',type : 'int',useNull : true  },//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错
        { name : 'carnumber',type : 'string'}, 
        { name : 'status',type : 'int'},
        { name : 'userid',type : 'int'}, 
        { name : 'createtime',  type : 'string'},
        { name : 'remark',  type : 'string'},
        { name : 'blocname',  type : 'string'},
        { name : 'blocid',  type : 'int'},
        { name : 'terminal',  type : 'string'},
        { name : 'username',  type : 'string'},
        { name : 'stime',  type : 'string'},
        { name : 'etime',  type : 'string'},
        { name : 'emergency',  type : 'int'},
        { name : 'lcd',  type : 'int'},
        { name : 'tts',  type : 'int'},
        { name : 'adv',  type : 'int'},
        { name : 'action',  type : 'int'}
           ],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
