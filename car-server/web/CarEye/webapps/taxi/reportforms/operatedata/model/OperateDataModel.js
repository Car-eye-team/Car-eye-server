Ext.define("OperateDataApp.model.OperateDataModel",{
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
                name : 'createtime',  
                type : 'string'
            },{  
                name : 'kalarminfo',  
                type : 'string'
            }, {  
                name : 'kstateinfo',  
                type : 'string'
            },{  
                name : 'klat',  
                type : 'string'
            },{  
                name : 'klng',  
                type : 'string'
            }, {  
                name : 'kaltitude',  
                type : 'string'
            }, {  
                name : 'kspeed',  
                type : 'string'
            }, {  
                name : 'kdirection',  
                type : 'string'
            }, { 
            	name : 'ktime',  
                type : 'string'
            }, {  
                name : 'kaddress',  
                type : 'string' 
            }, {  
                name : 'zalarminfo',  
                type : 'string' 
            }, {  
                name : 'zstateinfo',  
                type : 'string'  
            }, {  
                name : 'zlat',  
                type : 'string'  
            }, {  
                name : 'zlng',  
                type : 'string'  
            },{  
                name : 'zaltitude',  
                type : 'string'
            },{  
                name : 'zspeed',  
                type : 'string'
            }, {  
                name : 'zdirection',  
                type : 'string'
            },{  
                name : 'ztime',  
                type : 'string'
            },{  
                name : 'zaddress',  
                type : 'string'
            }, {  
                name : 'runid',  
                type : 'string'
            }, {  
                name : 'peoplenumber',  
                type : 'string'
            }, {  
                name : 'evaluateid',  
                type : 'string'
            }, {  
                name : 'options',  
                type : 'string'  
            },{  
                name : 'companycode',  
                type : 'string'
            },{  
                name : 'drivercode',  
                type : 'string'
            }, {  
                name : 'driverid',  
                type : 'string'
            },{  
                name : 'stime',  
                type : 'string'
            },{  
                name : 'etime',  
                type : 'string'
            }, {  
                name : 'mileage',  
                type : 'string'
            }, {  
                name : 'airmileage',  
                type : 'string'
            }, {  
                name : 'fuelsurcharge',  
                type : 'string'
            }, { 
            	name : 'waitingtime',  
                type : 'string'
            }, { 
            	name : 'tradeamount',  
                type : 'string'
            }, { 
            	name : 'vehicletrips',  
                type : 'string'
            }, { 
            	name : 'tradetime',  
                type : 'string'
            }, { 
            	name : 'tradetype',  
                type : 'string'
            }, { 
            	name : 'cardtype',  
                type : 'string'
            }, { 
            	name : 'cardoem',  
                type : 'string'
            }, { 
            	name : 'csn',  
                type : 'string'
            }, { 
            	name : 'tradecardno',  
                type : 'string'
            }, { 
            	name : 'tradeseq',  
                type : 'string'
            }, { 
            	name : 'samno',  
                type : 'string'
            }, { 
            	name : 'samseq',  
                type : 'string'
            }, { 
            	name : 'cardtradeamount',  
                type : 'string'
            }, { 
            	name : 'tradebalance',  
                type : 'string'
            }, { 
            	name : 'extend',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 
 
 
 
 