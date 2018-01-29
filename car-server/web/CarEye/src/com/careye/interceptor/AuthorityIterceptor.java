package com.careye.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityIterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {

		Map<?, ?> session = ActionContext.getContext().getSession();

		Object admin = session.get("userInfo");
		return invocation.invoke();
//		if (admin == null){
//			return "json";
//		}else{
//			return invocation.invoke();
//		}
	}

}
