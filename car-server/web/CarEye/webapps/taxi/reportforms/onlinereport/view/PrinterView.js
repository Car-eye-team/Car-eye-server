Ext.define('OnlineReportApp.view.PrinterView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.printerView',
	id : 'printerView' ,
	title : '图形展示————————单击柱形图可查看详细',
    width : 600,
//    minHeight:550,
//    autoHeight:true,
    region: "west",
    layout: 'fit',
	itemId :'printerWindow',
	modal : true,
	border : true,
	split:true,
	frame: true,
	closeAction : 'destroy',
	html:'<div id="echart" style="width:600px;height:480px;float:left;"></div>'
//	html : '11111111111'
	
});
