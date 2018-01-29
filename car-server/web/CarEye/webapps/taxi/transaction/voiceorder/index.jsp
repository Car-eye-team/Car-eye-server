<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理管理系统-语音订单业务管理</title>

        <script type="text/javascript" src="<%=path%>/taxi/transaction/voiceorder/voiceorder.js"></script>
        <script type="text/javascript" src="<%=path%>/resource/js/Map.js?v=<%=ver%>"></script>
		<script type="text/javascript" src="<%=BAIDU_MAP_URL%>"></script>
	<!--播放背景音乐组件-->
<script type="text/javascript" src="<%=path%>/resource/js/soundmanager2.js"></script>
</head>

<body>
   <input type="hidden" id="basepath" value="<%=basePath%>"/>
   <embed  id='voiceOrder' hidden=true/> 
</body>

<script type="text/javascript">
     soundManager.setup({
       	 url: window.BIZCTX_PATH + '/resource/swf', //swf文件夹的位置
  	     onready: function() {
         }
      });
</script>
</html>
