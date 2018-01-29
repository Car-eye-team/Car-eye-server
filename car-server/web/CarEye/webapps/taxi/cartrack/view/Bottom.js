Ext.define('CarTrackApp.view.Bottom' ,{
    extend: 'Ext.panel.Panel',
	alias : 'widget.bottom',
	autoHeight : true,
	region: "south",
	border: false,
	split: true,
	frame: true,
	autoWidth: true,
	collapsed: false,
	id:'trackbottom',
    collapsible: true,
    header : false, // 显示 header 默认 true
    collapseMode: "mini",
    layout: 'fit',
    margins: '0 2 2 2',
    items: [{
         xtype: 'tabpanel',
         id:'tracktabpanel',
		 defaults: {
            autoScroll: true    //是否有滚动条
         },
         items: [
	        {
                xtype: 'panel',
                id:'carTrackList',
                title: '车辆历史信息',
                items: [{xtype : 'carTrackListView'}]
            }
        ],
        listeners: {}
     }],
     listeners: {
			'resize': {
	            fn: function(com,width,height,oldwidth,oldheight,obj){
	            	
	            	var tabpanel = Ext.getCmp('tracktabpanel');
	 				var treepanel = tabpanel.getActiveTab();
	 				var view = Ext.getCmp(treepanel.id + 'View')
	            	
	            	if(typeof oldheight != "undefined"){
	            		Ext.getCmp('carTrackListView').setHeight(view.getHeight()+(height-oldheight));
	            	}
	            }
            }
			
       }
});


