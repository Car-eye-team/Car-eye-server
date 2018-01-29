Ext.define('ComplaintInfoApp.view.ComplaintInfoDealView', {
	extend : 'Ext.window.Window',
	alias : 'widget.complaintInfoDealView',
	title : '处理投诉',
    width : 400,
	layout : 'form',
	itemId :'complaintInfoDealWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items :[ { 
		xtype : 'form',
		frame : true,
		anchor : '100%',
		itemId :'complaintInfoDealPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 70
	    },
		layout : 'form',
		items : [{
					xtype : 'hidden',
					id:'id',
					name:'complaintInfo.id'
				},{
					xtype : 'textarea',
					fieldLabel : '<font color="red">*</font>处理内容',
					name : 'complaintInfo.dealcontent',
					rows :5,
					anchor : '100%',
					itemId : 'dealcontent',
					id : 'dealcontent',
					cls : 'x-textfield'
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


				


