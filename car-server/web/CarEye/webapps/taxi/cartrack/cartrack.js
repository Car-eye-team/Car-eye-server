Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'CarTrackApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/cartrack',
    controllers: [//声明所用到的控制层
        'CarTrackCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '历史轨迹',
		             itemId:'CarTrackCtrlPanel',
		             layout : 'border',
		             border:false,
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:'cartrack',
				     items: [{xtype:'carTrackSearchView'},{xtype:'mapView'},{xtype:'bottom'}]
			});
    }
});
