Ext.define("CarDriverApp.model.CarDriverModel",{
	extend : 'Ext.data.Model',  
    fields : [{  
                name : 'id',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'blocid',  
                type : 'int'
            }, {  
                name : 'userid',  
                type : 'string'  
            }, {  
                name : 'drivername',  
                type : 'string'  
            }, {  
                name : 'blocname',  
                type : 'string'  
            }, {  
                name : 'tel',  
                type : 'string'  
            }, {  
                name : 'phone',  
                type : 'string'  
            },{  
                name : 'sex',  
                type : 'string'
            },{  
                name : 'addr',  
                type : 'string'
            }, {  
                name : 'idnumber',  
                type : 'string'
            }, {  
                name : 'birthday',  
                type : 'string'
            }, {  
                name : 'qualificationcertificate',  
                type : 'string'
            }, {  
                name : 'certifyingauthority',  
                type : 'string'
            }, {  
                name : 'drivecrednum',  
                type : 'string'
            }, {  
                name : 'driverannualdate',  
                type : 'string'
            }, {  
                name : 'createtime',  
                type : 'string'
            }, {  
                name : 'sscno',  
                type : 'string'
            }, {  
                name : 'nation',  
                type : 'string'
            }, {  
                name : 'education',  
                type : 'string'
            }, {  
                name : 'political',  
                type : 'string'
            }, {  
                name : 'nowstatus',  
                type : 'string'
            }, {  
                name : 'drivingnumber',  
                type : 'string'
            }, {  
                name : 'zjcartype',  
                type : 'string'
            }, {  
                name : 'validity',  
                type : 'string'
            }, {  
                name : 'remark',  
                type : 'string'
            }, {  
                name : 'firstlztime',  
                type : 'string'
            }, {  
                name : 'fztime',  
                type : 'string'
            }, {  
                name : 'driverid',  
                type : 'string'
            }, {  
                name : 'carid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'carnumber',  
                type : 'string'
            }, {  
                name : 'drivercode',  
                type : 'string'
            }],  
    idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取 
 });