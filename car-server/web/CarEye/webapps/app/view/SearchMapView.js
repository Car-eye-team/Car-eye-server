/*
    地图展示panel
*/
Ext.define('FMS.view.SearchMapView',{
    extend : 'Ext.window.Window',
    alias: 'widget.searchMapView',
    border:true,
    width:500,
    height:350,
    itemId :'searchMapWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : true,
	minimizable : true,
	frame: false,
	closable : true,
	id:'searchMapWin',
	closeAction : 'destroy',
      items: [
            {
		        xtype : 'hidden',
		        id : 'c_lng',
		        itemId:'c_lng',
		        name:'c_lng',
		        fieldLabel : '经度'
			},{
		        xtype : 'hidden',
		        id : 'c_lat',
		        itemId:'c_lat',
		        name:'c_lat',
		        fieldLabel : '纬度'
			},{
		        xtype : 'hidden',
		        id : 'c_carstatus',
		        itemId:'c_carstatus',
		        name:'c_carstatus',
		        fieldLabel : '状态'
			},{
		        xtype : 'hidden',
		        id : 'c_carnumber',
		        itemId:'c_carnumber',
		        name:'c_carnumber',
		        fieldLabel : '车牌号'
			},{
		        xtype : 'hidden',
		        id : 'c_cwaaddr',
		        itemId : 'c_cwaaddr',
		        fieldLabel : '地址'
			},{
		        xtype : 'hidden',
		        id : 'c_createtime',
		        itemId : 'c_createtime',
		        fieldLabel : '时间'
			}
			
	]
		
   
});