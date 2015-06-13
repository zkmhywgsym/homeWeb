package com.yisisoftware.util.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;


/**
 * 
 * HQL过滤器，用于添加where条件和排序，过滤结果集
 * 
 * 添加规则使用addFilter方法
 * 
 * 举例：QUERY_t#id_S_EQ = 0 //最终连接出的HQL是 and t.id = :id id的值是0通过参数传递给Dao
 * 
 * 格式说明QUERY前缀就说明要添加过滤条件
 * 
 * t#id 就是t.id
 * 
 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float DB:Double
 * 
 * EQ 是操作符
 * 
 * // EQ 相等 // IN 包含 // NE 不等 // LT 小于 // GT 大于 // LE 小于等于 // GE 大于等于 // LK 模糊  // RLK 右模糊 // LLK 左模糊
 * 
 * @version 1.1   添加细化模糊查询共能（增加左模糊和右模糊，调用方法不变，使用方式：前台传值时包含*即可）
 */
public class HqlFilter {

	private HttpServletRequest	request;	//为了获取request里传过来的动态参数
	private Map<String, Object> params=new HashMap<String, Object>();		//条件参数
	private StringBuffer hql=new StringBuffer();
	private String sortField;	//排序字段
	private String sortOrder="desc";  //asc/desc  默认为desc
	
	/**
	 * 默认构造参数
	 */
	public HqlFilter() {
	}

	/**
	 * 带参数的构造参数
	 * 
	 * @param request
	 */
	public HqlFilter(HttpServletRequest request) {
		this.request = request;
		addFilter(request);
	}
	
	/**
	 * 添加排序字段
	 * 
	 * @param sort
	 */
	public void addSort(String sort) {
		this.sortField = sort;
	}

	/**
	 * 添加排序方法，默认asc升序
	 * 
	 * @param order
	 */
	public void addOrder(String order) {
		this.sortOrder = order;
	}
	
	/**
	 * 获得过滤字段参数和值
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 获得添加过滤字段后的HQL
	 * 
	 * @return
	 */
	public String getWhereHql() {
		return hql.toString();
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param request2
	 */
	public void addFilter(HttpServletRequest request) {

		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			String value=request.getParameter(name);
			addFilter(name,value);
		}
	}

	/**
	 * 添加过滤
	 * 
	 * 举例，name传递：QUERY_t#id_S_EQ
	 * 
	 * 举例，value传递：0
	 * 
	 * @param name
	 *      			查询条件
	 * 
	 * @param value
	 * 					对应的值
	 */
	public void addFilter(String name, String value) {

		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)){
			if(name.startsWith("QUERY_")){	//如果有需要过滤的字段
				String[] filterParams=StringUtils.split(name, "_");
				if(filterParams.length==4){
					String columnName = filterParams[1].replaceAll("#", ".");	//要过滤的字段名
					String columnType = filterParams[2];	//过滤字段的类型
					String operator = filterParams[3];	//sql操作
					String placeholder=UUID.randomUUID().toString().replaceAll("-", "");	//生成一个随机参数名
					
					if(hql.toString().indexOf(" where 1=1")<0){
						hql.append(" where 1=1 ");
					}
					//细化模糊查询的方法
					if("LK".equals(operator)){
						if(value.indexOf("*")!=-1){
							//传入参数中含有*号,判断*号位置
							if(value.indexOf("*")==0){
								operator="LLK";
							}else if(value.indexOf("*")==(value.length()-1)){
								operator="RLK";
							}
							value=value.replace("*", "");
							
						}
					}
					hql.append(" and "+ columnName+ getSqlOperator(operator)+" :param"+placeholder+" ");
					params.put("param"+placeholder,	getObjValue(columnType,operator,value));
				}
			}
		}
		
	}

	/**
	 * 将String类型转换为Object类型，用于拼写HQL，替换操作符和值
	 * 
	 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float DB:Double
	 * 
	 * @param columnType
	 * 						字段类型
	 * @param operator
	 * 						sql操作
	 * @param value
	 * 					对应值（字符串型）
	 * @return		对应类型的值
	 */
	private Object getObjValue(String columnType, String operator,
			String value) {

		if(StringUtils.equalsIgnoreCase(columnType, "S")){
			if(StringUtils.equalsIgnoreCase(operator, "LK")){
				value="%"+value+"%";
			}else if (StringUtils.equalsIgnoreCase(operator, "RLK")) {
				value = value + "%%";
			} else if (StringUtils.equalsIgnoreCase(operator, "LLK")) {
				value = "%%" + value;
			}
			return value;
		}
		if(StringUtils.equalsIgnoreCase(columnType, "L")){
			return Long.parseLong(value);
		}
		if(StringUtils.equalsIgnoreCase(columnType, "I")){
			return Integer.parseInt(value);
		}
		if(StringUtils.equalsIgnoreCase(columnType, "D")){
			try {
//				return DateUtils.parseDate(value, new String[]{ "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd" });
				Date date = DateUtils.parseDate(value, new String[]{ "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd" });
				return new java.sql.Date(date.getTime()); //转换为sql.date 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.equalsIgnoreCase(columnType, "ST")){
			return Short.parseShort(value); 
		}
		if(StringUtils.equalsIgnoreCase(columnType, "BD")){
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if(StringUtils.equalsIgnoreCase(columnType, "FT")){
			return Float.parseFloat(value);
		}
		if(StringUtils.equalsIgnoreCase(columnType, "DB")){
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * 转换sql操作符
	 * 
	 * @param columnType
	 * 		// EQ 相等 // IN 包含 // NE 不等 // LT 小于 // GT 大于 // LE 小于等于 // GE 大于等于 // LK 模糊
	 * @return
	 */
	private String getSqlOperator(String columnType) {
		if(StringUtils.equalsIgnoreCase(columnType, "EQ")){
			return " = ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "IN")){
			return " in ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "NE")){
			return " != ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "LT")){
			return " < ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "GT")){
			return " > ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "LE")){
			return " <= ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "GE")){
			return " >= ";
		}
		if(StringUtils.equalsIgnoreCase(columnType, "LK") || StringUtils.equalsIgnoreCase(columnType, "RLK") || StringUtils.equalsIgnoreCase(columnType, "LLK")){
			return " like ";
		}
		return "";
	}
	
	/**
	 * 获得添加过滤字段后加上排序字段的HQL
	 * 
	 * @return
	 */
	public String getWhereAndOrderHql(){
		if(StringUtils.isNotBlank(sortField) && StringUtils.isNotBlank(sortOrder)){
			if(sortField.indexOf(".")<1){
				sortField = "t."+sortField;
			}
			hql.append(" order by "+sortField+" "+sortOrder+" ");//添加排序信息
		}else{
			if(request != null){
				String s=request.getParameter("sortField");
				String o=request.getParameter("sortOrder");
				if(StringUtils.isNotBlank(s)){
					sortField = s;
				}
				if(StringUtils.isNotBlank(o)){
					sortField = o;
				}
				if(StringUtils.isNotBlank(sortField) && StringUtils.isNotBlank(sortOrder)){
					if(sortField.indexOf(".")<1){
						sortField = "t."+sortField;
					}
					hql.append(" order by "+sortField+" "+sortOrder+" ");//添加排序信息
				}
			}
		}
		return hql.toString();
	}
	
}
