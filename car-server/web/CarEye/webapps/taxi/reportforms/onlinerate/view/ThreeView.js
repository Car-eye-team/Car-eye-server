Ext.define('OnlineRateApp.view.ThreeView' ,{
    extend: 'Ext.panel.Panel',
	alias : 'widget.threeView',
//	autoHeight : true,
	region: "center",
	border: false,
	split: true,
	autoWidth: true,
	collapsed: false,
	id:'threeView',
    collapsible: true,
    header : false, // 显示 header 默认 true
    collapseMode: "mini",
    layout: 'fit',
    margins: '0 2 2 2',
    items: [{
			xtype:'threeCarInfoListView'
		},{
			xtype:'threePrinterView'
     }]
});