package com.careye.interceptor;

import java.util.Map;

import com.careye.common.action.UserLoginAction;
import com.careye.constant.WebConstants;
import com.careye.system.domain.BlocUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityJSONIterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	

	public String intercept(ActionInvocation invocation) throws Exception {
		
//		Object obj = invocation.getAction();
//		if(UserLoginAction.class == obj.getClass()){
//			return invocation.invoke();
//		}
//		ActionContext acx = invocation.getInvocationContext();
//		Map session = acx.getSession();
//		User user = (User)session.get(WebConstants.LOGIN_USER);
//		if(user == null){
//			return "index";
//		}else{
//			return invocation.invoke();
//		}
		return invocation.invoke();
	}

}
