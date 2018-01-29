<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/include_login.jsp" %>
<head>
<title>车辆管理平台</title>

<link href="<%=path%>/css/index.css?v=1.1" rel="stylesheet" type="text/css" />

<!--[if IE]>
	<style type="text/css">
        .yhm {
            color : black
        }
        .a04 {
            width : 240px;
            border-radius:15px;
        }
        .a01 INPUT{
        	height: 20px;
        }
        .a01 #username{
        	margin-top:6px;
        }
        .a01 #password{
        	margin-top:3px;
        }
        .input2{
        	margin-top:6px;
        }
        #error_tip{
        	color:red;
        	text-align:center;
        }
    </style>
<![endif]-->

</head>

<body>

<div class="photo">
   <img src="images/big_bj_01.png">
   <img src="images/big_bj_02.png">
   <img src="images/big_bj_03.png">
   <img src="images/big_bj_04.png">
   <img src="images/big_bj_05.png">
</div>





<div class="bigbj">
     <div class="denglu">用户登录</div>
   <div class="logo"><img src="images/che.png"></div>
  <div class="kk">
  
	<div id="error_tip" class="error_tip_align_center" ></div>
   <div class="a01">
   		<div class="yhm">用户名:</div>
   		<input class="input1" type="text" value="" id="username"/>
   </div>
   <div class="a01 a02">
   		<div class="yhm">密  &nbsp;&nbsp;码:</div>
   		<input class="input1"  type="password" value="" id="password"/>
   	</div>
   
   <div class="wx">
   		<div class="a01 a03">
   			<div class="yhm">验证码:</div>
   			<input class="input2"  type="text" value="" id="vcode"/>
   		</div>
    <div class="yanzm">
    	<img id="authImg" width='72' height="28" src="<%=path%>/AuthImg" onclick="refresh();"  alt="看不清？点击刷新" style="cursor:pointer;"/>
    </div>
    </div>
    
   <div><a class="a04" href="javascript:void(0)" onclick="userlogin()" style="cursor:pointer;">登录</a></div>
   
   <div class="a05">
   		<a href="vedio.zip" target="_blank">视频插件下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=path%>/common/register.action" target="_blank">在线注册</a>
   </div>
   
  </div>
   
 </div>
<!-- &nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.shenghong-technology.com/" target="_blank">晟鸿科技有限公司</a> -->
<div class="links">友情链接:<a href="https://github.com/Car-eye-admin" target="_blank">车眼开源平台</a></div>

</body>

<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js?v=1.0"></script>
<script type="text/javascript" src="<%=path%>/resource/js/login.js?v=1.0"></script>
</html>
