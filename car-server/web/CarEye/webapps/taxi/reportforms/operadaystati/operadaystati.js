Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'OperaDayStatiApp',//为应用程序起一个名字,相当于命名空间  Daily operation statistics 日均营运统计
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/reportforms/operadaystati',
    controllers: [//声明所用到的控制层
        'OperaDayStatiCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '日均营运统计',
		             itemId:'OperaDayStatiCtrlPanel',
		             border:false,
		             layout : 'border',  
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [ {xtype:'operaDayStatiListView'},{xtype:'operaDayStatiSearchView'}]
			});
		  
    }
});
