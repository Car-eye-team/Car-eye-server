Ext.define('FMS.view.Bottom' ,{
    extend: 'Ext.panel.Panel',
	alias : 'widget.bottom',
	autoHeight : true,
	region: "south",
	border: false,
	split: true,
	frame: true,
	autoWidth: true,
	collapsed: bottomcollapsed,
    collapsible: true,
    header : false, // 显示 header 默认 true
    collapseMode: "mini",
    minSize: 130,
    maxSize: 275,
    height: 200,
    layout: 'fit',
    id: 'south-panel',
    margins: '0 2 2 2',
    items: [{
         xtype: 'tabpanel',
		 defaults: {
            autoScroll: true    //是否有滚动条
         },
         items: [
	        {
                xtype: 'panel',
                title: '车辆实时信息',
                id:'carrealtime',
                items: [{xtype : 'carRealTime'}]
            }
         ]
     }]
     
});