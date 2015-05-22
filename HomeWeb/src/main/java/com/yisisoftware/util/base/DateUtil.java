package com.yisisoftware.util.base;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.yisisoftware.exception.DateException;

/**
 * 日期格式化工具类
 * 可识别日期格式为yyyy-MM-dd HH:mm:ssSSS
 * 
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateUtil.class);


	/**
	 * 根据日期判断可转为成的日期格式
	 * 
	 * @param dateString
	 * 					日期字符串
	 * @param styleFormat
	 * 					日期分割样式,如果不设置 将使用默认样式yyyy-MM-dd HH:mm:ssSSS
	 * 
	 * @return	返回可转换的格式
	 */
	public static String dateFormatString(String dateString,String styleFormat){
		
		//判断日期分割样式
		if(StringUtils.isEmpty(styleFormat)){
			styleFormat = "yyyy-MM-dd HH:mm:ssSSS";		//默认日期分割样式
		}
		char[] ch = styleFormat.replaceAll("\\w", "").toCharArray();
		//将所有非数字字符替换为‘-’号
		dateString = dateString.replaceAll("\\D", "-");
		String[] dString = dateString.split("-");
		String[] formatString = {"y","M","d","H","m","s","S"};	//日期类型
		
		StringBuffer format= new StringBuffer();	//日期格式
		
		if(dString.length>7){
			try {
				throw new DateException("日期类型无法解析！");
			} catch (DateException e) {
				e.printStackTrace();
				logger.error("日期类型无法解析！");
			}
		}
		for(int i=0; i<dString.length; i++){
			int tmp = dString[i].length();
			for(int j=0; j<tmp ; j++){
			format.append(formatString[i]);
			}
			if(i != dString.length -1 ){	//分隔符添加到尾部前一项
				format.append(ch[i]);
			}
		}
		return format.toString();
	}
	
	/**
	 * 输入年月获得日期
	 * 
	 * @param year
	 * 				年
	 * @param month
	 * 				月
	 * @return	返回日期
	 */
	public static Date getLastDayOfMonth(int year, int month) { 
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, year);// 年 
		cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1 
		cal.set(Calendar.DATE, 1);// 日，设为一号 
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号 
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天 
		return cal.getTime();
		
	} 
	
	
	/*public static void main(String[] argv) throws Exception {

			System.out.println(getLastDayOfMonth(2015, 02).getDate());
	  }*/
}
