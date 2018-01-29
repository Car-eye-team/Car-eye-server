Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'SearchMapApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/searchmap',
    controllers: [//声明所用到的控制层
        'SearchMapCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '一键导航',
		             itemId:'SearchMapCtrlPanel',
		             layout : 'border',
		             border:false,
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'addrSearchView'},{xtype:'searchMapView'},{xtype:'carInfoListView'}
				     ,{xtype:'centerListView'}//高德地图搜索使用
				     //,{xtype:'searchMapListView'}百度地图搜索使用
				     ]
			});
    }
});
