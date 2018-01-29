Ext.define("AreaSetApp.model.AreaSetModel",{
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
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'areaid',  
                type : 'string'  
            }, {  
                name : 'terminal',  
                type : 'string'  
            }, {  
                name : 'carnumber',  
                type : 'string'  
            }, {  
                name : 'seq',  
                type : 'string'  
            }, {  
                name : 'result',  
                type : 'string'  
            }, {  
                name : 'areatype',  
                type : 'string'  
            }, {  
                name : 'opertype',  
                type : 'string'  
            }, {  
                name : 'areaname',  
                type : 'string'  
            }, {  
                name : 'attr0',  
                type : 'string'  
            }, {  
                name : 'attr1',  
                type : 'string'  
            }, {  
                name : 'attr2',  
                type : 'string'  
            }, {  
                name : 'attr3',  
                type : 'string'  
            }, {  
                name : 'attr4',  
                type : 'string'  
            }, {  
                name : 'attr5',  
                type : 'string'  
            }, {  
                name : 'attr6',  
                type : 'string'  
            }, {  
                name : 'attr7',  
                type : 'string'  
            }, {  
                name : 'attr8',  
                type : 'string'  
            }, {  
                name : 'attr9',  
                type : 'string'  
            }, {  
                name : 'ddalertinfo',  
                type : 'string'  
            }, {  
                name : 'driveralertinfo',  
                type : 'string'  
            }, {  
                name : 'stime',  
                type : 'string'  
            },{  
                name : 'etime',  
                type : 'string'
            },{  
                name : 'maxspeed',  
                type : 'string'
            },{  
                name : 'speedtime',  
                type : 'string'
            },{  
                name : 'username',  
                type : 'string'
            },{  
                name : 'ylat',  
                type : 'string'
            },{  
                name : 'ylng',  
                type : 'string'
            },{  
                name : 'radius',  
                type : 'string'
            },{  
                name : 'latlt',  
                type : 'string'
            },{  
                name : 'lnglt',  
                type : 'string'
            },{  
                name : 'latrb',  
                type : 'string'
            },{  
                name : 'lngrb',  
                type : 'string'
            },{  
                name : 'lngrb',  
                type : 'string'
            },{  
                name : 'latsrt',  
                type : 'string'
            },{  
                name : 'lngsrt',  
                type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'
            }, {  
                name : 'termvalidity',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });