/**
 * 
 */
package com.careye.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author zhangrong
 *从session中获取值时，要保证web容器启动
 */
public class SessionUtils {
	
	
	/**
	 * 从session中获取值
	 * @param sessionKey
	 * @return
	 */
	public static Object getUserSession(String sessionKey) {
		
		return ServletActionContext.getRequest().getSession().getAttribute(sessionKey);
		
	}
	
	/**
	 * 从session中取出user对象
	 * @return
	 */
	public static BlocUser getUser() {
		return (BlocUser) getUserSession(WebConstants.LOGIN_USER);
	}
	
	public static Integer getUserId() {
		return getUser() == null ? null : getUser().getId();
	}
	
	public static Integer getBlocId() {
		return getUser() == null ? null : getUser().getBlocid();
	}
	
	/**
	 * 更新session中的user对象
	 */
	public static void updateUser(BlocUser user) {
		put(WebConstants.LOGIN_USER, user);
	}
	
	/**
	 * 将键值对存入session
	 * @param sessionKey
	 * @param value
	 */
	public static void put(String sessionKey, Object value){
		ServletActionContext.getRequest().getSession().setAttribute(sessionKey, value);
	}
	
	/**
	 * 清除session中指定key的属性
	 * @param sessionKey
	 */
	public static void invalidateAttribute(String sessionKey) {
		getSession().removeAttribute(sessionKey);
	}
	
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 放入值栈
	 * @param key
	 * @param value
	 */
	public static  void putInValueStack(String key, Object value) {
		ActionContext.getContext().getValueStack().set(key, value);
	}
	
	/**
	 * 得到请求客户端Ip
	 * @return
	 */
	public static String getRemoteRequestIp() {
			HttpServletRequest request = ServletActionContext.getRequest();
		
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip;
	}

}
