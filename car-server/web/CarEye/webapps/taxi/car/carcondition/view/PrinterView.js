Ext.define('CarConditionApp.view.PrinterView', {
	extend : 'Ext.window.Window',
	alias : 'widget.printerView',
	title : '报表展示',
    width : 700,
    minHeight:550,
    autoHeight:true,
    layout: 'fit',
	itemId :'printerWindow',
	modal : true,
	border : false,
	closeAction : 'destroy',
	html:'<div id="echart1" style="width:320px;height:260px;float:left;"></div>' +
		 '<div id="echart2" style="width:320px;height:260px;float:left;"></div>' +
		 '<div id="echart3" style="width:320px;height:260px;float:left;"></div>' +
		 '<div id="echart4" style="width:320px;height:260px;float:left;"></div>'
	
});