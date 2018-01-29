/*
    地图展示panel
*/
Ext.define('SoftPhoneApp.view.MapView',{
    extend: 'Ext.panel.Panel',
    alias : 'widget.mapView',
    border:false,
    dock: 'top',
    id:'mapView',
//    title: 'POI搜索',
//    height:55,
//    frame : true,
//    split: true,
	tbar: [
	      {
		        xtype : 'textfield',
		        width : 200,
		        id:'addr_search',
		        maxLength : 20,
		        labelWidth: 60,
		        margin:'0 0 0 20',
		        fieldLabel : 'POI搜索',
		        emptyText:'请输入'
			}, {
				xtype: 'button',
				text : '查询',
				tooltip : '查询',
				iconCls : 'common-search-icon',
				action: 'searchtext'
			}
	  ]
});