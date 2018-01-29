<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理运营管理平台-查看视频</title>

<script type="text/javascript" src="<%=path%>/taxi/vedio_guoji/vedio.js"></script>
<script type="text/javascript" src="<%=path%>/taxi/vedio_guoji/vedioContr.js"></script>
<script type="" src="<%=path%>/resource/js/jquery-1.4.4.min.js" ></script>

<script language='javascript' for=cmsv6 event="RecSearchEvent(strFile, nStartTime,nEndTime,nFileLen,nFileType,nSvrID,nLocation,nChannel)">
	
</script>

</head>

<body>
	
	<input type="hidden" id="terminal" value = "<%=request.getParameter("terminal") %>"/>

</body>


</html>
