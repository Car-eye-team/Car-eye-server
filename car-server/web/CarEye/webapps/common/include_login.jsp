<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String ver = "1.2";
%>

<script type="text/javascript">
	    window.BIZCTX_PATH   = '<%=path%>';
	    window.flage = true;
</script>
<link rel="shortcut icon" href="<%=path%>/favicon.ico" /> 
<link href="<%=path%>/resource/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/resource/sweetalert/sweetalert.min.js"></script>
