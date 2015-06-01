package com.yisisoftware.model;

import java.io.Serializable;

import com.yisisoftware.entity.LoginUser;

/**
 * 存储session信息
 * 
 * @author Administrator
 *
 */
public class SessionInfo implements Serializable {

	private LoginUser user;

	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser User) {
		this.user = User;
	}
	
}
