Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'loginApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/login',
    controllers: [//声明所用到的控制层
        'LoginCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.panel.Panel', {
		             border:false,
		             width:350,
		             height:220,
		             //layout:'fit',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo: 'loginForm',
				     items: [{xtype:'loginView'}]
			});
    }
});
