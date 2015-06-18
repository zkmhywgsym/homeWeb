package com.yisisoftware.service.user;

import com.yisisoftware.entity.LoginUser;
import com.yisisoftware.service.base.IBaseService;

public interface UserServiceI extends IBaseService<LoginUser> {
	public static final String STATUS_WAIT="0";
	public static final String STATUS_NORMAL="1";
	public static final String STATUS_STOP="2";
}
