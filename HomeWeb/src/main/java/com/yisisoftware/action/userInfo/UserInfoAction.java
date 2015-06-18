package com.yisisoftware.action.userInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.LoginUser;
import com.yisisoftware.entity.UserInfo;
import com.yisisoftware.model.SessionInfo;
import com.yisisoftware.service.user.UserInfoServiceI;
import com.yisisoftware.service.user.UserServiceI;
import com.yisisoftware.util.base.ConfigUtil;
import com.yisisoftware.util.base.HqlFilter;
import com.yisisoftware.util.base.SMSUtils;

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
	 * 用户信息服务
	 */
	@Autowired
	private UserInfoServiceI userInfoService;

	/**
	 * 用户登录验证
	 */
	public void userLogin() {
		String userName = this.getRequest().getParameter("loginName"); // 获得用户名	//
		String userPassWord = this.getRequest().getParameter("loginPwd"); // 获得密码；//
		String deviceId = this.getRequest().getParameter("deviceId"); // 获得密码；//
		logger.info("用户"+userName+"开始登陆");
		Map<String, String> map = new HashMap<>();

		try {
			if (StringUtils.isNotEmpty(userName)
					&& StringUtils.isNotEmpty(userPassWord)) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#adminUserName_S_EQ", userName);
				hqlFilter.addFilter("QUERY_t#adminPassWord_S_EQ", userPassWord);

				List<LoginUser> list = userService.findByFilter(hqlFilter);
				if (list != null && list.size() > 0) {
					LoginUser user=list.get(0);
					if(UserServiceI.STATUS_WAIT.equals(user.getStatus())){
						map.put("loginStatus", "waiting");
						return;
					}
					if(UserServiceI.STATUS_STOP.equals(user.getStatus())){
						map.put("loginStatus", "stopped");
						return;
					}
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setUser(list.get(0));
					getSession().setAttribute(ConfigUtil.getSessionInfoName(),
							sessionInfo);
					user.setAdminLastDate(new Date());
					userService.saveOrUpdate(user);
					// this.getSessionMapb().put(ConfigUtil.getSessionInfoName(),
					// sessionInfo);
					// System.out.println("action: " +
					// getSession().getAttribute(ConfigUtil.getSessionInfoName()));
					// System.out.println("action sessionName: " +
					// ConfigUtil.getSessionInfoName());
					// 登录成功
					map.put("loginStatus", "success");
					HqlFilter hqlFilter2 = new HqlFilter();
					hqlFilter2.addFilter("QUERY_t#phone_S_EQ", userName);
					UserInfo info=userInfoService.findByFilter(hqlFilter2).get(0);
					info.setLastLoginTime(new Date());
					userInfoService.save(info);
					//保存设备名称
					if(!StringUtils.isEmpty(deviceId)){
						getSession().setAttribute("deviceId", deviceId);
					}
					logger.info("用户"+userName+"登陆成功");
				} else {
					// 登录失败
					logger.info("用户"+userName+"登陆失败：原因是用户名密码不对应");
					map.put("loginStatus", "userOrPwdErr");
				}
			} else {
				// 未输入用户名或密码
				logger.info("用户登陆失败：原因是用户名密码或密码为空");
				map.put("loginStatus", "other");
			}
		} catch (Exception e) {
			// 登录异常 发生未知异常时返回
			logger.error("登陆时系统出现异常：");
			e.printStackTrace();
			map.put("loginStatus", "other");
		} finally {
			writeJson(map);
		}
	}

	/**
	 * 用户注销
	 */
	public void userdestory() {
		if (getSession() != null) {
			getSession().invalidate();
		}
		Map<String, String> map = new HashMap<>();
		map.put("destory", "success");
		writeJson(map);
	}

	public void getInfo(){
		String pwd=getRequest().getParameter("pwd");
		SessionInfo session=(SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(session==null){
			writeJson("false");
			return;
		}
		if(session.getUser().getAdminPassWord().equals(pwd)){
			UserInfo userInfo=userInfoService.getInfoByName(session.getUser().getAdminUserName());
			writeJson(userInfo);
		}else{
			writeJson("userOnPwdErr");
		}
	}
	public void modifyInfo(){
		try {
			String json=getRequest().getParameter("userInfo");
			String pwd=getRequest().getParameter("pwd");
			String oldPwd=getRequest().getParameter("oldPwd");
			//获取参数
			UserInfo userInfo=JSON.parseObject(json,UserInfo.class);
			if(userInfo==null){
				writeJson("false");
				return;
			}
			//获取session用户
			SessionInfo info=(SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			LoginUser user;
			if(info==null){//session为空
				writeJson("false");
				return;
			}
			user=info.getUser();
			if(user==null){//user为空
				writeJson("false");
				return;
			}
			//密码错误
			if(StringUtils.isEmpty(oldPwd)||!oldPwd.equals(user.getAdminPassWord())){
				writeJson("false");
				return;
			}
			//有密码时修改原密码
			if(!StringUtils.isEmpty(pwd)){
				System.out.println("old:"+oldPwd+";pwd:"+pwd+";sessionPwd:"+user.getAdminPassWord());
				user.setAdminPassWord(pwd);
				userService.saveOrUpdate(user);
				info.setUser(user);
				getSession().setAttribute(ConfigUtil.getSessionInfoName(), info);
			}
			userInfoService.saveOrUpdate(userInfo);
			writeJson("true");
		} catch (Exception e) {
			writeJson("false");
		}
	}
	/**
	 * 
	 */
	public void regUserInfo() {
		logger.info("用户注册");
		String userName = this.getRequest().getParameter("loginName"); // 获得用户名
		String userPassWord = this.getRequest().getParameter("loginPwd"); // 获得密码；
		String checkCode = this.getRequest().getParameter("checkCode"); // 获得密码；//
		System.out.println(checkCode);
		if(StringUtils.isEmpty(checkCode)){
			writeJson("checkCodeErr");
			logger.info("用户注册校验码为空");
			return;
		}
		String res=SMSUtils.getInstance().requestData(userName, checkCode, "86");
		System.out.println("checkCodeRes:"+res);
		if(!res.contains("200")){
			writeJson("checkCodeErr");
			logger.info("用户注册校验码无效");
			return;
		}
		String result = "false"; // 设置返回状态 true ,false,exists
		if (StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(userPassWord)) {
			try {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#adminUserName_S_EQ", userName);
				List<LoginUser> list = userService.findByFilter(hqlFilter);
				if (list.size() > 0) { // 如果手机号注册过
					result = "exists";
					logger.info("用户重复注册");
				} else {
					LoginUser user = new LoginUser();
					user.setAdminUserName(userName);
					user.setAdminPassWord(userPassWord);
					user.setAdminDate(new Date());
					user.setAdminLastDate(new Date());
					result = "true";
					HqlFilter hqlFilter2 = new HqlFilter();
					hqlFilter2.addFilter("QUERY_t#phone_S_EQ", userName);
					UserInfo info=null;
					List<UserInfo> infos=userInfoService.findByFilter(hqlFilter2);
					if(infos!=null&&infos.size()>0){
						info=infos.get(0);
					}else{
						info=new UserInfo();
						info.setId(0);
					}
					info.setCreateDate(new Date());
					info.setLastLoginTime(new Date());
					info.setPhone(userName);
					userInfoService.saveOrUpdate(info);
					userService.save(user);
					logger.info("用户注册成功");
				}

			} catch (Exception e) {
				e.printStackTrace();
				result = "false";
				logger.info("用户注册异常");
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		writeJson(map);
	}

	/**
	 * 
	 */
	public void userReset() {
		String userName = this.getRequest().getParameter("loginName"); // 获得用户名
		String userPassWord = this.getRequest().getParameter("loginPwd"); // 获得密码；
		String checkCode = this.getRequest().getParameter("checkCode"); // 获得密码；//
		if(StringUtils.isEmpty(checkCode)){
			writeJson("checkCodeErr");
			return;
		}
		if(!SMSUtils.getInstance().requestData(userName, checkCode, "86").contains("200")){
			writeJson("checkCodeErr");
			return;
		}
		String result = "false"; // 设置返回状态 true ,false,exists
		if (StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(userPassWord)) {
			try {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#adminUserName_S_EQ", userName);
				List<LoginUser> list = userService.findByFilter(hqlFilter);
				if (list.size() > 0) { // 如果手机号注册过
					LoginUser user = list.get(0);
					user.setAdminPassWord(userPassWord);
					userService.save(user);
					result = "true";
				} else {
					result = "notExists";
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = "false";
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("result", result);
		writeJson(map);
	}
}
