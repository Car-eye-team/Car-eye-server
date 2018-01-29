<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理部标平台-车辆在线率分析</title>

<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/taxi/reportforms/onlinerate/onlinerate.js"></script>
<script type="text/javascript" src="<%=path%>/resource/echarts-2.2.7/dist/echarts.js"></script>
<style>
.x-tree-icon {height: 0;width: 0;}
</style>

</head>

<body>

</body>
<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: window.BIZCTX_PATH + '/resource/echarts-2.2.7/dist'
            }
        });
</script>
</html>
