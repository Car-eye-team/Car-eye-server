Ext.define('ClockInfoApp.view.ClockMapView', {
	extend : 'Ext.window.Window',
	alias : 'widget.clockMapView',
	title : '上下班位置信息',
    width : 700,
    height:500,
    autoHeight:true,
	itemId :'clockMapView',
	modal : true,
	border : false,
	closeAction : 'destroy',
	items: [{xtype:'mapView'}]
			
});