/*
    地图展示panel
*/
Ext.define('AreaSetApp.view.SearchMapView',{
    extend : 'Ext.window.Window',
    alias: 'widget.searchMapView',
    border:true,
    width:500,
    height:350,
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : true,
	minimizable : true,
	frame: false,
	closable : true,
	id:'searchMapView',
	modal : true,
	closeAction : 'destroy',
      items: [
            {
		        xtype : 'hidden',
		        id : 'c_areaid'
			}
	]
		
   
});