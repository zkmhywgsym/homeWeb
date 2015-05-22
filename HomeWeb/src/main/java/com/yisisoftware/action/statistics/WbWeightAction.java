package com.yisisoftware.action.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.WbWeight;
import com.yisisoftware.entity.view.WbWeightView;
import com.yisisoftware.service.business.statistics.WbWeightServiceI;
import com.yisisoftware.util.base.Constant;
import com.yisisoftware.util.base.HqlFilter;

/**
 * 出入库统计分析
 * 
 * @author Administrator
 *
 */
@Controller
public class WbWeightAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WbWeightAction.class);

	@Autowired
	private WbWeightServiceI WbWeightService;
	
	/**
	 * 统计
	 * @throws ParseException 
	 */
	public void statistics() throws ParseException{
		
		String type = this.getRequest().getParameter("type");
		String sql = "";
		
		List<WbWeightView> backList = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(type)){
			HqlFilter hqlFilter = new HqlFilter();
			//当type为-1时显示全部数据
			if("-1".equals(type)){
				List<WbWeightView> typeList3 = new ArrayList<>();
				List<WbWeightView> typeList1 = new ArrayList<>();
				sql = "select convert(char(10),lightDate,120) as time,sum(suttle) as weight,type from WB_Weight "+
						"where type=3 AND  convert(char(10),lightDate,120) > DATEADD(day,-8,getdate()) "+
						"group by convert(char(10),lightDate,120),type order by convert(char(10),lightDate,120) desc";
				typeList3 = findWbWeightViewListByType(sql);
				backList.addAll(typeList3);
				sql = "select convert(char(10),weightDate,120) as time,sum(suttle) as weight,type from WB_Weight "+
						"where type=0 AND  convert(char(10),weightDate,120) > DATEADD(day,-8,getdate()) "+
						"group by convert(char(10),weightDate,120),type order by convert(char(10),weightDate,120) desc";
				typeList1 = findWbWeightViewListByType(sql);
				backList.addAll(typeList1);
			}
			if(String.valueOf(Constant.TYPE_RUKU).equals(type)){
				//入库查询
				sql = "select convert(char(10),lightDate,120) as time,sum(suttle) as weight,type from WB_Weight "+
						"where type=3 AND  convert(char(10),lightDate,120) > DATEADD(day,-8,getdate()) "+
						"group by convert(char(10),lightDate,120),type order by convert(char(10),lightDate,120) desc";
				backList = findWbWeightViewListByType(sql);
			}
			if(String.valueOf(Constant.TYPE_CHUKU).equals(type)){
				//出库查询
				sql = "select convert(char(10),weightDate,120) as time,sum(suttle) as weight,type from WB_Weight "+
						"where type=0 AND  convert(char(10),weightDate,120) > DATEADD(day,-8,getdate()) "+
						"group by convert(char(10),weightDate,120),type order by convert(char(10),weightDate,120) desc";
				backList = findWbWeightViewListByType(sql);
			}
		}
		
		writeJson(backList);
	}
	
	
	/**
	 * 根据类型 获得出入库信息列表
	 * 
	 * @param sql	查询语句
	 * 
	 * @return 返回 WbWeightView 列表
	 */
	private List<WbWeightView> findWbWeightViewListByType(String sql){
		List<WbWeightView> backList = new ArrayList<>();
		List<Object[]> list = this.WbWeightService.findBySql(sql);
		if(StringUtils.isNotEmpty(sql)){
			for(Object[] w: list){
				WbWeightView wbWeightView = new WbWeightView();
				wbWeightView.setTime(w[0].toString());
				wbWeightView.setWeight(Double.valueOf(w[1].toString()));
				wbWeightView.setType(Short.valueOf(w[2].toString()));
				backList.add(wbWeightView);
			}
		}
		
		return backList;
	}
}
