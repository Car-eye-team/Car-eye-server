Ext.define('SoftPhoneApp.view.MapWindowView',{
    extend : 'Ext.window.Window',
    alias: 'widget.mapWindowView',
    frame: true,
    border:false,
	animCollapse:false,
	title:'地图定位',
	constrain : true,
	constrainHeader : true,
	maximizable : true,
	minimizable : true,
	id:'mapWindowView',
	height:500,
	width:780,
	closable : true,
	dockedItems:[{xtype:'mapView'},{xtype:'centerListView'},{xtype:'searchMapView'}]
});