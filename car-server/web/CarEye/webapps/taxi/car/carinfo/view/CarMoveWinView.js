Ext.define('CarInfoApp.view.CarMoveWinView' ,{
	extend : 'Ext.window.Window',
	alias : 'widget.carMoveWinView',
    width : 780,
    title : '车辆转移',
	//layout : 'form',
	id:'carMoveWinView',
	constrain : true,
	constrainHeader : true,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items :[ {
		xtype : 'form',
		frame : true,
		border : false,
		collapsible : false,
		buttonAlign : 'right',
		//layout : 'form',
		items : [{
			layout : 'column',
			border : false,
			//columnWidth : 1,
			items : [{
					columnWidth :.55,
					layout : 'form',
					border:false,
					items : [ {
						xtype:'carWinTreeView'
					},{
						xtype:'hidden',
						id:'move_deptid'
					},{
						xtype:'hidden',
						id:'move_deptname'
					}]
			},{
					columnWidth :.45,
					layout : 'form',
					border:false,
					items : [ {
						xtype:'deptWinTreeView'
					}]
			}]
		}]
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

