Ext.define('AlarmmusicSetApp.view.AlarmmusicSetAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.alarmmusicSetAddView',
	title : '新增报警类型',
    width : 400,
	layout : 'form',
	itemId :'alarmmusicSetAddWindow',
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
		itemId :'alarmmusicSetAddPanel',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 90
	    },
		layout : 'form',
		items : [{
					xtype : 'textfield',
					id:'alarmname',
					fieldLabel : '<font color="red">*</font>报警类型名称',
					name : 'alarmmusicSet.alarmname',
					maxLength : 20,  
					maxLengthText : '最大长度不超过20位!', 
					allowBlank : false,
					blankText : '不能为空',
					anchor : '100%',
					regex : /^[\u4E00-\u9FA5]+$/,
					regexText : '报警类型名称只能为中文!',
					cls : 'x-textfield'
				}, {
					xtype: "fieldcontainer",
				    layout: { type: "hbox" },
				    width:360, 
				    items: [ {
							xtype : 'textfield',
							fieldLabel : '<font color="red">*</font>报警声音',
							name : 'alarmmusicSet.musicaddr',
							id : 'musicaddr',
							anchor : '100%',
							width:320, 
							editable: false,
							allowBlank : false
						}, {
							xtype : 'filefield',
							name : 'fileInput',
							fieldLabel : '文件上传',
							id : 'fileInput'
					}]  
				},{
					xtype : 'fieldcontainer',
					border : false,
					html : '<span id="fileQueue"></span>'
				},{
								xtype : 'textarea',
								fieldLabel : '备注',
								name : 'alarmmusicSet.remark',
								rows :5,
								anchor : '100%',
								itemId : 'remark',
								id : 'remark',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 51,
								maxLengthText : '最大长度不超过51位!',
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


				


