Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'OnlineRateApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/reportforms/onlinerate',
    controllers: [//声明所用到的控制层
        'OnlineRateCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '车辆在线率分析',
		             itemId:'OnlineRateCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'onlineRateSearchView'},{xtype:'bottom'}]
			});
    }
});
