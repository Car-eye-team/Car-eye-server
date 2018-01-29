Ext.define('AdvertTypeApp.view.AdvertTypeAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.advertTypeAddView',
	title : '新增广告类型信息',
    width : 300,
	layout : 'form',
	itemId :'advertTypeAddWindow',
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
		itemId :'advertTypeAddPanel',
		collapsible : false,
		//buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 60
	    },
		layout : 'form',
		items : [
				{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>类型名称',
					id:'typename',
					name : 'advertType.typename',
					tabIndex : 1,
					maxLength : 20,
					maxLengthText : '最大长度不超过20位!',
					allowBlank : false,
					blankText : '不能为空',
					enableKeyEvents : true,
					regex : /^[\u4E00-\u9FA5]+$/,
					regexText : '类型名称只能为中文!',
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


				


