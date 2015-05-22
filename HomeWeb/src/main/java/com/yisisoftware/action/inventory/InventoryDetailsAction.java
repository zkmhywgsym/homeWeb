package com.yisisoftware.action.inventory;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.WbCars;
import com.yisisoftware.entity.WbCustomer;
import com.yisisoftware.entity.WbMaterial;
import com.yisisoftware.entity.WbSupply;
import com.yisisoftware.entity.WbWeight;
import com.yisisoftware.service.business.inventory.WbCarsServiceI;
import com.yisisoftware.service.business.inventory.WbCustomerServiceI;
import com.yisisoftware.service.business.inventory.WbMaterialServiceI;
import com.yisisoftware.service.business.inventory.WbSupplyServiceI;
import com.yisisoftware.service.business.statistics.WbWeightServiceI;
import com.yisisoftware.util.base.Constant;
import com.yisisoftware.util.base.DateUtil;
import com.yisisoftware.util.base.HqlFilter;

/**
 * 库存明细管理
 * 
 * @author Administrator
 *
 */
@Controller
public class InventoryDetailsAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(InventoryDetailsAction.class);

	/**
	 * 车牌号服务
	 */
	@Autowired
	private WbCarsServiceI wbCarsService;
	
	/**
	 * 客户实现服务
	 */
	@Autowired
	private WbCustomerServiceI wbCustomerService;
	
	/**
	 * 物料服务
	 */
	@Autowired
	private WbMaterialServiceI wbMaterialService;
	
	/**
	 * 供货商服务
	 */
	@Autowired
	private WbSupplyServiceI wbSupplyService;
	
	/**
	 * 出入库服务
	 */
	@Autowired
	private WbWeightServiceI wbWeightService;
	
	/**
	 * 获得库存明细信息
	 */
	public void getInventoryDetails(){
		
		String[] type = {"0","3"};
		
		//获得车牌号
		String sql = "SELECT DISTINCT SUBSTRING(ShortName, 0, 2) as car from WB_Cars t "+
				"where IsNumeric(t.ShortName)=0 AND ascii(SUBSTRING(ShortName, 0, 2)) > 123";
		List<Object> carsList = this.wbCarsService.findBySql(sql);
		//获得客户列表
		List<WbCustomer> customersList = this.wbCustomerService.find();
		//获得物料列表
		List<WbMaterial> materialList = this.wbMaterialService.find();
		//获得供货商列表
		List<WbSupply> supplyList= this.wbSupplyService.find();
		
		/*车牌号*/
		StringBuffer carNum = new StringBuffer();
		for(Object o :carsList){
			carNum.append(o.toString()+",");
		}
		if(carNum.lastIndexOf(",")>0){
			carNum.deleteCharAt(carNum.lastIndexOf(","));
		}
		
		/*客户列表*/
//		Map<String, Object> customersMap = new HashMap<String, Object>();
//		List<Map<String, Object>> customersListTmp = new ArrayList<Map<String, Object>>();
		Map<String, Object> customerMapTmp = new HashMap<>();
		for(WbCustomer wbCustomer: customersList){
			customerMapTmp.put(wbCustomer.getId(), wbCustomer.getShortName());
//			customersListTmp.add(customerMapTmp);
		}
//		carMap.put("persons", customersListTmp);
		
		/*物料列表*/
//		Map<String, Object> materialMap = new HashMap<String, Object>();
//		List<Map<String, Object>> materialListTmp = new ArrayList<Map<String, Object>>();
		Map<String, Object> wbMaterialMapTmp = new HashMap<>();
		for(WbMaterial wbMaterial: materialList){
			wbMaterialMapTmp.put(wbMaterial.getId(), wbMaterial.getShortName());
//			materialListTmp.add(wbMaterialMapTmp);
		}
//		carMap.put("materials", materialListTmp);
		
		/*供货商列表*/
//		Map<String, Object> supplyMap = new HashMap<String, Object>();
//		List<Map<String, Object>> supplyListTmp = new ArrayList<Map<String, Object>>();
		Map<String, Object> wbSupplyMapTmp = new HashMap<>();
		for(WbSupply wbSupply: supplyList){
			wbSupplyMapTmp.put(wbSupply.getId(), wbSupply.getShortName());
//			supplyListTmp.add(wbSupplyMapTmp);
		}
		
		/*status*/
		StringBuffer statusListT = new StringBuffer();
		statusListT.append("已交票,");
		statusListT.append("已发货,");
		statusListT.append("已派车");
		
		List<Object> backList = new ArrayList<>();
		for(int i = 0 ;i < type.length ;i++ ){
			Map<String, Object> backMap = new HashMap<>();
			backMap.put("cars", carNum.toString());	//车牌号
			backMap.put("materials", wbMaterialMapTmp);	//物料
			if(type[i].equals(String.valueOf(Constant.TYPE_CHUKU))){
				backMap.put("persons", customerMapTmp);	//客户列表
			}else if(String.valueOf(Constant.TYPE_RUKU).equals(type[i])){
				backMap.put("persons", wbSupplyMapTmp);	//供货商
			}
			backMap.put("status", statusListT.toString());	//status
			if(type[i].equals(String.valueOf(Constant.TYPE_RUKU))){
				backMap.put("type", String.valueOf(Constant.TYPE_RUKU));	//cars
			}else if(String.valueOf(Constant.TYPE_CHUKU).equals(type[i])){
				backMap.put("type", String.valueOf(Constant.TYPE_CHUKU));
			}
			backList.add(backMap);
		}
		
		writeJson(backList);
	}
	
	/**
	 * 获得库存明细列表
	 * @throws UnsupportedEncodingException 
	 */
	public void getInventoryDetailsList() throws UnsupportedEncodingException{
		Integer rows = 7;
		Integer page = 1;
		String pageStr = this.getRequest().getParameter("page");
		if(StringUtils.isNotEmpty(pageStr)){
			page = Integer.valueOf(pageStr);	//获得页码
		}
		String rowstr = this.getRequest().getParameter("rows");
		if(StringUtils.isNotEmpty(rowstr)){
			rows = Integer.valueOf(rowstr); //获得每页显示的页数
		}
		String type = this.getRequest().getParameter("type");	//获得库存类型 0入库 3 出库
		
		String companyID = this.getRequest().getParameter("company");	//获得 供应商或者客户
		
		String materialID = this.getRequest().getParameter("material");	//获得物料id
		
		
		String startTime = this.getRequest().getParameter("startTime");		//开始时间
		String endTime = this.getRequest().getParameter("endTime");		//结束时间
		
		String status = this.getRequest().getParameter("status");	//状态 （查询条件 暂时不做处理）
		
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date beginDate,endDate;	//初始化日期
		
		if(StringUtils.isNotEmpty(startTime)){
			try {
				beginDate = dateformat.parse(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			try {
				endDate = dateformat.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String carNum ="";
		String tmpStr = this.getRequest().getParameter("carNum");
		if(StringUtils.isNotEmpty(tmpStr)){
//			carNum = new String(tmpStr.getBytes("iso-8859-1"), "utf-8");	//获得车牌号全部
			carNum = tmpStr;	
		}
		String materials = this.getRequest().getParameter("materials"); //获得物料信息

		String sql = "from WbWeight t where 1 = 1";
		//初始化返回list
		List<Map<String, Object>> backList = new ArrayList<>();
		
		HqlFilter hqlFilter = new HqlFilter();
		if(StringUtils.isNotEmpty(type)){
			sql += " and type ="+type;
		}
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and lightDate >='"+startTime+"'";
		}
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and lightDate <='"+endTime+"'";
		}
		if(StringUtils.isNotEmpty(materialID)){
			sql += " and material ='"+ materialID+"'";
		}
		if(StringUtils.isNotEmpty(carNum)){
			sql +=" and cars in (select c.id from WbCars c where ShortName LIKE '"+carNum+"%')";
		}
		//设置供货商和客户
		if(StringUtils.isNotEmpty(companyID)){
			if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){		//当type为3 出库时 查询条件为客户
				hqlFilter.addFilter("QUERY_t#customer_S_EQ", companyID);
//				sql = sql.replaceAll("WB_Supply", "WB_Customer");
			}else if(String.valueOf(Constant.TYPE_RUKU).equals(type)){	//当type为0入库时 查询条件为供货商
				hqlFilter.addFilter("QUERY_t#supply_S_EQ", companyID);
//				sql = sql.replaceAll("WB_Supply", "WB_Supply");
			}
//			sql +=" and s.id='"+companyID+"'";
		}
		
		
		List<WbWeight> weightList = this.wbWeightService.find(sql, hqlFilter.getParams(), page, rows);
		
		
		for(WbWeight weight: weightList){
			HqlFilter hqlFilter2 = new HqlFilter();
			hqlFilter2.addFilter("QUERY_t#id_S_EQ", weight.getCustomer());
			List<WbCustomer> customerList = this.wbCustomerService.findByFilter(hqlFilter2);	//	得到客户信息
			//查询物料
			HqlFilter hqlFilter3 = new HqlFilter();
			hqlFilter3.addFilter("QUERY_t#id_S_EQ",weight.getMaterial());
			List<WbMaterial> materialList = this.wbMaterialService.findByFilter(hqlFilter3);
			
			//查询车牌号
			HqlFilter hqlFilter4 = new HqlFilter();
			hqlFilter4.addFilter("QUERY_t#id_S_EQ",weight.getCars());
			List<WbCars> carsList = this.wbCarsService.findByFilter(hqlFilter4);
			
			//查询供货商
			HqlFilter hqlFilter5 = new HqlFilter();
			hqlFilter5.addFilter("QUERY_t#id_S_EQ",weight.getSupply());
			List<WbSupply> supplyList = this.wbSupplyService.findByFilter(hqlFilter5);
			
			Map<String, Object> backMap = new HashMap<>();
			if(String.valueOf(Constant.TYPE_RUKU).equals(type)){	//	当查询为入库时 供货商
				backMap.put("company", supplyList.get(0).getShortName());	//设置供货商名称
			}else if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){	//当查询为出库时 客户
				backMap.put("company", customerList.get(0).getShortName());	//设置客户名称
			}
			backMap.put("materialType", materialList.get(0).getShortName());	//设置物料名称
			backMap.put("carCode", carsList.get(0).getShortName());		//设置车牌号
			backMap.put("weight", weight.getSuttle());	//设置净重
			//
			if(String.valueOf(Constant.TYPE_RUKU).equals(type)){	//当查询为入库时 设置
				backMap.put("time", weight.getLightDate());	//设置轻车时间 
			}else if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){
				//当查询为出库时 设置
				backMap.put("time", weight.getWeightDate());	//设置重车时间 
			}
			backMap.put("status", "已发货");		//设置发货状态
			backList.add(backMap);
		}
		
		writeJson(backList);
	}
	
	
	
	/**
	 * 根据查询结果获得库存明细列表统计数
	 * 
	 */
	public void getInventoryDetailsListQueryCount() throws UnsupportedEncodingException{
		
		String type = this.getRequest().getParameter("type");	//获得库存类型 0入库 3 出库
		
		String materialID = this.getRequest().getParameter("material");	//获得物料id
		
		
		String startTime = this.getRequest().getParameter("startTime");		//开始时间
		String endTime = this.getRequest().getParameter("endTime");		//结束时间
		
		String status = this.getRequest().getParameter("status");	//状态 （查询条件 暂时不做处理）
		
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date beginDate,endDate;	//初始化日期
		
		if(StringUtils.isNotEmpty(startTime)){
			try {
				beginDate = dateformat.parse(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			try {
				endDate = dateformat.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String carNum ="";
		String tmpStr = this.getRequest().getParameter("carNum");
		if(StringUtils.isNotEmpty(tmpStr)){
//			carNum = new String(tmpStr.getBytes("iso-8859-1"), "utf-8");	//获得车牌号全部
			carNum = tmpStr;
		}
		String materials = this.getRequest().getParameter("materials"); //获得物料信息

		
		String sql = "from WbWeight t where 1 = 1";
		//初始化返回list
		List<Map<String, Object>> backList = new ArrayList<>();
		
		HqlFilter hqlFilter = new HqlFilter();
		if(StringUtils.isNotEmpty(type)){
			sql += " and type ="+type;
		}
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and lightDate >='"+startTime+"'";
		}
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and lightDate <='"+endTime+"'";
		}
		if(StringUtils.isNotEmpty(materialID)){
			sql += " and material ='"+ materialID+"'";
		}
		if(StringUtils.isNotEmpty(carNum)){
			sql +=" and cars in (select c.id from WbCars c where ShortName LIKE '"+carNum+"%')";
		}
		
		Long count = this.wbWeightService.count("select count(*) " + sql);		//统计总记录数
		Double weightCount =Double.valueOf("0");
		if(this.wbWeightService.sum("select sum(suttle) " + sql) == null){
			logger.error("查询结果为null 不可以转换为double");
		}else{
			weightCount = ((Double) this.wbWeightService.sum("select sum(suttle) " + sql)).doubleValue();		//统计总重量
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("count", count);
		map.put("weightCount", weightCount);
		//返回json数据
		writeJson(map);
		
	}
	
	/**
	 * 获得清单明细汇总列表
	 * 
	 */
	public void getInventoryDetailsGroupCollect(){
		
		Integer rows = 7;
		Integer page = 1;
		String pageStr = this.getRequest().getParameter("page");
		if(StringUtils.isNotEmpty(pageStr)){
			page = Integer.valueOf(pageStr);	//获得页码
		}
		String rowstr = this.getRequest().getParameter("rows");
		if(StringUtils.isNotEmpty(rowstr)){
			rows = Integer.valueOf(rowstr); //获得每页显示的页数
		}
		
		String companyID = this.getRequest().getParameter("company");	//获得供货商 或者客户
		
		String type = "";	
		type = this.getRequest().getParameter("type");	//获得库存类型 0入库 3 出库
		//初始化 type 类型为3
		if(StringUtils.isEmpty(type)){
			type = String.valueOf(Constant.TYPE_CHUKU);
		}
		
		String materialID = this.getRequest().getParameter("material");	//获得物料id
		
		
		String startTime = this.getRequest().getParameter("startTime");		//开始时间
		String endTime = this.getRequest().getParameter("endTime");		//结束时间
		
		String status = this.getRequest().getParameter("status");	//状态 （查询条件 暂时不做处理）
		String formatString = "yyyy-MM-dd";
		String subFormatString ="";
		if(StringUtils.isNotEmpty(startTime)){
			formatString = DateUtil.dateFormatString(startTime, null);
		}else if(StringUtils.isNotEmpty(endTime)){
			formatString = DateUtil.dateFormatString(endTime, null);
		}
		subFormatString = formatString;
		//补充日期格式
		if("yyyy-MM".equals(formatString)){
			formatString = "yyyy-MM-dd";
			startTime += "-01";
			endTime += "-"+DateUtil.getLastDayOfMonth(Integer.parseInt(endTime.substring(0, 4)), Integer.parseInt(endTime.substring(5, 7))).getDate();
		}else if("yyyy".equals(formatString)){
			formatString = "yyyy-MM-dd";
			startTime += "-01-01";
			endTime += "-12-31";
		}
		
		SimpleDateFormat dateformat=new SimpleDateFormat(formatString);
		
		Date beginDate,endDate;	//初始化日期
		
		if(StringUtils.isNotEmpty(startTime)){
			try {
				beginDate = dateformat.parse(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			try {
				endDate = dateformat.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String carNum ="";
		String tmpStr = this.getRequest().getParameter("carNum");
		if(StringUtils.isNotEmpty(tmpStr)){
//			carNum = new String(tmpStr.getBytes("iso-8859-1"), "utf-8");	//获得车牌号全部
			carNum = tmpStr;	
		}
		String materials = this.getRequest().getParameter("materials"); //获得物料信息
		//初始化sql
		String sql = "SELECT TOP 32 SUM (suttle) AS weight,	s.ShortName AS ghs,	COUNT (*) carTimes,	m.ShortName, CONVERT(varchar("+subFormatString.length()+"), w.lightDate, 23) FROM "
						+"WB_Weight w,	WB_Material m,	WB_Supply s "
						+"WHERE 1 = 1 AND w.Material = m.Id AND w.Supply = s.Id AND w.Type = "+type;
//						+"GROUP BY	s.ShortName,	m.ShortName";
		
		//设置开始时间
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and w.lightDate >='"+startTime+"'";
		}
		//设置结束时间
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and w.lightDate <='"+endTime+"'";
		}
		//设置物料条件
		if(StringUtils.isNotEmpty(materialID)){
			sql += " and w.material ='"+ materialID+"'";
		}
		//设置供货商和客户
		if(StringUtils.isNotEmpty(companyID)){
			if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){		//当type为3 出库时 查询条件为客户
				sql = sql.replaceAll("WB_Supply", "WB_Customer");
			}else if(String.valueOf(Constant.TYPE_RUKU).equals(type)){	//当type为0入库时 查询条件为供货商
				sql = sql.replaceAll("WB_Supply", "WB_Supply");
			}
			sql +=" and s.id='"+companyID+"'";
		}
		
		sql += " GROUP BY	s.ShortName,	m.ShortName, CONVERT(varchar("+subFormatString.length()+"), w.lightDate, 23) order by CONVERT(varchar("+subFormatString.length()+"), w.lightDate, 23) desc";
		
//		List<Object[]> list = this.wbWeightService.findBySql(sql, page, rows);
		List<Object[]> list = this.wbWeightService.findBySql(sql);	//不使用分页查询
		List<Map<String, Object>> backList = new ArrayList<>();
		for(Object[] o :list){
			Map<String, Object> backMap = new HashMap<>();
			backMap.put("weight", o[0]);
			backMap.put("company", o[1]);
			backMap.put("carTimes", o[2]);
			backMap.put("materialType", o[3]);
			backMap.put("time", o[4]);
			backList.add(backMap);
		}
		
		writeJson(backList);
	}
	
	
	/**
	 * 获得清单明细汇总列表
	 * 
	 * 带分页查询功能
	 */
	public void getInventoryDetailsGroupCollectnew(){
		
		Integer rows = 7;
		Integer page = 1;
		String pageStr = this.getRequest().getParameter("page");
		if(StringUtils.isNotEmpty(pageStr)){
			page = Integer.valueOf(pageStr);	//获得页码
		}
		String rowstr = this.getRequest().getParameter("rows");
		if(StringUtils.isNotEmpty(rowstr)){
			rows = Integer.valueOf(rowstr); //获得每页显示的页数
		}
		
		String companyID = this.getRequest().getParameter("company");	//获得供货商 或者客户
		
		String type = "";	
		type = this.getRequest().getParameter("type");	//获得库存类型 0入库3 出库
		//初始化 type 类型为3
		if(StringUtils.isEmpty(type)){
			type = String.valueOf(Constant.TYPE_CHUKU);
		}
		
		String materialID = this.getRequest().getParameter("material");	//获得物料id
		
		
		String startTime = this.getRequest().getParameter("startTime");		//开始时间
		String endTime = this.getRequest().getParameter("endTime");		//结束时间
		
		String status = this.getRequest().getParameter("status");	//状态 （查询条件 暂时不做处理）
		
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date beginDate,endDate;	//初始化日期
		
		if(StringUtils.isNotEmpty(startTime)){
			try {
				beginDate = dateformat.parse(startTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			try {
				endDate = dateformat.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String carNum ="";
		String tmpStr = this.getRequest().getParameter("carNum");
		if(StringUtils.isNotEmpty(tmpStr)){
//			carNum = new String(tmpStr.getBytes("iso-8859-1"), "utf-8");	//获得车牌号全部
			carNum = tmpStr;	
		}
		String materials = this.getRequest().getParameter("materials"); //获得物料信息
		//初始化sql
		String sql = "SELECT	SUM (suttle) AS weight,	s.ShortName AS ghs,	COUNT (*) carTimes,	m.ShortName FROM "
						+"WB_Weight w,	WB_Material m,	WB_Supply s "
						+"WHERE 1 = 1 AND w.Material = m.Id AND w.Supply = s.Id AND w.Type = "+type;
//						+"GROUP BY	s.ShortName,	m.ShortName";
		
		//设置开始时间
		if(StringUtils.isNotEmpty(startTime)){
			sql += " and w.lightDate >='"+startTime+"'";
		}
		//设置结束时间
		if(StringUtils.isNotEmpty(endTime)){
			sql += " and w.lightDate <='"+endTime+"'";
		}
		//设置物料条件
		if(StringUtils.isNotEmpty(materialID)){
			sql += " and w.material ='"+ materialID+"'";
		}
		//设置供货商和客户
		if(StringUtils.isNotEmpty(companyID)){
			if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){		//当type为3 出库时 查询条件为客户
				sql = sql.replaceAll("WB_Supply", "WB_Customer");
			}else if(String.valueOf(Constant.TYPE_RUKU).equals(type)){	//当type为0入库时 查询条件为供货商
				sql = sql.replaceAll("WB_Supply", "WB_Supply");
			}
			sql +=" and s.id='"+companyID+"'";
		}
		
		sql += " GROUP BY	s.ShortName,	m.ShortName";
		
		List<Object[]> list = this.wbWeightService.findBySql(sql, page, rows);
		List<Map<String, Object>> backList = new ArrayList<>();
		for(Object[] o :list){
			Map<String, Object> backMap = new HashMap<>();
			backMap.put("weight", o[0]);
			backMap.put("company", o[1]);
			backMap.put("carTimes", o[2]);
			backMap.put("materialType", o[3]);
			backList.add(backMap);
		}
		
		writeJson(backList);
	}
}
