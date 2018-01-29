Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'DialRuleApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/sysset/dialrule',
    controllers: [//声明所用到的控制层
        'DialRuleCtrl'
    ],
    
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'DialRuleCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'typeListView'},{xtype:'parameterSetView'}]
			});
    }
});


