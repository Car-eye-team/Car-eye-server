Ext.define('ComplaintTypeApp.view.ComplaintTypeEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.complaintTypeEditView',
	title : '修改投诉类型',
    width : 400,
	layout : 'form',
	itemId :'complaintTypeEditWindow',
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
		itemId :'complaintTypeEditPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 90
	    },
		layout : 'form',
		items : [{
					xtype : 'hidden',
					id:'id',
					name:'complaintType.id'
				},{
					xtype : 'textfield',
					id:'type',
					fieldLabel : '<font color="red">*</font>投诉类型',
					name : 'complaintType.type',
					maxLength : 10,  
					maxLengthText : '最大长度不超过10位!', 
					allowBlank : false,
					blankText : '不能为空',
					anchor : '100%',
					regex : /^\d+$/,
					regexText : '投诉类型必须为大于0的整数',
					cls : 'x-textfield'
				}, {
					xtype : 'textfield',
					id:'typename',
					fieldLabel : '<font color="red">*</font>投诉类型名称',
					name : 'complaintType.typename',
					maxLength : 20,  
					maxLengthText : '最大长度不超过20位!', 
					allowBlank : false,
					blankText : '不能为空',
					anchor : '100%',
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


				


