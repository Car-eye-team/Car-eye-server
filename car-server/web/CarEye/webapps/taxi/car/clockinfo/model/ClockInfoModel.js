Ext.define("ClockInfoApp.model.ClockInfoModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'int',
                useNull : true
            },{  
                name : 'blocname',  
                type : 'string'
            }, {  
                name : 'userid',  
                type : 'string' 
            }, {  
                name : 'carid',  
                type : 'string' 
            }, {  
                name : 'username',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            },{  
                name : 'type',  
                type : 'string'
            },{  
                name : 'count',  
                type : 'string'
            }, {  
                name : 'result',  
                type : 'string'
            },{  
                name : 'mcs',  
                type : 'string'
            },{  
                name : 'cardnum',  
                type : 'string'
            }, {  
                name : 'totalnumber',  
                type : 'string'
            }, {  
                name : 'vehicletrips',  
                type : 'string'
            }, {  
                name : 'companycode',  
                type : 'string'
            }, {  
                name : 'drivercode',  
                type : 'string'
            },{  
                name : 'vehicleid',  
                type : 'string'
            }, {  
                name : 'signintime',  
                type : 'string'
            }, {  
                name : 'driverid',  
                type : 'string'
            }, {  
                name : 'stime',  
                type : 'string'
            },{  
                name : 'etime',  
                type : 'string'
            }, {  
                name : 'dbmileage',  
                type : 'string'
            }, {  
                name : 'dbyymileage',  
                type : 'string'
            }, {  
                name : 'jstmie',  
                type : 'string'
            }, {  
                name : 'totalamount',  
                type : 'string'
            }, {  
                name : 'cardamount',  
                type : 'string'
            }, {  
                name : 'bjmileage',  
                type : 'string'
            }, {  
                name : 'totalmileage',  
                type : 'int'
            }, {  
                name : 'createtime',  
                type : 'string'
            },  {  
                name : 'totalyymileage',  
                type : 'string'  
            },  {  
                name : 'price',  
                type : 'string'  
            },  {  
                name : 'totalwaittime',  
                type : 'string'  
            },  {  
                name : 'sbblng',  
                type : 'string'  
            },  {  
                name : 'sbblat',  
                type : 'string' 
            },  {  
                name : 'sbbaddress',  
                type : 'string' 
            },  {  
                name : 'xbblng',  
                type : 'string' 
            },  {  
                name : 'xbblat',  
                type : 'string' 
            },  {  
                name : 'xbbaddress',  
                type : 'string' 
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 
 
 
 
 