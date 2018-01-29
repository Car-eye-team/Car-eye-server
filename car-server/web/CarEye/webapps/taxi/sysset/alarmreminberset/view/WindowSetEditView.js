Ext.define('WindowSetApp.view.WindowSetEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.windowSetEditView',
	title : '选择不需要提醒项',
    width : 600,
	layout : 'form',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items:[{
			xtype:'remindTreeView'
		},{
			xtype:'hidden',
			id:'reminduserid'
		}]
});


				


