package com.yisisoftware.action.location;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.util.StringUtils;
import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.action.userInfo.UserInfoAction;
import com.yisisoftware.databaseUtils.DatabaseHolder;
import com.yisisoftware.entity.LoginUser;
import com.yisisoftware.entity.MapPoint;
import com.yisisoftware.entity.WorkSpace;
import com.yisisoftware.model.SessionInfo;
import com.yisisoftware.service.location.IDeviceServiceI;
import com.yisisoftware.service.location.ILocationServiceI;
import com.yisisoftware.service.location.IWorkSpaceServiceI;
import com.yisisoftware.service.user.UserServiceI;
import com.yisisoftware.util.base.ConfigUtil;
import com.yisisoftware.util.base.HqlFilter;

@Controller
public class LocationAction extends BaseAction {
	private MapPoint point=new MapPoint();
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserInfoAction.class);
	@Autowired
	private ILocationServiceI locationService;
	@Autowired
	private IWorkSpaceServiceI workSpaceService;
	@Autowired
	private UserServiceI userService;
	@Autowired
	private IDeviceServiceI deviceService;
	public void savePoint() {
		//取参
		double longitude=Double.valueOf(this.getRequest().getParameter("longitude"));
		double latitude=Double.valueOf(this.getRequest().getParameter("latitude"));
		String deviceId=this.getRequest().getParameter("deviceId");
		String type=this.getRequest().getParameter("type");
		//获取设备信息
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#deviceId_S_EQ", deviceId);
		String deviceName=deviceService.findByFilter(hqlFilter).get(0).getName();
		//获取用户
		Object info=getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String userName="";
		if(info!=null){
			LoginUser user=((SessionInfo)info).getUser();
			userName=user.getAdminUserName();
		}
		logger.info("用户"+userName+"通过设备"+deviceName+"定位，类型是"+type+"坐标是：("+latitude+":"+longitude+")");
		//保存定位信息
		point.setLatitude(latitude);
		point.setLongitude(longitude);
		point.setName(userName);
		point.setDeviceName(deviceName);
		point.setTime(new Date());
		point.setLocationType(type);
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		locationService.save(point);
		//返回操作结果
		Map<String, String> map = new HashMap<>();
		map.put("result", "true");
		writeJson(map);
		logger.info("用户"+userName+"通过设备"+deviceName+"定位成功，类型是"+type+"坐标是：("+latitude+":"+longitude+")");
		
	}
	public void getMapPoint(){
		logger.info("");
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
			point=new MapPoint();
		}
		writeJson(point);
	}
	public void getAllPoints(){
		String name=getRequest().getParameter("test");
		System.out.println(name);
		String hql = "from MapPoint t where t.name= "+name+" order by t.time desc";
//		String hql = "from MapPoint t";
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<MapPoint>  points = locationService.find(hql, 1, 1);
		writeJson(points);
	}
	public void drawMap(){
		//获取参数
		String workSpaceName=getRequest().getParameter("workSpace");
		String deviceName=getRequest().getParameter("deviceName");
		String name=getRequest().getParameter("name");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		//获取工作空间
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<WorkSpace> list=workSpaceService.find();
		List<WorkSpace> targetWorkSpace=new ArrayList<WorkSpace>();
		if(!StringUtils.isEmpty(workSpaceName)){
			for (WorkSpace workSpace : list) {
				if(workSpaceName.equals(workSpace.getName())){
					targetWorkSpace.add(workSpace);
					continue;
				}
			}
		}else{
			targetWorkSpace.addAll(list);
		}
		for (WorkSpace workSpace : targetWorkSpace) {
			Map<String, Object> work=new HashMap<String, Object>();
			work.put("work",workSpace);
			String[] sonNames=workSpace.getSons().split(",");
			List<MapPoint> sons=new ArrayList<MapPoint>();
			for (String sName : sonNames) {
				if(!StringUtils.isEmpty(deviceName)&&!deviceName.equals(sName)){
					continue;
				}
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#deviceName_S_EQ", sName);
				if(!StringUtils.isEmpty(name)){
					hqlFilter.addFilter("QUERY_t#name_S_EQ", name);
				}
				//TODO 加上今天的判断
				hqlFilter.addOrder("desc");
				hqlFilter.addSort("time");
				List<MapPoint> ss=locationService.find("from MapPoint t "+hqlFilter.getWhereAndOrderHql(),hqlFilter.getParams());
//				List<MapPoint> ss=locationService.findByFilter(hqlFilter);
				if(ss.size()>0){
					sons.add(ss.get(0));
				}
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
