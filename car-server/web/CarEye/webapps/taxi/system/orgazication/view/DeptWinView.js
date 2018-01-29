Ext.define('OrgazicationDeptApp.view.DeptWinView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.deptWinView',
	title : '企业列表',
    width : 400,
    frame: true,
	layout : 'form',
	constrain : true,
	constrainHeader : true,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items:[{
			xtype:'deptWinTreeView'
		},{
			xtype:'hidden',
			id:'move_blocid'
		},{
			xtype:'hidden',
			id:'move_blocname'
		}],
	buttons: [{
		text: '保存',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});

