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
		<div class="pageboxlist">
			<a href='javascript:jumpPage(1, <%=myPage.getTotalPages()%>)'>首页 </a>
			<% if(myPage.isHasPre()){ %>
				<a class="pageboxlist_page_prev" href='javascript:jumpPage(<%=myPage.getPrePage() %>, <%=myPage.getTotalPages() %>)'>上一页</a>	
			<%}else{%>
				<span class="pageboxlist_page_prev_1" style="color: #CCCCCC;">上一页</span>
			<%} %>
			<%if (myPage.getTotalPages() <= 8){ %>
			    <%for(int i=1; i<myPage.getTotalPages()+1; i++){ %>
					<%if(i == myPage.getCurrentPage()){ %>
						<span id="currentPage"><%=myPage.getCurrentPage() %></span>
					<%}else{%>
						<a href='javascript:jumpPage(<%=i %>, <%=myPage.getTotalPages() %>)'><%=i %></a>
					<%} %>
				<%} %>
			<%}else{%>
			    <%if(myPage.getCurrentPage()>4 && myPage.getCurrentPage() < (myPage.getTotalPages()-3)){ %>
					<% for(int a = (myPage.getCurrentPage()-3); a<(myPage.getCurrentPage());a++){%>
						<a href='javascript:jumpPage(<%=a %>, <%=myPage.getTotalPages() %>)'><%=a %></a>	
					<%} %>
					<span id="currentPage"><%=myPage.getCurrentPage() %></span>					
					<% for(int b = (myPage.getCurrentPage()+1); b<(myPage.getCurrentPage()+4);b++){%>
						<a href='javascript:jumpPage(<%=b %>, <%=myPage.getTotalPages() %>)'><%=b %></a>	
					<%} %>
				<%}else if(myPage.getCurrentPage() <= 4){ %>
				<% for(int c = 1; c<8;c++){%>
				       <%if(c == myPage.getCurrentPage()){ %>
							<span id="currentPage"><%=myPage.getCurrentPage() %></span>
						<%}else{%>
							<a href='javascript:jumpPage(<%=c %>, <%=myPage.getTotalPages() %>)'><%=c %></a>
						<%} %>		
					<%} %>
				<%}else if(myPage.getCurrentPage() >= (myPage.getTotalPages()-3)){ %>
					<% for(int d = (myPage.getTotalPages()-7); d< myPage.getTotalPages()+1;d++){%>
						<%if(d == myPage.getCurrentPage()){ %>
							<span id="currentPage"><%=myPage.getCurrentPage() %></span>
						<%}else{%>
							<a href='javascript:jumpPage(<%=d %>, <%=myPage.getTotalPages() %>)'><%=d %></a>
						<%} %>	
					<%} %>
				<%} %>
			<%} %>
			<% if(myPage.isHasNext()){ %>
				<a class="pageboxlist_page_next" href='javascript:jumpPage(<%=myPage.getNextPage() %>, <%=myPage.getTotalPages() %>)'>下一页</a>
			<%}else{%>
				<span class="pageboxlist_page_next_1" style="color: #CCCCCC;">下一页</span>
			<%} %>
			<a href='javascript:jumpPage(<%=myPage.getTotalPages() %>, <%=myPage.getTotalPages() %>)'>末页 </a>
            <span>共<span id="totalPage"><%=myPage.getTotalPages() %></span>页</span>
            <span>第<input type="text" id="pageNumber" value="<%=myPage.getCurrentPage() %>" />页
            </span><a href="javascript:void(0)" onclick="turnPage(<%=myPage.getTotalPages() %>)">确定</a>
		</div>
<%} %>
