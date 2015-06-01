package com.yisisoftware.service.user.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.LoginUser;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.user.UserServiceI;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<LoginUser> implements UserServiceI {

}
