package com.yisisoftware.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 编码拦截器
 * 
 * @author Administrator
 *
 */
public class EncodingInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(EncodingInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		return invocation.invoke();
	}

}
