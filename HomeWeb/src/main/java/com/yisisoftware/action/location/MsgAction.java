package com.yisisoftware.action.location;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.databaseUtils.DatabaseHolder;
import com.yisisoftware.entity.EntityICCard;
import com.yisisoftware.entity.MapPoint;
import com.yisisoftware.entity.WorkSpace;
import com.yisisoftware.service.location.IICInfoServiceI;
import com.yisisoftware.service.location.ILocationServiceI;
import com.yisisoftware.service.location.IWorkSpaceServiceI;
import com.yisisoftware.util.base.HqlFilter;

@Controller
public class MsgAction extends BaseAction {
	private String imagePath;
	private Logger logger=Logger.getLogger(getClass());
	private MapPoint point;
	@Autowired
	private ILocationServiceI locationService;
	@Autowired
	private IICInfoServiceI icInfoService;
	@Autowired
	private IWorkSpaceServiceI workSpaceService;
	public void saveICInfo() {
		imagePath=ServletActionContext.getServletContext().getRealPath("/")+"image";
		logger.info("开始提交IC卡信息");
		String test=getRequest().getParameter("test");
		System.out.println(test);
		String json=getRequest().getParameter("ICjson");
		System.out.println(json);
		EntityICCard ic=null;
		if(!StringUtils.isEmpty(json)){
			ic=JSON.parseObject(json,EntityICCard.class);
			System.out.println("ic:"+ic.getCustomName());
			System.out.println(ic.getPic1Name());
			System.out.println("file size:"+ic.getPic1().length);
		}
		icInfoService.saveFile(imagePath,ic);
		ic.setPic1(null);
		ic.setPic2(null);
		ic.setPic3(null);
		icInfoService.save(ic);
//		point=new MapPoint();
//		double longitude=Double.valueOf(this.getRequest().getParameter("longitude"));
//		double latitude=Double.valueOf(this.getRequest().getParameter("latitude"));
//		String name=this.getRequest().getParameter("name");
//		point.setLatitude(latitude);
//		point.setLongitude(longitude);
//		point.setName(name);
//		point.setTime(new Date());
//		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
//		locationService.save(point);
		Map<String, String> map = new HashMap<>();
		map.put("result", "true");
		writeJson(map);
		
	}
	public void getMapPoint(){
		point=null;
		HqlFilter hqlFilter2 = new HqlFilter();
		hqlFilter2.addOrder("desc");
		hqlFilter2.addSort("time");
		String hql = "from MapPoint t";
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<MapPoint>  points = locationService.find(hql+hqlFilter2.getWhereAndOrderHql());
		if (points!=null&&!points.isEmpty()) {
			point=points.get(0);
		}else{
			point=new MapPoint(0, 114.21892734521,29.575429778924, new Date(), "15034010946", "e_01", "wf");
		}
		writeJson(point);
	}
	public void getAllPoints(){
		String hql = "from MapPoint t";
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<MapPoint>  points = locationService.find();
		writeJson(points);
	}
	public void drawMap(){
		List result=new ArrayList();
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<WorkSpace> list=workSpaceService.find();
		for (WorkSpace workSpace : list) {
			Map<String, Object> work=new HashMap<String, Object>();
			work.put("work",workSpace);
			String[] sonNames=workSpace.getSons().split(",");
			List<MapPoint> sons=new ArrayList<MapPoint>();
			for (String sName : sonNames) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#name_S_EQ", sName);
				List<MapPoint> ss=locationService.findByFilter(hqlFilter);
				sons.add(ss.get(0));
			}
			work.put("sons",sons);
			result.add(work);
		}
		writeJson(result);
	}
	
	
	public MapPoint getPoint() {
		return point;
	}

	public void setPoint(MapPoint point) {
		this.point = point;
	}
	
}
