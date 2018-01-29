package com.careye.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careye.base.action.TreeDomain;
import com.careye.common.domain.EntityTreeUtil;
import com.careye.common.domain.MenuTree;
import com.careye.common.service.MenuTreeService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.constant.WebConstants;
import com.careye.system.domain.Bloc;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.UserCar;
import com.careye.utils.DateUtil;


public class DeptTree extends HttpServlet {
	
	private MenuTreeService menuTreeService;
	private List<MenuTree> menuList;
	private String menutree;

	/**
	 * Constructor of the object.
	 */
	public DeptTree() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.reset();// 清空输出流   
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");   
		
		try{

			HttpSession session = request.getSession(true);
			
			String type = request.getParameter("type");
			String blocname = request.getParameter("bloc_name");	//部门名称
			
			
			BlocUser user = (BlocUser)session.getAttribute(WebConstants.LOGIN_USER);
			if(user == null){
				return;
			}else{
				menuTreeService = (MenuTreeService)BeanLocator.getInstance().getBean("menuTreeService");
				Integer userid = user.getId();
				Integer deptid = user.getParentid();
				if(user.getLoginname().equals("admin")){	//超级管理员取出全部
					deptid = 0;
					userid = null;
				}
				
			   if((type != null && type.equals("200"))){
					Bloc orgazicationBloc = new Bloc();
					if(blocname !=null && !blocname.equals("null")&& !blocname.equals("undefined")){
						orgazicationBloc.setBlocname(URLDecoder.decode(blocname, "UTF-8"));
					}
					orgazicationBloc.setUserid(userid);
					menuList = menuTreeService.deptTreeList(orgazicationBloc);
					if(blocname !=null && !blocname.equals("null")&& menuList.size()>0){
						deptid = Integer.parseInt(menuList.get(0).getParentId());
					}
				}else{
					menuList = menuTreeService.deptTreeList(null);
					deptid = 0;
				}
				Iterator<MenuTree> ite=menuList.iterator();
				while(ite.hasNext()){
					MenuTree m=ite.next();
					m.setLeaf(false);
					m.setExpanded(true);
				}
				
				menutree =  EntityTreeUtil.getTreeJsonString(menuList,2,deptid);
				if(type == null || "200".equals(type)){
					//把选择框去掉
					menutree = menutree.replaceAll(",\"checked\":false","");
				}
			
				PrintWriter out = response.getWriter();
				out.println(menutree);
				out.flush();
				out.close();
			}
		
		}catch(Exception e){
			PrintWriter out = response.getWriter();
			out.println("[{text: '系统加载错误，请重试。。。', id:1,expanded: true}]");
			out.flush();
			out.close();
			e.printStackTrace();
		}
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
