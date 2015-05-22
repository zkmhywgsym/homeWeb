package com.yisisoftware.service.business.inventory.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yisisoftware.entity.WbMaterial;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.business.inventory.WbMaterialServiceI;
import com.yisisoftware.util.base.HqlFilter;

/**
 * 物料服务实现类
 * 
 * @author Administrator
 *
 */
@Service("WbMaterialService")
public class WbMaterialServiceImpl extends BaseServiceImpl<WbMaterial>
		implements WbMaterialServiceI {

}
