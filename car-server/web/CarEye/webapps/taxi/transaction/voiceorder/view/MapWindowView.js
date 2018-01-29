/*
    地图展示panel
*/
Ext.define('VoiceOrderApp.view.MapWindowView',{
    extend : 'Ext.window.Window',
    alias: 'widget.mapWindowView',
    border:false,
	animCollapse:false,
	title:'地图定位',
	constrain : true,
	constrainHeader : true,
	maximizable : true,
	minimizable : true,
	id:'mapWindowView',
	frame: true,
	height:500,
	width:780,
	closable : true,
	//modal : true,
	dockedItems:[{xtype:'mapView'},{xtype:'centerListView'},{xtype:'searchMapView'}]
//	,
//	buttons: [{
//		text: '确定',
//		action: 'save'
//	}]
});