Ext.Loader.setConfig({enabled:true});//开启动态加载

Ext.application({
	name:'FeedbackAdviceApp',//为应用程序起一个名字,相当于命名空间
	   //应用的根目录
	appFolder:window.BIZCTX_PATH+ '/taxi/sysset/phoneclient/feedbackadvice',
	controllers: [//声明所用到的控制层
       'FeedbackAdviceCtrl'
   ],
    launch:function(){
    	Ext.create('Ext.container.Viewport',{
		             title: '意见反馈',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, // 显示 header 默认 true
				   //  renderTo:Ext.getBody(),
				     items: [{xtype:'feedbackAdviceSearchView'},{xtype:'feedbackAdviceListView'}]
			});
			/*Ext.create('Ext.container.Viewport', {
    layout: 'border',
    items: [{
        region: 'north',
        html: '<h1 class="x-panel-header" align="center">Page Title</h1>',
        border: false,
        margins: '0 0 5 0'
    }, {
        region: 'west',
         html: 'Information goes here',
        collapsible: true,
        title: 'Navigation',
        width: 150
        // 这里通常可以使用 TreePanel 或者 AccordionLayout布局的导航菜单
    }, {
        region: 'south',
        title: 'South Panel',
        collapsible: true,
        html: 'Information goes here',
        split: true,
        height: 100,
        minHeight: 100
    }, {
        region: 'east',
        title: 'East Panel',
        collapsible: true,
        split: true,
        width: 150
    }, {
        region: 'center',
        xtype: 'tabpanel', // TabPanel本身没有title属性
        activeTab: 0,      // 配置默认显示的激活面板
        items: [{
            title: 'Default Tab',
            html: 'The first tab\'s content. Others may be added dynamically'
        },
        {
            title: '2Default Tab',
            html: 'The first tab\'s content. Others may be added dynamically'
        }]
    }]
});*/
    }
});
