package com.yisisoftware.model;

import java.io.Serializable;

import com.yisisoftware.entity.WbAdminUser;
/**
 * 存储session信息
 * 
 * @author Administrator
 *
 */
public class SessionInfo implements Serializable {

	private WbAdminUser wbAdminUser;

	public WbAdminUser getWbAdminUser() {
		return wbAdminUser;
	}

	public void setWbAdminUser(WbAdminUser wbAdminUser) {
		this.wbAdminUser = wbAdminUser;
	}
	
}
