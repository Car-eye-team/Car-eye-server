Ext.define('TerminalSetApp.view.TypeListView' ,{
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
				                	var pidList = ['16','19','21','23','24','26','27','28','29','34','39','40','41','44','45',
				                				   '46','47','64','65','66','67','68','69','70','71','72','73','85','86','87',
				                				   '88','89','90','91','92','93','94'];
				                				   
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

