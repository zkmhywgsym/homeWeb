package com.yisisoftware.service.location.impl;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.MapPoint;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.location.ILocationServiceI;

@Service("LocationService")
public class LocationServiceImpl extends BaseServiceImpl<MapPoint>
		implements ILocationServiceI {
	
}
