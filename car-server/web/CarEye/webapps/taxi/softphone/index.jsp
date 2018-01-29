<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理部标平台-客户来电信息</title>
<script type="text/javascript" src="<%=path%>/taxi/softphone/softphone.js"></script>
<script type="text/javascript" src="<%=path%>/resource/js/Map.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=BAIDU_MAP_URL%>"></script>

</head>

<body>

	<input type="hidden" id="customer_phone" value='<%=request.getParameter("phone") %>'/>

</body>
</html>