<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ page import="org.apache.struts2.ServletActionContext"%>
<%@ page import="java.util.*"%>
<%@ page import="com.careye.base.action.Page"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/page/page.css">

<%
	OgnlValueStack pageStack = (OgnlValueStack) request.getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);
	Page myPage = (Page) pageStack.findValue("page");
%>
<%if (myPage.getTotalPages() >0){ %>
		<div class="pagebox">
			<ul>
				<li>共<span><%=myPage.getTotalPages() %></span>页</li>
				<li>总计<span><%=myPage.getTotalCount() %></span>条记录</li>
				<li>当前第<span><%=myPage.getCurrentPage() %></span>页</li>
				<li><a href='javascript:jumpPage(1, <%=myPage.getTotalPages() %>)'>首页 </a></li>	
				<li>
					<% if(myPage.isHasPre()){ %>
						<a href='javascript:jumpPage(<%=myPage.getPrePage() %>, <%=myPage.getTotalPages() %>)'>上一页 </a>
					<%}else{%>
						<b>上一页</b>	
					<%} %>
				</li>
				<li>
					<% if(myPage.isHasNext()){ %>
						<a href='javascript:jumpPage(<%=myPage.getNextPage() %>, <%=myPage.getTotalPages() %>)'>下一页 </a>
					<%}else{%>
						<b>下一页</b>
					<%} %>
				</li>
				<li>
					<a href='javascript:jumpPage(<%=myPage.getTotalPages() %>, <%=myPage.getTotalPages() %>)'>末页 </a>
				</li>
			</ul>
		</div>
<%} %>
