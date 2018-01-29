Ext.define('RemoteControlApp.view.TypeListView' ,{
    extend: 'Ext.panel.Panel',
    id : 'typeViewGrid',
    frame: false,
	split: true,
    region: "center",
    alias : 'widget.typeListView',
    autoScroll:true,
	title:'设置选项',
	layout : 'form',
	items : [ {
		xtype : 'form',
		anchor : '100%',
		border:false,
		autoScroll:true,
		collapsible : false,
		buttonAlign : 'right',
		items : [{
			    layout: 'fit',
				xtype: 'treepanel',
				collapseMode: "mini",
    	        border: false,
    	        rootVisible: false,
    	        id: 'tree-panel',
    	   		store: 'ParmTypeTreeStore',
    	   		    listeners: {
		                'itemclick': {
			                fn: function(view, record, item, index, event) {
			                	if(record.get('id').length > 1){
				                	var pidList = ['sc','xh','dy','dd','wbxx','fsfwm','fsdz','zbfs','zdgj',
				                				   'zdfw','zdhfccsz','gbsjtx','gbsywxtx','rdkz'];
				                				   
				             		for(var i=0,len=pidList.length;i<len;i++){
										Ext.getCmp("form_"+pidList[i]).setVisible(false);
									}
									Ext.getCmp("form_"+record.get('id')).setVisible(true);
			                	}
			                }
			            }
		   		    }
	         }
		]
	} ]
	
});

