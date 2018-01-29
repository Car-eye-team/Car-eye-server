Ext.Loader.setConfig({enabled:true});
Ext.application({
		name:'FMS',
		appFolder:'app',
		launch:function() {
	        Ext.create('Ext.container.Viewport', {
	        	layout: 'border',
    			border: false,
    			renderTo:Ext.getBody(),//指明上层容器  
	            items: [{
	            			xtype:'top'
	            		},{
	                    	xtype:'left'
	                    },{
	                    	xtype:'center'
	                    },{
	                    	xtype:'bottom'
	                    },{
	                    	xtype:'right'
	                    }
	            ]
	        });
	    },
	    controllers:['Controllers']
	}
);