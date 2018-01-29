Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'CarPhotoApp',
	appFolder : window.BIZCTX_PATH + '/taxi/reportforms/carphoto',
    controllers: [
        'CarPhotoCtrl'
    ],
    
    launch: function() {
		   Ext.create('Ext.container.Viewport', {
		             itemId:'CarPhotoCtrlPanel',
		             border:false,
		             layout : 'border',
					 collapsible: true,
					 header : false, 
				     renderTo:Ext.getBody(),
					 items: [
					         {xtype:'carInfoListView'},
					         {xtype:'rightView'}
					 ]
			});
    }
});


