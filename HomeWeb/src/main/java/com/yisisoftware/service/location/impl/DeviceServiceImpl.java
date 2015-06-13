package com.yisisoftware.service.location.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.HandheldMachineInfo;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.location.IDeviceServiceI;

@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<HandheldMachineInfo>
		implements IDeviceServiceI {

}
