/**
 * 回车事件触发表单提交
 */
$(document).ready(function(){
	$("#username").focus(); 
	$(document).keydown(function(e){ 
		var curkey = e.which; 
		if(curkey == 13){ 
			userlogin();
			return false; 
		} 
	}); 
});


function refresh(){
	document.getElementById("authImg").src= window.BIZCTX_PATH + '/AuthImg?now=' + new Date();
}

function userlogin(){
	var username = $('#username').val();
	var password = $('#password').val();
	var vcode = $('#vcode').val();
		if (username == '') {
			$("#error_tip").text("请输入用户名!");
			$('#username').focus();
			return;
		}
		
		if (password == '') {
			$("#error_tip").text("请输入密码!");
			$('#password').focus();
			return;
		}
		
		if (vcode == '') {
			$("#error_tip").text("请输入验证码!");
			$('#vcode').focus();
			return;
		}
		$("#error_tip").text("正在登陆,请稍后......");
		//发送登陆请求
		jQuery.ajax( {
			url : window.BIZCTX_PATH + "/userLoginJson/userLogin.action", // 请求路径
			data : {
				"user.loginname" : username,
				"user.password" : password,
				"user.veryCode":vcode
			}, // 参数
			async:false,
			type : "post", // 请求类型，默认为get
			success : function(data) {
				var resp = data.result;
				var su = resp.su;
					if (su == -1) {
						$("#error_tip").text("验证码不正确请重新输入!");
						$('#vcode').focus();
						$('#vcode').val("");
						refresh();
					}else if (su == -2) {
						$("#error_tip").text("用户名密码不正确!");
						refresh();
					}else if (su == 0) {
						$("#error_tip").text("用户已被停用,请与管理员联系!");
						refresh();
					}else {
						window.location.href = window.BIZCTX_PATH  + '/main.jsp';
					}
			}
		});  	  
		
}
