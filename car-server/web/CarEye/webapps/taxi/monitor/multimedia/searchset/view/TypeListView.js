Ext.define('SearchSetApp.view.TypeListView' ,{
    extend: 'Ext.panel.Panel',
    id : 'typeViewGrid',
    frame: false,
	split: true,
    region: "center",
    alias : 'widget.typeListView',
    autoScroll:true,
	title:'下发指令选项',
	layout : 'form',
	items : [ {
		xtype : 'form',
		anchor : '100%',
		border:false,
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
			                	if(record.get('id').length == 2){
				                	var pidList = ['11','12'];
				                				   
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

