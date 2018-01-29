Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'TerminalSetApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/monitor/terminalset/terminalset',
    controllers: [//声明所用到的控制层
        'TerminalSetCtrl'
    ],
    
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'TerminalSetCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'carInfoListView'},{xtype:'typeListView'},{xtype:'parameterSetView'}]
			});
    }
});


