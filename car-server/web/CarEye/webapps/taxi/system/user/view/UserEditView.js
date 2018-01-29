Ext.define('userApp.view.UserEditView', {
	extend : 'Ext.window.Window',
	alias : 'widget.userEditView',
	id:'useredit',
	title : '修改用户信息',
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
	id:'userEditView',
	items :[ {
		xtype : 'form',
		frame : true,
		anchor : '100%',
		collapsible : false,
		buttonAlign : 'right',
		fieldDefaults: {
	        labelAlign: 'right',
	        labelWidth: 80
	    },
		layout : 'form',
		items : [{
			layout : 'column',
			columnWidth : 1,
			items : [{
					columnWidth : .49,
					layout : 'form',
					border:false,
					items : [{
			                    xtype: 'hidden',
			                    name : 'user.id',
								id : 'id'
							},{
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>登录名',
								name : 'user.loginname',
								itemId : 'loginname',
								id : 'loginname',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 20,
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								anchor : '100%',
								cls : 'x-textfield'
							}, {
								xtype : 'textfield',
								fieldLabel : '<font color="red">*</font>用户密码',
								name : 'user.password',
								itemId : 'password',
								id : 'password',
								anchor : '100%',
								cls : 'x-textfield',
								minLength : 6,
								minLengthText : '最小长度不小于6位!',
								maxLength : 20,
								maxLengthText : '最大长度不超过20位!',
								allowBlank : false,
								blankText : '不能为空',
								regex : /[a-zA-Z0-9]{6,20}/,
								regexText : '只能输入6~20个必须含有字母或数字的字符串!',
								inputType : 'password',
								enableKeyEvents : true
							}, {
								xtype : 'textfield',
								fieldLabel : '用户姓名',
								name : 'user.username',
								itemId : 'username',
								id : 'username',
								anchor : '100%',
								cls : 'x-textfield',
								minLength : 2,
								minLengthText : '最小长度不小于2位!',
								maxLength : 10,
								maxLengthText : '最大长度不超过10位!',
								allowBlank : true,
								regex : /^([\u4E00-\u9FA5]+|[a-zA-Z]+)$/,
								regexText : '只能输入2~10个字母或汉字的名字!',
								enableKeyEvents : true
							}, {
								xtype : 'textfield',
								fieldLabel : '手机号码',
								itemId : 'mobile',
								id : 'mobile',
								name : 'user.mobile',
								anchor : '100%',
								minLength : 11,
								minLengthText : '长度为11为数字!',
								maxLength : 11,
								maxLengthText : '长度为11为数字!',
								allowBlank : true,
								regex : /^[1][358][0-9]{9}$/,
								regexText : '以1开头的11位数字',
								cls : 'x-textfield'
							}, {
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>用户状态',
								name : 'user.state',
								hiddenName : 'state',
								itemId : 'state',
								id : 'state',
								anchor : '100%',
								allowBlank : false,
								blankText : '请选择',
								cls : 'x-textfield',
								editable: false,
								validateOnChange: false,
								store : Ext.create('Ext.data.Store', {
								    fields: ['state', 'name'],
								    data : [
								        {"state":"1", "name":"激活"},
								        {"state":"2", "name":"停用"}
								    ]
								}),
								displayField : 'name',
								valueField : 'state'
							}, {
								xtype : 'combo',
								fieldLabel : '用户性别',
								name : 'user.usersex',
								hiddenName : 'usersex',
								itemId : 'usersex',
								id : 'usersex',
								anchor : '100%',
								cls : 'x-textfield',
								editable: false,
								validateOnChange: false,
								store : Ext.create('Ext.data.Store', {
								    fields: ['usersex', 'name'],
								    data : [
								        {"usersex":"1", "name":"男"},
								        {"usersex":"2", "name":"女"}
								    ]
								}),
								displayField : 'name',
								valueField : 'usersex'
							} ]
				}, {
					columnWidth : .49,
					layout : 'form',
					border:false,
					items : [{
								xtype : 'combo',
								fieldLabel : '<font color="red">*</font>企业组',
								itemId : 'blocgroupid',
								id : 'blocgroupid',
								name : 'user.blocgroupid',
								anchor : '100%',
								store : 'UserGroupListStore',
								editable: true,
								allowBlank : false,
								blankText : '请选择',
								cls : 'x-textfield',
								displayField : 'blocgroupname',
								valueField : 'blocgroupid'
							},{
//								xtype : 'combo',
//								maxLength : 20,
//								itemId : 'blocid',
//								id : 'blocid',
//								name:'user.blocid',
//								anchor : '100%',
//								store : 'BlocInfoTwoStore',
//								editable: true,
//								valueField : 'blocid',
//								displayField : 'blocname',
//								fieldLabel : '<font color="red">*</font>企业',
//								labelWidth: 50,
//								labelAlign: 'right'
//							},{
								xtype : 'comboboxtree',
								fieldLabel : '<font color="red">*</font>企业',
								name : 'user.blocid',
								id : 'blocid',
								anchor : '100%',
//								store : 'DeptInfoStore',
								store: Ext.create('Ext.data.TreeStore', {  
							        autoLoad : false,
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
							            	var store = Ext.getCmp('blocid').store;
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
							},
							{
								xtype : 'textfield',
								fieldLabel : '证件号',
								itemId : 'cardnumber',
								id : 'cardnumber',
								name : 'user.cardnumber',
								anchor : '100%',
								cls : 'x-textfield'
							}, {
								xtype : 'textfield',
								fieldLabel : '固定电话',
								itemId : 'telphone',
								id : 'telphone',
								name : 'user.telphone',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true,
								regex : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
								regexText : '格式：3-4位区号，7-8位直播号码，1－4位分机号'
							}, {
								xtype : 'textfield',
								fieldLabel : 'Email',
								itemId : 'email',
								id : 'email',
								name : 'user.email',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true,
								regex : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
								regexText : '形如abc@duosensoft.com'
							}, {
								xtype : 'textfield',
								fieldLabel : '分机号',
								id : 'runnumber',
								name : 'user.runnumber',
								anchor : '100%',
								cls : 'x-textfield',
								allowBlank : true,
								regex : /^[0-9]+$/,
								regexText : '只允许数字'
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


				


