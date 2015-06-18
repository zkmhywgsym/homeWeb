package com.yisisoftware.service.user;

import com.yisisoftware.entity.UserInfo;
import com.yisisoftware.service.base.IBaseService;

public interface UserInfoServiceI extends IBaseService<UserInfo> {
	public UserInfo getInfoByName(String name);
}
