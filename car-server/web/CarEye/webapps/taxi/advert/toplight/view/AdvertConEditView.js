Ext.define('TopLightApp.view.AdvertConEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.advertConEditView',
	title : '修改广告内容信息',
    width : 320,
	layout : 'form',
	itemId :'advertConEditWindow',
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
		itemId :'advertConEditPanel',
		collapsible : false,
		//buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 60
	    },
		layout : 'form',
		items : [{
			xtype : 'hidden',
			id:'id',
			name:'advertCon.id'
		},{
			xtype : 'textfield',
			fieldLabel : '<font color="red">*</font>广告名称',
			id:'adname',
			name : 'advertCon.adname',
			allowBlank : false,
			cls : 'x-textfield'
		},{
			xtype : 'datefield',
			fieldLabel : '到期日期',
			id:'dtime',
			name : 'advertCon.dtime',
			editable : false,
			format:'Y-m-d',
			cls : 'x-textfield'
		},{
			xtype : 'combo',
			fieldLabel : '特效',
			id:'con1',
			name : 'advertCon.con1',
			cls : 'x-textfield',
			store : 'TXStore',
			allowBlank : false,
			displayField : 'type',
			valueField : 'id'
		},{
			xtype : 'numberfield',
			fieldLabel : '<font color="red">*</font>速度',
			id:'con2',
			name : 'advertCon.con2',
			minValue : 1,
			minText : '最小数值为1!',
			maxValue : 10,
			maxText : '最大数值为10!',
			allowBlank : false,
//			regex : /^([1-9]|10)$/,
//			regexText : '请输入一个1-10之间的数值！',
			cls : 'x-textfield'
		},{
			xtype : 'numberfield',
			fieldLabel : '<font color="red">*</font>暂停',
			id:'con3',
			name : 'advertCon.con3',
			minValue : 1,
			minText : '最小数值为1!',
			maxValue : 255,
			maxText : '最大数值为255!',
			allowBlank : false,
//			regex : /^(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/,
//			regexText : '请输入一个0-255之间的数值！',
			cls : 'x-textfield'
		},{
			xtype : 'numberfield',
			fieldLabel : '<font color="red">*</font>重复',
			id:'con4',
			name : 'advertCon.con4',
			minValue : 0,
			minText : '最小数值为0!',
			maxValue : 254,
			maxText : '最大数值为254!',
			allowBlank : false,
//			regex : /^(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-4])$/,
//			regexText : '请输入一个0-254之间的数值！',
			cls : 'x-textfield'
		},{
			xtype : 'textarea',
			fieldLabel : '<font color="red">*</font>广告描述',
			id:'remark',
			name : 'advertCon.remark',
			allowBlank : false,
			rows:2,
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


				


