/*
    地图展示panel
*/
Ext.define('CarTrackApp.view.SearchMapView',{
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
	frame: true,
	closable : true,
	id:'searchMapView',
	//modal : true,
	closeAction : 'destroy',
      items: [
            {
		        xtype : 'hidden',
		        id : 'c_lng'
			},{
		        xtype : 'hidden',
		        id : 'c_lat'
			},{
		        xtype : 'hidden',
		        id : 'c_carnumber'
			},{
		        xtype : 'hidden',
		        id : 'c_cwaaddr'
			},{
		        xtype : 'hidden',
		        id : 'c_createtime'
			},{
		        xtype : 'hidden',
		        id : 'c_direction'
			}
			
	]
		
   
});