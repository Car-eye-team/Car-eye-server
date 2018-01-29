Ext.define("RideOrderApp.model.RidePassengerListModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true //这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            },{  
                name : 'seq',  
                type : 'int' 
            },{  
                name : 'passagename',  
                type : 'string' 
            },{  
                name : 'phone',  
                type : 'string' 
            },{  
                name : 'orderid',  
                type : 'string' 
            },{  
                name : 'saddress',  
                type : 'string' 
            },{  
                name : 'slat',  
                type : 'string' 
            },{  
                name : 'slng',  
                type : 'string' 
            },{  
                name : 'eaddress',  
                type : 'string' 
            },{  
                name : 'elat',  
                type : 'string' 
            },{  
                name : 'elng',  
                type : 'string' 
            },{  
                name : 'stime',  
                type : 'string' 
            },{  
                name : 'etime',  
                type : 'string' 
            },{  
                name : 'sumtime',  
                type : 'string' 
            },{  
                name : 'summile',  
                type : 'string' 
            },{  
                name : 'totalfee',  
                type : 'string' 
            },{  
                name : 'ordersatus',  
                type : 'string' 
            },{  
                name : 'createtime',  
                type : 'string' 
            },{  
                name : 'remark',  
                type : 'string' 
            },{  
                name : 'carid',  
                type : 'string' 
            },{  
                name : 'carnumber',  
                type : 'string' 
            }                 
              ],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 