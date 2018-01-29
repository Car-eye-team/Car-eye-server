<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.*"%>
<%@ page import="com.careye.base.action.Page"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/page/page_min.css">

<%
	OgnlValueStack pageStack = (OgnlValueStack) request.getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);
	Page myPage = (Page) pageStack.findValue("objectPage");
%>
<%if (myPage.getTotalPages() >0){ %>
		<div class="pageboxlist">
			<a href='javascript:jumpPage(1, <%=myPage.getTotalPages()%>)'>首页 </a>
			<% if(myPage.isHasPre()){ %>
				<a class="pageboxlist_page_prev" href='javascript:jumpPage(<%=myPage.getPrePage() %>, <%=myPage.getTotalPages() %>)'>上一页</a>	
			<%}else{%>
				<span class="pageboxlist_page_prev_1" style="color: #CCCCCC;">上一页</span>
			<%} %>
			
			<% if(myPage.isHasNext()){ %>
				<a class="pageboxlist_page_next" href='javascript:jumpPage(<%=myPage.getNextPage() %>, <%=myPage.getTotalPages() %>)'>下一页</a>
			<%}else{%>
				<span class="pageboxlist_page_next_1" style="color: #CCCCCC;">下一页</span>
			<%} %>
			<a href='javascript:jumpPage(<%=myPage.getTotalPages() %>, <%=myPage.getTotalPages() %>)'>末页 </a>
            <span>共<span id="totalPage"><%=myPage.getTotalPages() %></span>页</span>
		</div>
<%} %>
