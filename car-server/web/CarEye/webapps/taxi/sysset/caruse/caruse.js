Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'CarUseApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/sysset/caruse',
    controllers: [//声明所用到的控制层
        'CarUseCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '车辆用途设置',
		             itemId:'CarUsePanel',
		             border:false,
		             layout : 'border',
			         collapsible: true,
			         header : false, // 显示 header 默认 true
		             renderTo:Ext.getBody(),
				     items: [{xtype:'carUseListView'}]
			});
    }
});
