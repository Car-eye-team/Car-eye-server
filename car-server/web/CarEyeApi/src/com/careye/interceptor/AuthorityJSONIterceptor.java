package com.careye.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityJSONIterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	

	public String intercept(ActionInvocation invocation) throws Exception {
		
		return invocation.invoke();
	}

}
