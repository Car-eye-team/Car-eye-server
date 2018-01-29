Ext.define('userGroupApp.view.UserGroupEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.userGroupEditView',
	id:'userGroupedit',
	title : '编辑企业组信息',
    width : 350,
	layout : 'fit',
	animCollapse:false,
	constrain : true,  //true则强制此window控制在viewport，默认为false
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	modal : true,  //modal:true为模式窗口，后面的内容都不能操作，默认为false
	border : false,
	closeAction : 'destroy',
	items : [ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		items : [ {
					xtype : 'hidden',
					id : 'id',
					name:'userGroup.id'
				},{
					xtype : 'comboboxtree',
					fieldLabel : '<font color="red">*</font>企业',
					name : 'userGroup.blocid',
					id : 'blocid',
					width:300,
					store: Ext.create('Ext.data.TreeStore', {  
				        autoLoad : 'true',
				        fields: ['text','id','parentId'], 
						root : {expanded : true,text : '企业' },
						proxy: {
							 type: 'ajax',
							 url: window.BIZCTX_PATH + '/servlet/DeptTree?type=200', 
							 reader: {
								 type: 'json'
							 }
						}
				    }) ,
				    rootVisible: false,
					editable: true,
					allowBlank : false,
					blankText : '请选择',
					cls : 'x-textfield',
					valueField: 'id', 
					displayField: 'text',
					listeners: {
							        change: {
							            element: 'el', 
							            fn: function(){ 
							            	var store = Ext.getCmp('deptid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('blocid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
				},{
//						xtype : 'combo',
//						maxLength : 20,
//						itemId : 'blocid',
//						id : 'blocid',
//						name:'userGroup.blocid',
//						store : 'BlocInfoTwoStore',
//						editable: true,
//						valueField : 'blocid',
//						displayField : 'blocname',
//						fieldLabel : '<font color="red">*</font>企业',
//						labelAlign: 'right'
//				},{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>企业组名称',
					name : 'userGroup.blocgroupname',
					itemId :'blocgroupname',
					id :'blocgroupname',
					width:300,
					cls : 'x-textfield',
					minLength : 3,
					minLengthText : '最小长度不小于3位!',
					maxLength : 15,
					maxLengthText : '最大长度不超过15位!',
					allowBlank : false
					
				},{
				xtype : 'textarea',
				width:300,
				fieldLabel : '描述',
				name : 'userGroup.blocgroupdesc',
				id : 'blocgroupdesc',
				maxLength : 250,
				maxLengthText : "输入不能大于250位"
			} ]
	} ],
	buttons: [{
		text: '修改',
		action: 'save'
	},{
		text: '取消',
		handler: function(btn){
			btn.up('window').close();
		}
	}]
});