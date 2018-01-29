Ext.define('PhotoSetApp.view.ParameterSetView', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.parameterSetView',
	split : true,
	frame: false,
	border:false,
	width : 350,
	title:'服务证信息',
	autoScroll:true,
	region : "east",
	items : [ {xtype : 'photoView'},{xtype : 'serviceDetailView'} ]
});
