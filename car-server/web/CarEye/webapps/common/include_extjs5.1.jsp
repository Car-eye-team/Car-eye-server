<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.careye.constant.ConfigProperties" %>
<%@ page import="com.careye.constant.WebConstants" %>
<%@ page import="com.careye.system.domain.BlocUser" %>
  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String ver = "1.0";
	
%>

<script type="text/javascript">
	    window.BIZCTX_PATH = "<%=path%>";
		var bizctxpath = "<%=path%>";
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

<link rel="stylesheet" type="text/css" href="<%=path%>/ext-5.1.0/app.css?v=<%=ver%>" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css?v=<%=ver%>" />

<!--Extjs-->
<link rel="stylesheet" type="text/css" href="<%=path%>/ext-5.1.0/build/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css?v=<%=ver%>" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/ext-5.1.0/build/ext-all.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext-5.1.0/build/packages/ext-locale/build/ext-locale-zh_CN.js?v=<%=ver%>"></script>


<script type="text/javascript" src="<%=path%>/ext-5.1.0/datetime/UX_TimePickerField.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext-5.1.0/datetime/UX_DateTimePicker.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext-5.1.0/datetime/UX_DateTimeMenu.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext-5.1.0/datetime/UX_DateTimeField.js?v=<%=ver%>"></script>


<script type="text/javascript" src="<%=path%>/ext-5.1.0/TabCloseMenu.js?v=<%=ver%>"></script>
<script type="text/javascript" src="<%=path%>/ext-5.1.0/ComboBoxTree.js?v=<%=ver%>"></script>

<link rel="shortcut icon" href="<%=path%>/favicon.ico?v=<%=ver%>" /> 

