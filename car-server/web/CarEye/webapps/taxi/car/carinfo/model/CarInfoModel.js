Ext.define("CarInfoApp.model.CarInfoModel",{
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
                name : 'preblocid',  
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
                name : 'password',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            },{  
                name : 'province',  
                type : 'string'
            },{  
                name : 'city',  
                type : 'string'
            }, {  
                name : 'district',  
                type : 'string'
            },{  
                name : 'provincename',  
                type : 'string'
            },{  
                name : 'cityname',  
                type : 'string'
            }, {  
                name : 'districtname',  
                type : 'string'
            }, {  
                name : 'color',  
                type : 'string'
            }, {  
                name : 'cartype',  
                type : 'string'
            }, {  
                name : 'caruse',  
                type : 'string'
            },{  
                name : 'drivername',  
                type : 'string'
            }, {  
                name : 'onedriverid',  
                type : 'string'
            }, {  
                name : 'twodriverid',  
                type : 'string'
            }, {  
                name : 'oiltype',  
                type : 'string'
            },{  
                name : 'framenumber',  
                type : 'string'
            }, {  
                name : 'drivlicnum',  
                type : 'string'
            }, {  
                name : 'transnumber',  
                type : 'string'
            }, {  
                name : 'ventingmeasure',  
                type : 'string'
            }, {  
                name : 'enginenumber',  
                type : 'string'
            }, {  
                name : 'framenumber',  
                type : 'string'
            }, {  
                name : 'buytime',  
                type : 'string'
            }, {  
                name : 'carstatus',  
                type : 'int'
            }, {  
                name : 'createtime',  
                type : 'string'
            },  {  
                name : 'type',  
                type : 'string'  
            },  {  
                name : 'typename',  
                type : 'string'  
            },  {  
                name : 'phone',  
                type : 'string'  
            }, {  
                name : 'typeid',  
                type : 'int'  
            }, {  
                name : 'devicetype',  
                type : 'string'  
            },  {  
                name : 'remark',  
                type : 'string'  
            },  {  
                name : 'typename',  
                type : 'string'  
            },  {  
                name : 'cartypename',  
                type : 'string'  
            },  {  
                name : 'usename',  
                type : 'string'  
            },  {  
                name : 'drivername1',  
                type : 'string'  
            },  {  
                name : 'drivername2',  
                type : 'string'  
            },  {  
                name : 'drivername3',  
                type : 'string'  
            },  {  
                name : 'provincename',  
                type : 'string'  
            },  {  
                name : 'cityname',  
                type : 'string'  
            },  {  
                name : 'districtname',  
                type : 'string'  
            },  {  
                name : 'devicenumber',  
                type : 'string'  
            },  {  
                name : 'devicemodel',  
                type : 'string'  
            },  {  
                name : 'carcolor',  
                type : 'string'  
            },  {  
                name : 'factory',  
                type : 'string'  
            },  {  
                name : 'carmodel',  
                type : 'string'  
            },  {  
                name : 'managenumber',  
                type : 'string'  
            },  {  
                name : 'ownername',  
                type : 'string'  
            },  {  
                name : 'owneraddress',  
                type : 'string'  
            },  {  
                name : 'installtime',  
                type : 'string'  
            },  {  
                name : 'registertime',  
                type : 'string'
            },  {  
                name : 'seatnumber',  
                type : 'string'  
            },  {  
                name : 'ownership',  
                type : 'string'  
            },  {  
                name : 'entertime',  
                type : 'string'  
            },  {  
                name : 'factorytime',  
                type : 'string'  
            },  {  
                name : 'fueltype',  
                type : 'string'  
            },  {  
                name : 'enginecapacity',  
                type : 'string'  
            },  {  
                name : 'capacitystandard',  
                type : 'string'  
            },  {  
                name : 'nowstatus',  
                type : 'string'  
            },  {  
                name : 'contracttime',  
                type : 'string'  
            },  {  
                name : 'proinsure',  
                type : 'string'  
            },  {  
                name : 'accinsure',  
                type : 'string'  
            },  {  
                name : 'ridinsure',  
                type : 'string'
            },  {  
                name : 'cominsure',  
                type : 'string'  
            },  {  
                name : 'dlwinsure',  
                type : 'string'  
            },  {  
                name : 'electagstatus',  
                type : 'string'
            },  { 
            	name : 'operatenumber',  
                type : 'string'
            },  {
            	name : 'operatestatus',  
                type : 'string'
            },  {
            	name : 'operateproperty',  
                type : 'string'
            },  {
            	name : 'licensetime',  
                type : 'string'
            },  {
            	name : 'certificatetype',  
                type : 'string'
            },  {
            	name : 'firstregisttime',  
                type : 'string'
            },  {
            	name : 'entryperson',  
                type : 'string'
            },  {
            	name : 'entrytime',  
                type : 'string'
            },  {
            	name : 'optremark',  
                type : 'string'
            },  {
            	name : 'drivercode',  
                type : 'string'
             },  {
            	name : 'drivername',  
                type : 'string'
            },  {
            	name : 'management_nature',  
                type : 'string'
             }, {
                name : 'terphone',  
                type : 'string'
             }, {
                name : 'vediono',  
                type : 'string'
             }, {
                 name : 'entryremark',  
                 type : 'string'
             }, {
                 name : 'vediotype',  
                 type : 'string'
              }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 
 
 
 
 
 