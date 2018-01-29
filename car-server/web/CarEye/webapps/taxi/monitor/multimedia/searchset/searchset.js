Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'SearchSetApp',
	appFolder : window.BIZCTX_PATH + '/taxi/monitor/multimedia/searchset',
    controllers: [
        'SearchSetCtrl'
    ],
    
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'SearchSetCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, 
				     renderTo:Ext.getBody(),
					 items: [
					         {xtype:'carInfoListView'},
					         {xtype:'typeListView'},
					         {xtype:'parameterSetView'}
					 ]
			});
    }
});


