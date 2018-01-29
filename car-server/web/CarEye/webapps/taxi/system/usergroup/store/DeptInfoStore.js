Ext.define("userGroupApp.store.DeptInfoStore",{
	extend:"Ext.data.Store",
	autoLoad: true,
	fields : [{  
                name : 'deptid',  
                type : 'int',  
                useNull : true//这样数字如果值为空则不会自动转成0,则提交时注意后台bean类中的属性int要用对象类型，否则解析出错  
            }, {  
                name : 'deptname',  
                type : 'String'
            }],
	proxy: {
		type: 'ajax',
		url: window.BIZCTX_PATH + '/orgazicationdeptjson/selectorgazicationDeptList.action',  //请求的服务器地址
		reader: {
			type: 'json',
			root: 'result.list'
		}
	}
 });