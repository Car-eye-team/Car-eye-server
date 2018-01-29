<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.careye.constant.ConfigProperties" %>
<%@ page import="com.careye.constant.WebConstants" %>
<%@ page import="com.careye.system.domain.BlocUser" %>
  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String ver = "1.0";
	//百度地图相关接口
	String BAIDU_MAP_URL = ConfigProperties.MAPBAIDU_MAP_URL;
	String MAPBAIDU_TRAFFIC_CSS_URL = ConfigProperties.MAPBAIDU_TRAFFIC_CSS_URL;
	String MAPBAIDU_TRAFFIC_JS_URL = ConfigProperties.MAPBAIDU_TRAFFIC_JS_URL;
	String MAPBAIDU_DISTANCETOOL_URL = ConfigProperties.MAPBAIDU_DISTANCETOOL_URL;
	
	//高德地图相关接口
	String GAODE_MAP_URL = ConfigProperties.MAPGAODE_MAP_URL;
	//地图类型(1百度 2高德)
	String MAP_TYPE = ConfigProperties.MAP_TYPE;
%>

<script type="text/javascript">
	    window.BIZCTX_PATH = "<%=path%>";
		var bizctxpath = "<%=path%>";
		var poipagesize = 20;
		 var maptype = "<%=MAP_TYPE%>";
</script>
<script type="text/javascript">
 validator : function vd(text){   
          if(this.allowBlank==false && text.replace(/(^\s*)|(\s*$)/g, "").length==0)      
                return "不能全部为空格";       
             else       
               return true;   
           };
</script>
<%
    HttpSession s = request.getSession(); 
    BlocUser user = (BlocUser)s.getAttribute(WebConstants.LOGIN_USER);
	if(user == null){
%>
<script type="text/javascript">
<!--
    top.window.location.href = window.BIZCTX_PATH+"/index.jsp";
//-->
</script>

<%}else{ %>
	<script type="text/javascript">
	    try{
			var admin = {
				id : "<%=user.getId()%>",
				blocid : "<%=user.getBlocid()%>",
				username : "<%=user.getLoginname()%>",
				name : "<%=user.getUsername()%>"
			};
		}catch(e){}

	</script>
<%} %>

<!--Extjs-->
<link rel="stylesheet" type="text/css" href="<%=path%>/ext4/resources/css/ext-all.css?v=<%=ver%>" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css?v=<%=ver%>" />
<link rel="stylesheet" type="text/css" href="<%=path%>/ext4/resources/app.css?v=<%=ver%>" />
<script type="text/javascript" src="<%=path%>/ext4/ext-all.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/locale/ext-lang-zh_CN.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/ComboBoxTree.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_TimePickerField.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimePicker.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimeMenu.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext4/datetime/UX_DateTimeField.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/common/js/common.js?v=<%=ver%>"></script>

<link rel="shortcut icon" href="<%=path%>/favicon.ico?v=<%=ver%>" /> 

