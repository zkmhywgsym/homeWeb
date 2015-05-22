package com.yisisoftware.service.business.inventory.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.WbCars;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.business.inventory.WbCarsServiceI;
import com.yisisoftware.util.base.HqlFilter;
/**
 * 车牌信息实现类
 * 
 * @author Administrator
 *
 */
@Service("WbCarsService")
public class WbCarsServiceImpl extends BaseServiceImpl<WbCars> implements
		WbCarsServiceI {

}
