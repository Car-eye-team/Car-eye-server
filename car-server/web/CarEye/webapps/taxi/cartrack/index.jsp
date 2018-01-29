<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ include file="/common/include.jsp"%>
	<head>
		<title>当前车辆历史轨迹</title>
		<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=BAIDU_MAP_URL%>"></script>
		<script type="text/javascript" src="<%=path%>/taxi/cartrack/cartrack.js"></script>
		<script type="text/javascript" src="<%=path%>/taxi/cartrack/js/track.js"></script>
		<script type="text/javascript" src="<%=path%>/resource/js/Map.js?v=<%=ver%>"></script>
		
		 <!-- 高德 -->
		<script type="text/javascript" src="<%=GAODE_MAP_URL%>"></script>
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
		<%
			String carnumber = request.getParameter("carnumber");
			if(carnumber != null && !("null").equals(carnumber) && !("").equals(carnumber)){
				carnumber = new String(carnumber.getBytes("ISO-8859-1"),"UTF-8");
			}else{
				carnumber = "";
			}
		%>
		<input type="hidden" id="carnumber" value='<%=carnumber %>' />
		<div id="cartrack"></div>
	</body>
</html>
