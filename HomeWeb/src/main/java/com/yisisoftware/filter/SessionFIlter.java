package com.yisisoftware.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.yisisoftware.databaseUtils.DatabaseHolder;
import com.yisisoftware.util.base.ConfigUtil;

/**
 * 用于拦截链接地址
 * 
 * @author jonny
 *
 */
public class SessionFIlter implements Filter{

	private List<String> list = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// 初始化需要拦截的文件夹
		String include = filterConfig.getInitParameter("include");
		if (!StringUtils.isBlank(include)) {
			StringTokenizer st = new StringTokenizer(include, ",");
			list.clear();
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String servletPath = request.getServletPath();

		for (String url : list) {
			if (servletPath.indexOf(url) > -1) {// 需要过滤
//				logger.info("进入session过滤器->访问路径为[" + servletPath + "]");
//				System.out.println(request.getSession().getAttribute(ConfigUtil.getSessionInfoName()));
				if (request.getSession().getAttribute(ConfigUtil.getSessionInfoName()) == null) {// session不存在需要拦截
//					request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
					PrintWriter pw = response.getWriter();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("result", "no login");
					String json = JSON.toJSONString(map);
					pw.write(json);
					pw.flush();
					pw.close();
					return;
				}
				break;
			}
		}
		chain.doFilter(request, response);
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
	}

	@Override
	public void destroy() {
		
	}

}
