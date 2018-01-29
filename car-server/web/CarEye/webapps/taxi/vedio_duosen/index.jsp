<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include_main.jsp" %>
<head>
<title>车辆管理运营管理平台-查看视频</title>
<script type="text/javascript" src="<%=path%>/taxi/vedio_duosen/vedioContr.js"></script>
<style>
	#EasyPlayerOcx1{z-index: -1;}
</style>

</head>

<body>
	
	<input type="hidden" id="terminal" value = "<%=request.getParameter("terminal") %>"/>
	
	<!--flex通讯组件-->
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		id="taxiFlex" width="0" height="0"
		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
		<param name="movie" value="<%=path%>/common/swf/TAXIFLEX.swf" />
		<param name="quality" value="high" />
		<param name="bgcolor" value="#869ca7" />
		<param name="allowScriptAccess" value="sameDomain" />
		<embed src="<%=path%>/common/swf/TAXIFLEX.swf" quality="high"
			bgcolor="#869ca7" width="0" height="0" name="taxiFlex"
			align="middle" play="true" loop="false" quality="high"
			allowScriptAccess="sameDomain" type="application/x-shockwave-flash"
			pluginspage="http://www.macromedia.com/go/getflashplayer"> </embed>
	</object>

</body>

<script type="text/javascript" src="<%=path%>/taxi/vedio_duosen/vedio.js"></script>

<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/taxicookie.js?v=<%=ver%>"></script>

<!--flex通讯组件-->
<script type="text/javascript" src="<%=path%>/common/swf/flexInterface-1.0.1.js"></script>
<script type="text/javascript" src="<%=path%>/common/swf/flexCallBackInterface-1.0.1.js"></script>

<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_TimePickerField.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimePicker.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimeMenu.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimeField.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/common/js/common.js?v=<%=ver%>"></script>


</html>
