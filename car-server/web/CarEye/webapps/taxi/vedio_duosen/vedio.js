Ext.Loader.setConfig({enabled:true});//开启动态加载

//函数入口处添加此行代码，保证弹出框不会被object的标签遮盖
Ext.useShims=true; 

Ext.application({
    name: 'VedioDuosenApp',//为应用程序起一个名字,相当于命名空间
    //应用的根目录
	appFolder : window.BIZCTX_PATH + '/taxi/vedio_duosen',
    controllers: [//声明所用到的控制层
        'VedioDuosenCtrl'
    ],
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             title: '视频',
		             itemId:'VedioCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				     renderTo:Ext.getBody(),
				     items: [{xtype:'vedioRight'},{xtype:'vedioView'},{xtype:'vedioView1'}]
			});
    }
});
