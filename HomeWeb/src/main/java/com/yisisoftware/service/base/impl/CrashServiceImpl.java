package com.yisisoftware.service.base.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.Crash;
import com.yisisoftware.service.base.ICrashServiceI;

@Service("crashService")
public class CrashServiceImpl extends BaseServiceImpl<Crash>
		implements ICrashServiceI {

}
