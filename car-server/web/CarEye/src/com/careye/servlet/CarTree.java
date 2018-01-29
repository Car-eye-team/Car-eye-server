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
import com.careye.car.domain.CarInfo;
import com.careye.common.domain.EntityTreeUtil;
import com.careye.common.domain.MenuTree;
import com.careye.common.service.MenuTreeService;
import com.careye.component.springhelper.BeanLocator;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.careye.system.domain.UserCar;
import com.careye.utils.DateUtil;
import com.careye.utils.SessionUtils;
import com.careye.utils.StringUtils;

public class CarTree extends HttpServlet {
	
	private MenuTreeService menuTreeService;
	private List<MenuTree> menuList;
	private String menutree;

	/**
	 * Constructor of the object.
	 */
	public CarTree() {
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
			
			//autoload为1是加载，否则延迟加载
			String sdeptid = request.getParameter("deptid");	//此deptid代表双击组织机构取出对应车辆
			String querydeptid = request.getParameter("querydeptid");	//左侧查询组织机构
			String deptname = request.getParameter("deptname");	//部门名称
			String carnumber = request.getParameter("carnumber");	//左侧查询车辆
			String flag = request.getParameter("flag");		//为1代表查询车辆时车辆全部取出，不止取出机构
			
			
			BlocUser user = (BlocUser)session.getAttribute(WebConstants.LOGIN_USER);
			if(user == null){
				return;
			}else{
				
				menuTreeService = (MenuTreeService)BeanLocator.getInstance().getBean("menuTreeService");
				Integer userid = user.getUserid();
				Integer deptid = user.getParentid();
				if(user.getLoginname().equals("admin")){	//超级管理员取出全部
					deptid = 0;
					userid = null;
				}
				
				TreeDomain baseDomain = new TreeDomain();
				
				baseDomain.setUserid(userid);
				if(sdeptid != null && !"".equals(sdeptid)){		//异步加载对应组织机构下面的车辆
					baseDomain.setBlocid(Integer.parseInt(sdeptid));
				}
				if(querydeptid != null && !"".equals(querydeptid)){		
					baseDomain.setQuerydeptid(Integer.parseInt(querydeptid));
				}else{
					String editdeptname = request.getParameter("editdeptname"); //左侧组织机构按组织机构名搜索
					if(editdeptname != null && !editdeptname.equals("") && !editdeptname.equals("null")){
						editdeptname = new String(request.getParameter("editdeptname").getBytes("ISO-8859-1"),"UTF-8");
						Integer eidtdeptid = menuTreeService.getDeptidByName(editdeptname);
						if(eidtdeptid != null){
							baseDomain.setQuerydeptid(eidtdeptid);
						}else{
							baseDomain.setQuerydeptid(1);
						}
					}
				}
				if(carnumber != null && !"".equals(carnumber)&& !"null".equals(carnumber)){		//异步加载对应组织机构下面的车辆
					baseDomain.setCarnumber(URLDecoder.decode(carnumber, "UTF-8").toUpperCase());
				}
				
				//如果用户所在机构不是根机构下面
				Integer rootdeptid = menuTreeService.getRootDeptid();
				if(StringUtils.isEmty(sdeptid)){		//不是异步加载对应组织机构下面的车辆
					if(user.getBlocid().intValue() != rootdeptid.intValue() ){
						baseDomain.setBlocid(user.getBlocid());
					}
				}
				
				String date1 = DateUtil.getSQLDate();
				if(flag != null && "1".equals(flag)){
					menuList = menuTreeService.allCarTreeList(baseDomain);
				}else{
					menuList = menuTreeService.carTreeList(baseDomain);
				}
				String date2 = DateUtil.getSQLDate();
				System.out.println("左侧树开始加载"+ date1);
				System.out.println("左侧树结束加载"+ date2);
				if(sdeptid != null && !"".equals(sdeptid) && menuList.size() >0 ){ //找出父节点下面车辆
					deptid = Integer.parseInt(menuList.get(0).getParentId());
				}
				if(user.getBlocid().intValue() != rootdeptid.intValue()){
					MenuTree rootMenu = new MenuTree();
					rootMenu.setId(user.getBlocid().toString());
					rootMenu.setText(user.getBlocname());
					rootMenu.setParentId(user.getParentid().toString());
					rootMenu.setLeaftype(0);
					
					TreeDomain td = new TreeDomain();
					td.setBlocid(Integer.parseInt(rootMenu.getId()));
					Integer total = menuTreeService.getTotalCars(td);
					Integer inline = menuTreeService.getInlineCars(td);
					Integer longoffline = menuTreeService.getLongOfflineCars(td);
					
					rootMenu.setTotal(total);
					rootMenu.setInline(inline);
					rootMenu.setLongoffline(longoffline);
					if(menuList.size() > 0){
						menuList.add(rootMenu);
					}
				}
				
				Iterator<MenuTree> ite=menuList.iterator();
				while(ite.hasNext()){
					MenuTree m=ite.next();
					if(m.getId().indexOf("C") == -1){		//组织机构
						m.setLeaf(false);
						m.setExpanded(true);
						
						if(sdeptid == null || sdeptid.equals("")){	//统计组织机构下面车辆数量
							if(m.getTotal()!= null && m.getTotal() == 0){
								m.setText(m.getText()+"(总数:0,在线0,长离0)");
							}else{
								m.setText(m.getText()+"(总数:"+m.getTotal()+",在线"+m.getInline()+",离线"+(m.getTotal()-m.getInline()-m.getLongoffline())+",长离"+m.getLongoffline()+")");
							}
							
							if(m.getParentId().equals("0")){
								m.setText(WebConstants.getRootDeptPic(request.getContextPath()) + m.getText());
							}else{
								m.setText(WebConstants.getDeptPic(request.getContextPath()) + m.getText());
							}
						}
						
					}else{		//车辆
						m.setLeaf(true);
						m.setExpanded(false);
						m.setHrefTarget(m.getText());
						if(m.getCarstatus()!= null){
							m.setText(WebConstants.getCarText(request.getContextPath(),m.getText(),m.getCarstatus(), m.getTravelposition(),1));
						}
					}
				}
				
				menutree =  EntityTreeUtil.getTreeJsonString(menuList,2,deptid);
			
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
