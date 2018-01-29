package com.careye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careye.common.domain.EntityTreeUtil;
import com.careye.common.domain.MenuTree;
import com.careye.common.service.MenuTreeService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.careye.system.service.UserService;


public class ShowAllRemindTree extends HttpServlet {

	private MenuTreeService menuTreeService;
	private UserService userService;
	private List<MenuTree> menuList;
	private String menutree;
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
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

			//根据选中的用户才取列表，如果需要按当前用户取只需要屏蔽以下四行代码
			menuTreeService = (MenuTreeService)BeanLocator.getInstance().getBean("menuTreeService");
			
			List checkedList = Collections.emptyList();
			String reminduserid = request.getParameter("reminduserid");
			Integer assignuserid = null;
			if(reminduserid != null && !reminduserid.equals("") && !reminduserid.equals("null")){	
				assignuserid = Integer.parseInt(reminduserid);
				checkedList = menuTreeService.getRemindCheckedList(assignuserid);
			}
			
			
			menuList = menuTreeService.remindTreeList();
			for(MenuTree m :menuList){
				if(m.getLeaftype() == 1){		//叶子
					m.setLeaf(true);
					m.setExpanded(false);
				}
			}
			
			for(MenuTree m :menuList){
				if(checkedList.size() > 0 && checkedList.contains(m.getId())){		
					m.setChecked(true);
				}
			}
			menutree =  EntityTreeUtil.getTreeJsonString(menuList, 1, 0);
		
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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
