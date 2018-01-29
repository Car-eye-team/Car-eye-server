<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理系统-营运时长分析</title>

<script type="text/javascript" src="<%=path%>/taxi/reportforms/operatimeanalysis/ota.js"></script>
<script src="<%=path%>/resource/echarts-2.2.7/dist/echarts.js"></script>
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
