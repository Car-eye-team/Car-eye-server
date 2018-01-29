<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<head>
<title>车辆管理管理系统-顺风车订单管理</title>

        <script type="text/javascript" src="<%=path%>/taxi/transaction/rideorder/rideorder.js"></script>
		


<!--百度地图-->

<script type="text/javascript" src="<%=BAIDU_MAP_URL%>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/taxi/transaction/rideorder/ridemap.js"></script>
<link rel="stylesheet" type="text/css" href="<%=MAPBAIDU_TRAFFIC_CSS_URL %>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=MAPBAIDU_TRAFFIC_JS_URL %>"></script>
<script type="text/javascript" src="<%=MAPBAIDU_DISTANCETOOL_URL %>"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/RectangleZoom/1.2/src/RectangleZoom_min.js"></script>

<script type="text/javascript" src="<%=path%>/resource/js/baiduMap.js?v=<%=ver%>"></script>

<!-- 高德 -->
<script type="text/javascript" src="<%=GAODE_MAP_URL%>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/gaodeMap.js?v=<%=ver%>"></script>

<style>
			.amap-scale-edgeleft, .amap-scale-edgeright {
			    border: 1px solid #333;
			    height: 6px;
			    width: 1px;
			}
			.amap-scale-edgeleft, .amap-scale-edgeright, .amap-scale-middle {
			    background-color: #333;
			    overflow: hidden;
			    position: absolute;
			}
			.amap-scale-middle {
			    border-bottom: 1px solid #333;
			    border-top: 1px solid #333;
			    height: 2px;
			    left: 2px;
			    top: 2px;
			}
		</style>
</head>

<body>
</body>
</html>
