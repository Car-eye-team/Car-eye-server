Ext.define("CarTrackApp.model.CarTrackModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'terminalnum',  
                type : 'string'  
            }, {  
                name : 'carstatus',  
                type : 'string'  
            }, {  
                name : 'address',  
                type : 'string'  
            }, {  
                name : 'motorcade_name',  
                type : 'string'  
            }, {  
                name : 'carbrand',  
                type : 'string'  
            }, {  
                name : 'carmodename',  
                type : 'string'  
            }, {  
                name : 'chauffeurname',  
                type : 'string'  
            }, {  
                name : 'lat',  
                type : 'string'  
            }, {  
                name : 'lng',  
                type : 'string'  
            }, {  
                name : 'phone',  
                type : 'string'  
            }, {  
                name : 'speed',  
                type : 'string'  
            }, {  
                name : 'direction',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'createtime',  
                type : 'string'  
            }, {  
                name : 'mileage',  
                type : 'string'  
            }, {  
                name : 'summileage',  
                type : 'string'  
            }, {  
                name : 'stoptime',  
                type : 'string'  
            }],
        idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });
 
 
 
 