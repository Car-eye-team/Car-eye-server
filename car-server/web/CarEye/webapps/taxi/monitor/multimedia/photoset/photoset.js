Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'PhotoSetApp',
	appFolder : window.BIZCTX_PATH + '/taxi/monitor/multimedia/photoset',
    controllers: [
        'PhotoSetCtrl'
    ],
    
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'PhotoSetCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, 
				     renderTo:Ext.getBody(),
					 items: [
					         {xtype:'carInfoListView'},
					         {xtype:'parameterSetView'}
					 ]
			});
    }
});


