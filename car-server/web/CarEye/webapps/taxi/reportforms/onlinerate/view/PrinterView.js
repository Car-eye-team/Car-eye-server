Ext.define('OnlineRateApp.view.PrinterView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.printerView',
	id : 'printerView' ,
//	title : '图形展示————————单击柱形图可查看详细',
    autoWidth : true,
//    minHeight:550,
//    autoHeight:true,
    region: "center",
    layout: 'fit',
	itemId :'printerWindow',
	modal : true,
	border : true,
	closeAction : 'destroy',
	html:'<div id="echart" style="width:600px;height:480px;float:left;"></div>'
//	html : '11111111111'
	
});
