Ext.define('CarPhotoApp.view.RightView',{
	extend:'Ext.panel.Panel',
	alias:'widget.rightView',
	region:'center',
	border : false,
	collapsible : true,
	header : false, // 显示 header 默认 true
	items : [{
		xtype : 'photoSearchView'
	},{
		xtype : 'photoListView'
	}]
});