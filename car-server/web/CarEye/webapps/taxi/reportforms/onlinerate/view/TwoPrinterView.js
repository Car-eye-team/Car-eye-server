Ext.define('OnlineRateApp.view.TwoPrinterView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.twoPrinterView',
	id : 'twoPrinterView' ,
//	title : '图形展示————————单击柱形图可查看详细',
    autoWidth : true,
    height: 700,
//    minHeight:550,
//    autoHeight:true,
    region: "center",
    layout: 'fit',
	itemId :'twoPrinterWindow',
	modal : true,
	border : true,
	closeAction : 'destroy',
	html:'<div style="margin:10px auto; width:200px;" id="carnumber2"></div>' +
			'<div id="echart2" style="width:99%;height:480px;float:left;"></div>'
//	html : '11111111111'
	
});
