<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.careye.constant.ConfigProperties" %>
<%@ page import="com.careye.constant.WebConstants" %>
<%@ page import="com.careye.system.domain.BlocUser" %>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String ver = "1.0";
	String BAIDU_MAP_URL = ConfigProperties.MAPBAIDU_MAP_URL;
	
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



