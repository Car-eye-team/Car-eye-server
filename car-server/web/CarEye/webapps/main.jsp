<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/include_main.jsp" %>
<head>
<title>车辆管理平台</title>

<script type="text/javascript" src="<%=path%>/common/mainpage.js"></script>
<script type="text/javascript" src="<%=path%>/app.js"></script>

<style>
.x-tree-icon {height: 0;width: 0;}
.amap-info-content{width:400px !important;}


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

<!--百度地图-->

<link rel="stylesheet" type="text/css" href="<%=MAPBAIDU_TRAFFIC_CSS_URL %>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=MAPBAIDU_TRAFFIC_JS_URL %>"></script>
<script type="text/javascript" src="<%=MAPBAIDU_DISTANCETOOL_URL %>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/baiduMap.js?v=<%=ver%>"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/RectangleZoom/1.2/src/RectangleZoom_min.js"></script>
<script type="text/javascript" src="<%=path%>/taxi/car/areaset/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>

<script type="text/javascript" src="<%=path%>/resource/js/jquery-1.4.4.min.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/taxicookie.js?v=<%=ver%>"></script>

<!--flex通讯组件-->
<script type="text/javascript" src="<%=path%>/common/swf/flexInterface-1.0.1.js"></script>
<script type="text/javascript" src="<%=path%>/common/swf/flexCallBackInterface-1.0.1.js"></script>

<!-- 深海捷电话对接接口 SOFT_PHONE_URL-->
<script type="text/javascript" src="<%=path%>/common/phoneswf/seatphone.js"></script>
<script type="text/javascript" src="<%=SOFT_PHONE_URL%>/admin/?m=interface&c=index&a=init"></script>
<script type="text/javascript" src="<%=SOFT_PHONE_URL%>/admin/?m=interface&c=api&a=popscreen&extension=<%=user==null?"":user.getRunnumber()%>&pop_url=&pop_typ=LINK&pop_out=DialIn&open_type=2&mixcallback=belling"></script>
<script type="text/javascript" src="<%=SOFT_PHONE_URL%>/admin/?m=interface&c=api&a=command"></script>


<!-- 高德 -->
<script type="text/javascript" src="<%=GAODE_MAP_URL%>"></script>
<script type="text/javascript" src="<%=path%>/resource/js/gaodeMap.js?v=<%=ver%>"></script>

<!--播放背景音乐组件-->
<script type="text/javascript" src="<%=path%>/resource/js/soundmanager2.js"></script>

<!--Javascript检测Flash插件是否安装及版本号-->
<script type=text/javascript>
	var i_flash;
	var v_flash;
	// Netscape
	if (navigator.plugins) {
		for (var i=0; i < navigator.plugins.length; i++) {
			if (navigator.plugins[i].name.toLowerCase().indexOf("shockwave flash") >= 0) {
				i_flash = true;
				v_flash = navigator.plugins[i].description.substring(navigator.plugins[i].description.toLowerCase().lastIndexOf("flash ") + 6, navigator.plugins[i].description.length);
			}
		}
	}
</script>
<script type=text/vbscript>
//IE
on error resume next
set f = CreateObject("ShockwaveFlash.ShockwaveFlash")
if IsObject(f) then
i_flash = true
v_flash = hex(f.FlashVersion())
end if
</script>


<script type=text/javascript>
<!--
	if (!i_flash) {
		if(window.confirm('您的系统没有安装Flash插件,将会影响车辆位置信息实时刷新,请确认是否下载安装?')){
			window.open(window.BIZCTX_PATH + "/install_flash_player.exe");
         }
	}
// -->
</script>

<script type="text/javascript">
     soundManager.setup({
       	 url: window.BIZCTX_PATH + '/resource/swf', //swf文件夹的位置
  	     onready: function() {
         }
      });
</script>


</html>
