package com.yisisoftware.service.base.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.TestMapPoint;
import com.yisisoftware.service.base.TestServiceI;

@Service("TestService")
public class TestServiceImpl extends BaseServiceImpl<TestMapPoint>
		implements TestServiceI {

}
