Ext.define('OnlineRateApp.view.TwoView' ,{
    extend: 'Ext.panel.Panel',
	alias : 'widget.twoView',
//	autoHeight : true,
	region: "center",
	border: false,
	split: true,
	autoWidth: true,
	collapsed: false,
	id:'twoView',
    collapsible: true,
    header : false, // 显示 header 默认 true
    collapseMode: "mini",
    layout: 'fit',
    margins: '0 2 2 2',
    items: [{
			xtype:'twoCarInfoListView'
		},{
			xtype:'twoPrinterView'
     }]
});


