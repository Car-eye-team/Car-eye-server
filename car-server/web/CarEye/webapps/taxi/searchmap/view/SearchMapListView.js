Ext.define('SearchMapApp.view.SearchMapListView' ,{
    extend: 'Ext.panel.Panel',
    border : false,
    alias : 'widget.searchMapListView',
			region: "east",
			width:250,
			title:'搜索列表',
            collapsible: true,
            collapseMode: "mini",
            split: true,
			frame: true,
			autoScroll : true,
 			bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
			items: [{
				id : 'poiPanel',
				xtype: 'panel',
				overflow : false,
				layout:'fit',
				height:500
			}
			]
});

