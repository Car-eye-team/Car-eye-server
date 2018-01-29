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
            },{  
                name : 'blocname',  
                type : 'string'
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'devicenumber',  
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
            }, {  
                name : 'carnumbercolor',  
                type : 'string'
            }, {  
                name : 'carnumbertype',  
                type : 'string'
            }, {  
                name : 'driverid',  
                type : 'string'
            },  {  
                name : 'drivername',  
                type : 'string'
            },{  
                name : 'drivername',  
                type : 'string'
            }, {  
                name : 'cartype',  
                type : 'string'
            }, {  
                name : 'businesstype',  
                type : 'string'
            }, {  
                name : 'factory',  
                type : 'string'
            },{  
                name : 'carmodel',  
                type : 'string'
            }, {  
                name : 'carstyle',  
                type : 'string'
            }, {  
                name : 'productaddr',  
                type : 'string'
            }, {  
                name : 'vehiclelocation',  
                type : 'string'
            }, {  
                name : 'oiltype',  
                type : 'string'
            }, {  
                name : 'outercolor',  
                type : 'string'
            }, {  
                name : 'innercolor',  
                type : 'string'
            }, {  
                name : 'framenumber',  
                type : 'string'
            }, {  
                name : 'drivlicnum',  
                type : 'string'
            }, {  
                name : 'transmissiontype',  
                type : 'string'
            }, {  
                name : 'transmissionnumber',  
                type : 'string'
            }, {  
                name : 'ventingmeasure',  
                type : 'string'
            }, {  
                name : 'altitude',  
                type : 'string'
            }, {  
                name : 'enginenumber',  
                type : 'string'
            }, {  
                name : 'buytime',  
                type : 'string'
            }, {  
                name : 'insuranceexpires',  
                type : 'string'
            }, {  
                name : 'carstatus',  
                type : 'string'
            }, {  
                name : 'controlstatus',  
                type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'
            },  {  
                name : 'travelposition',  
                type : 'string'  
            },  {  
                name : 'car_typename',  
                type : 'string'  
            },  {  
                name : 'car_name',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });