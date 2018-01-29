package com.careye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careye.common.domain.EntityTreeUtil;
import com.careye.common.domain.MenuEntry;
import com.careye.common.service.MenuTreeService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;

public class AuthorityMenu extends HttpServlet {
	
	private MenuTreeService menuTreeService;
	private List<MenuEntry> menuList;
	private String menutree;
	
	/**
	 * Constructor of the object.
	 */
	public AuthorityMenu() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.reset();// 清空输出流   
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");   
		
		try{
			
			HttpSession session = request.getSession(true);
			BlocUser user = (BlocUser)session.getAttribute(WebConstants.LOGIN_USER);
			if(user == null){
				return;
			}
			menuTreeService = (MenuTreeService)BeanLocator.getInstance().getBean("menuTreeService");
			Integer groupid = user.getBlocgroupid();
			if(user.getLoginname() != null && "admin".equals(user.getLoginname())){
				groupid = null;
			}
			menuList = menuTreeService.getMenuList(groupid);
			menutree =  EntityTreeUtil.getAuthorityTreeString(menuList);
			menutree = menutree.replace(",\"menu\":\u005B]","");
			
			PrintWriter out = response.getWriter();
			out.println(menutree);
			out.flush();
			out.close();
			
		}catch(Exception e){
			PrintWriter out = response.getWriter();
			out.println("[{text: '系统加载错误，请重试。。。', id:1,expanded: true}]");
			out.flush();
			out.close();
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
