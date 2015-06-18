package com.yisisoftware.service.user.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.UserInfo;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.user.UserInfoServiceI;
import com.yisisoftware.util.base.HqlFilter;

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoServiceI {

	@Override
	public UserInfo getInfoByName(String name) {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#phone_S_EQ", name);
		return findByFilter(hqlFilter).get(0);
	}
	
}
