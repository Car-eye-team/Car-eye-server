<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/include_login.jsp" %>
<head>
<title>车辆管理平台-用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="车联网,车联网系统,车联网产品" />
<meta name="description" content="车辆管理平台" />
<link href="<%=path%>/userregister/css/index.css?v=<%=ver%>" rel="stylesheet" type="text/css" />
<link href="<%=path%>/resource/mobile/jquery.mobile-1.4.5.min.css?v=<%=ver%>"" rel="stylesheet" type="text/css" />
<link href="<%=path%>/resource/mobile/jquery.mobile.structure-1.4.5.min.css?v=<%=ver%>"" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="main_middle_div">
	     <div class="info_middle">
		     <div class="info_middle_div1">
				 <div class="info_middle_div2"  id="middle_content">
					 
					<div class="middle_top">
						<ul>
						   <li class="current_li">填写注册信息</li>
						</ul>
					</div>

					<div class="middle_content">
						
						      <div class="a">
								 <div class="yhm" >手机号:</div>
								 <input type="text" value="" id="phone"/>
							  </div>
						   
							  <div class="a a03">
								 <div class="pa">
									<div class="yhm">验证码:</div>
									<input type="text" value="" id="smscode"/>
								 </div>
								 <div class="yanzm">
									<a  onclick="getSmsCode();"><div class="checkcode" id="sendcode">获取验证码</div></a>								 </div>
							  </div>
							  
							  <div class="a">
								 <div class="yhm" >密码:</div>
								 <input type="password" value="" id="password"/>
							  </div>
							  
							   <div class="a">
								 <div class="yhm" >重复密码:</div>
								 <input type="password" value="" id="repassword"/>
							  </div>
							  
							  
							  <div class="a">
								 <div class="yhm" >车牌号:</div>
								 <input type="text" value="" id="carnumber"/>
							  </div>
							  
							  <div>
							  	<a class="a04" onclick="registration()">提交注册</a>	
							  </div>
				    </div>
				 </div>
			 </div>
		 </div>
	 </div>
   
</body>
</html>
<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/userregister/js/index.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/resource/mobile/jquery.mobile-1.4.5.min.js?v=<%=ver%>"></script>
