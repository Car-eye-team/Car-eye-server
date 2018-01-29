Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'authorityApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/system/authority',
    controllers: [//声明所用到的控制层
        'AuthorityCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '面板标题',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'authoritySearchView'},{xtype:'authorityListView'}]
			});
    }
});
