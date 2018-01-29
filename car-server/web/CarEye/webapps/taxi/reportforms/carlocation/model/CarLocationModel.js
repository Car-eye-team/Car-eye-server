Ext.define("CarLocationApp.model.CarLocationModel",{
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
                name : 'carstatus',  
                type : 'int',
                useNull : true
            }, {  
                name : 'travelposition',  
                type : 'int',
                useNull : true
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'blocname',  
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
            },{  
                name : 'district',  
                type : 'string'  
            },{  
                name : 'address',  
                type : 'string'  
            },{  
                name : 'createtime',  
                type : 'string'  
            },{  
                name : 'typename',  
                type : 'string'  
            },{  
                name : 'carnumbercolor',  
                type : 'string'  
            },{  
                name : 'color',  
                type : 'string'  
            },{  
                name : 'lng',  
                type : 'string'  
            },{  
                name : 'lat',  
                type : 'string'  
            },{  
                name : 'cartypename',  
                type : 'string'  
            },{  
                name : 'usename',  
                type : 'string'  
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });