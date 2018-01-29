Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
    name: 'OnCallCountApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/statement/transtatement/oncallcount',
    controllers: [//声明所用到的控制层
        'OnCallCountCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '电召统计报表',
		             itemId:'OnCallCountCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'onCallCountSearchView'},{xtype:'onCallCountListView'}]
			});
    }
});
