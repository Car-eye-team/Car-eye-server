//显示加载器  
function showLoader() {  
    //显示加载器.for jQuery Mobile 1.2.0  
    $.mobile.loading('show', {  
        text: '数据提交中...', //加载器中显示的文字  
        textVisible: true, //是否显示文字  
        theme: 'b',        //加载器主题样式a-e  
        textonly: false   //是否只显示文字  
        //html: ""           //要显示的html内容，如图片等  
    });  
}  
  
//隐藏加载器.for jQuery Mobile 1.2.0  
function hideLoader()  
{  
    //隐藏加载器  
    $.mobile.loading('hide');  
}  

/**
 * 回车事件触发表单提交
 */
$(document).ready(function(){
	$("#phone").focus(); 
});


/**
 * 获取验证码
 */
var count = 60;
function getSmsCode(){
	
	if (count == 0) {
		count = 60;
	}
	if(count != 60)
		return;
	
	var phone = $('#phone').val();
	if(phone.length == 0){
		$("#phone").focus(); 
		swal("失败", "请输入手机号", "error"); 
		return false;
	}
	
	if(phone.length != 11){
		$("#phone").focus(); 
		swal("失败", "输入手机号格式错误", "error"); 
		return false;
	}
	
	var reg = /^1[0-9]{10}$/i;
	if (!reg.test(phone)) {
		$("#phone").focus(); 
		swal("失败", "输入手机号格式错误", "error"); 
		return false;
	}else{
		
		var id = setInterval(function(){
	  	if (count > 0) {
	  		$("#sendcode").html(count+"秒");
	  		$("#sendcode").attr("disabled",true);
	  		count--;
	  	} else if (count == 0) {
	  		clearInterval(id);
	  		$("#sendcode").attr("disabled",false);
	  		$("#sendcode").html("发送验证码"); 
	  	}
	  	
	  	},1000);
		
	} 
	
	jQuery.ajax( {
			url : window.BIZCTX_PATH + "/commonjson/getSmsCode.action", // 请求路径
			data : {
				"phone" : phone
			}, // 参数
			async:false,
			type : "post", // 请求类型，默认为get
			success : function(data) {
				var resp = data.result;
				var su = resp.su;
					if(su == 0){
						$("#smscode").focus(); 
						swal("成功", "验证码发送成功", "success"); 
					}else{
						swal("失败", "验证码发送失败", "error"); 
					}
			}
		}); 
}

function registration(){
	
	var phone = $('#phone').val();
	if(phone.length == 0){
		$("#phone").focus(); 
		swal("失败", "请输入手机号", "error"); 
		return false;
	}
	
	if(phone.length != 11){
		$("#phone").focus(); 
		swal("失败", "输入手机号格式错误", "error"); 
		return false;
	}
	
	var reg = /^1[0-9]{10}$/i;
	if (!reg.test(phone)) {
		$("#phone").focus(); 
		swal("失败", "输入手机号格式错误", "error"); 
		return false;
	}
	
	var re = /^[0-9]+.?[0-9]*$/;
	var smscode = $('#smscode').val();
	if(smscode.length == 0){
		$("#smscode").focus(); 
		swal("失败", "请输入短信验证码", "error"); 
		return false;
	}
	
	if (!re.test(smscode)){
		$("#smscode").val(""); 
		$("#smscode").focus(); 
    	swal("失败", "短信验证码只能为数字", "error");
		return false;
    }
    
    var password = $('#password').val();
    if(password.length == 0){
		$("#password").focus(); 
		swal("失败", "请输入密码", "error"); 
		return false;
	}
    var repassword = $('#repassword').val();
    if(repassword.length == 0){
		$("#repassword").focus(); 
		swal("失败", "请输入重复密码", "error"); 
		return false;
	}
	
	if(password != repassword){
		$("#repassword").focus(); 
		swal("失败", "输入的密码与重复密码不一致", "error"); 
		return false;
	}
	
	var carnumber = $('#carnumber').val();
    if(carnumber.length == 0){
		$("#carnumber").focus(); 
		swal("失败", "请输入车牌号", "error"); 
		return false;
	}
	
	 var cre=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	if(!cre.test(carnumber)){
            $("#carnumber").focus(); 
			swal("失败", "车牌号格式不正确", "error"); 
            return false;
      }
	
	showLoader();
    jQuery.ajax( {
			url : window.BIZCTX_PATH + "/commonjson/userRegister.action", // 请求路径
			data : {
				"phone" : phone,
				"code":smscode,
				"password":password,
				"carnumber":carnumber
			}, // 参数
			async:false,
			type : "post", // 请求类型，默认为get
			success : function(data) {
				hideLoader();
				var resp = data.result;
				var su = resp.su;
					if(su == 0){
					 swal({ 
						  title: "注册成功", 
						  type: "success",
						  confirmButtonText: "确定", 
						  closeOnConfirm: false			
						},
						function(isConfirm){ 
						  if (isConfirm) {
							  window.location.href =window.BIZCTX_PATH+ "/index.jsp";
						  }
						});
					}else if(su == -1){
						$("#smscode").focus(); 
						$('#smscode').val("");
						swal("失败", "验证码不正确", "error"); 
					}else if(su == -2){
						swal("失败", "注册失败,请与管理员联系", "error"); 
					}
			}
		}); 
}

