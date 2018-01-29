Ext.define("ComplaintInfoApp.model.ComplaintInfoModel",{
	extend : 'Ext.data.Model',  
     fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },{  
                name : 'phone',  
                type : 'string'  
            },{  
                name : 'blocid',  
                type : 'string'  
            }, {  
            	name : 'blocname',  
                type : 'string'  
            }, { 
            	name : 'carnumber',  
                type : 'string'  
            }, { 
            	name : 'drivername',  
                type : 'string'  
            }, { 
            	name : 'driverphone',  
                type : 'string'  
            }, { 
            	name : 'type',  
                type : 'string'  
            }, {
            	name : 'typename',  
                type : 'string'  
            }, {
            	name : 'remark',  
                type : 'string'  
            }, {
            	name : 'passengername',  
                type : 'string'  
            }, {
            	name : 'passengerphone',  
                type : 'string'  
            }, {
            	name : 'orderid',  
                type : 'string'  
            }, {
            	name : 'complainttime',  
                type : 'string'  
            }, {
            	name : 'dealstatus',  
                type : 'string'  
            }, {
            	name : 'dealtime',  
                type : 'string'  
            }, {
            	name : 'dealman',  
                type : 'string'  
            }, {
            	name : 'dealcontent',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 