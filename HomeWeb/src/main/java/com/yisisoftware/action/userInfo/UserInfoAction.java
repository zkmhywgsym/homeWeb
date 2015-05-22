package com.yisisoftware.action.userInfo;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.WbAdminUser;
import com.yisisoftware.model.SessionInfo;
import com.yisisoftware.service.user.UserServiceI;
import com.yisisoftware.util.base.ConfigUtil;
import com.yisisoftware.util.base.HqlFilter;

@Controller
public class UserInfoAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserInfoAction.class);

	/**
	 * 用户信息服务
	 */
	@Autowired
	private UserServiceI userService;
	
	/**
	 * 用户登录验证
	 */
	public void userLogin(){
		String userName = this.getRequest().getParameter("loginName");	//获得用户名
		String userPassWord = this.getRequest().getParameter("loginPwd");	//获得密码；
		Map<String, String> map = new HashMap<>();
		
		try {
			if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userPassWord)){
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#adminUserId_S_EQ", userName);
				hqlFilter.addFilter("QUERY_t#adminPassWord_S_EQ", userPassWord);
				
				List<WbAdminUser> list= userService.findByFilter(hqlFilter);
				if(list != null && list.size() > 0){
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setWbAdminUser(list.get(0));
					getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
//					this.getSessionMapb().put(ConfigUtil.getSessionInfoName(), sessionInfo);
//					System.out.println("action: " + getSession().getAttribute(ConfigUtil.getSessionInfoName()));
//					System.out.println("action sessionName: " + ConfigUtil.getSessionInfoName());
					//登录成功
					map.put("loginStatus", "success");
				}else{
					//登录失败
					map.put("loginStatus", "userOrPwdErr");
				}
			}else{
				//未输入用户名或密码
				map.put("loginStatus", "other");
			}
		} catch (Exception e) {
			// 登录异常 发生未知异常时返回
			logger.error("系统出现异常："+e);
			map.put("loginStatus", "other");
		}finally{
			writeJson(map);
		}
	}
	
	/**
	 * 用户注销
	 */
	public void userdestory(){
		if (getSession() != null) {
			getSession().invalidate();
		}
		Map<String, String> map = new HashMap<>();
		map.put("destory", "success");
		writeJson(map);
	}
	
	/**
	 * 
	 */
	public void regUserInfo(){
		
	}
}
