Ext.define('loginApp.controller.LoginCtrl', {
    extend: 'Ext.app.Controller',
    stores: [],//声明该控制层要用到的store
    models: [],//声明该控制层要用到的model
    views: ['LoginView'],//声明该控制层要用到的view
    refs: [//相当于一个映射,这样就可以在控制层方便的通过geter取得相应的对象了
        {
            ref: 'loginView',
            selector: 'loginView'
        }
    ],
    init: function() {
    	
		this.control({
			'loginView button[action=login]' : {
				click : this.userLogin
			},
			'loginView' : {
				afterrender : this.loadCookie
			}
		});
	},
	loadCookie : function(button) {
		var username = getCookieValue("cookTaxiUser");
		var password = getCookieValue("cookTaxiPass");
		Ext.getCmp('loginname').setValue(charArrayToStr(BASE64.decoder(username)));
		Ext.getCmp('password').setValue(charArrayToStr(BASE64.decoder(password)));
		if(username.length>0 && password.length >0){
			Ext.Ajax.request( {
				url : window.BIZCTX_PATH + '/userLoginJson/getVeryCode.action',
				method : 'POST',  
				success : function(response) {
					var text = response.responseText;
				   	var verycode = Ext.JSON.decode(text).result.verycode;
				   	Ext.getCmp('verycode').setValue(verycode);
				},
				failure : function() {
				}
			});
		}
	},
	userLogin : function(button) {
		var form = button.up('form');
		if (!form.getForm().isValid()) {
			return;
		}
//		var mask=new Ext.LoadMask('loginViewPanel',{msg:"正在登陆,请稍后......"});
//		mask.show();
		form.getForm().submit({
			url : window.BIZCTX_PATH + '/userLoginJson/userLogin.action',
			method : 'post',
			timeout:300000,
			success : function(form, action) {
//					mask.hide();
					var resp = action.result.result;
					var su = resp.su;
					if (su == -1) {
						Ext.Msg.alert("提示","验证码不正确请重新输入!");
						document.getElementById("authImg").src= window.BIZCTX_PATH + '/AuthImg?now=' + new Date();
					}else if (su == -2) {
						Ext.Msg.alert("提示","用户名密码不正确!");
						document.getElementById("authImg").src= window.BIZCTX_PATH + '/AuthImg?now=' + new Date();
					}else if (su == 0) {
						Ext.Msg.alert("提示","用户已被停用,请与管理员联系!");
					}else {
						var remindpassword = Ext.getCmp('remindpassword').getValue();
						if(remindpassword){
							addCookie();
						}
						window.location.href = window.BIZCTX_PATH  + '/main.jsp';
					}
		    },
			failure : function(form, action) {
//				mask.hide();
				Ext.Msg.alert('提示', "登陆失败,请联系管理员!");
			}
		});
		return false;
	}
});


function charArrayToStr(cArray){
	var str = '';  
	for(var i = 0 ,len = cArray.length ; i < len ;++i){  
	      str += String.fromCharCode(cArray[i]);  
	} 
	return str;
}


