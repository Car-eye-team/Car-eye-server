Ext.define('userGroupApp.view.UserGroupAddView', {
	extend : 'Ext.window.Window',
	alias : 'widget.userGroupAddView',
	title : '新增企业组信息',
    width : 350,
	layout : 'form',
	itemId :'userGroupAddWindow',
	animCollapse:false,
	constrain : true,
	constrainHeader : true,
	maximizable : false,
	minimizable : false,
	closable : true,
	modal : true,
	border : false,
	closeAction : 'destroy',
	items : [ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		items : [ 
			{
					xtype : 'comboboxtree',
					fieldLabel : '<font color="red">*</font>企业',
					name : 'userGroup.blocid',
					id:'c_deptid',
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
							            	var store = Ext.getCmp('c_deptid').store;
											store.clearFilter(true);
											store.on('beforeload', function (store, options) {
									            var new_params = { 
									            	blocname: encodeURI(Ext.getCmp('c_deptid').getRawValue())
									            };
									            Ext.apply(store.proxy.extraParams, new_params);
									        });
									        store.reload(); 
							            }
							        }
								 }
				},
					{
					xtype : 'textfield',
					fieldLabel : '<font color="red">*</font>企业组名称',
					name : 'userGroup.blocgroupname',
					itemId :'blocgroupname',
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
				//id : 'usergroupdesc',
				maxLength : 250,
				maxLengthText : "输入不能大于250位"
			} ]
	} ],
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