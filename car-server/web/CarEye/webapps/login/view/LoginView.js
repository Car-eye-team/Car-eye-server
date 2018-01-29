Ext.define('loginApp.view.LoginView' ,{
    extend: 'Ext.form.Panel',
    alias : 'widget.loginView',
    title:'车辆管理部标平台-用户登陆',
    id:'loginViewPanel',
    iconCls : 'common-login-panel',
			frame : true,
			layout : 'vbox',
			defaults : {
				margin : 8,
				width : 300,
				labelAlign : 'right'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : '用户名',
				blankText : '请输入您的登陆账号',
				name : 'user.loginname',
				id : 'loginname',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '密码',
				blankText : '请输入您的登陆密码',
				name : 'user.password',
				id : 'password',
				allowBlank : false,
				inputType : 'password'
			},{
                xtype : 'fieldcontainer',
                layout : 'hbox',
                items : [{
                    flex : 1,
                    xtype : 'textfield',
	                labelAlign : 'right',
	                allowBlank : false,
	                msgTarget : 'qtip',
	                fieldLabel : '验&nbsp;证&nbsp;码',
	                blankText : '请输入右边图片中的数字',
                    maxLength : 4,
                    maxLengthText : '请输入四位验证码',
                    minLength : 4,
                    minLengthText : '请输入四位验证码',
                    name : 'user.veryCode',
                    id:'verycode',
                    listeners: {
                        specialkey: function (textfield, e) {
                            if (e.getKey() == Ext.EventObject.ENTER) {
                                Ext.get('tms_login').dom.click();
                            }
                        }
                    }
	            }, {
                    xtype : 'container',
                    width : 80,
                    margin : '0 0 0 5',
                    html:'<img id="authImg" src="'+window.BIZCTX_PATH+'/AuthImg" onclick="refresh();" alt="看不清？点击刷新" style=\'cursor:pointer;\'/>'
                }]
            },{
            	 xtype:'checkboxfield',
            	 boxLabel: '记住密码',
                 id:'remindpassword',
                 itemId:'remindpassword',
                 margin:'0 0 0 115',
                 checked:true
            }
            ],
			buttonAlign : 'center',
		    buttons : [{
			    text : '登陆',
			    id : 'tms_login',
			    tooltip : '登陆车辆管理部标平台',
			    iconCls : 'common-login-ico',
			    action: 'login'
			}, {
			    text : '重置',
			    tooltip : '清空登陆条件',
			    iconCls : 'common-reset-icon',
			    action : 'reset',
		        	handler: function(button){
		        	button.up('form').getForm().reset();
		        }
			}]
});


function refresh()
{
	document.getElementById("authImg").src= window.BIZCTX_PATH + '/AuthImg?now=' + new Date();
}
