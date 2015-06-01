package com.yisisoftware.action.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.databaseUtils.DatabaseHolder;
import com.yisisoftware.entity.TestMapPoint;
import com.yisisoftware.service.base.TestServiceI;
import com.yisisoftware.util.base.HqlFilter;

@Controller
public class TestAction extends BaseAction {
	private TestMapPoint point;
	@Autowired
	private TestServiceI testServer;
	public void testSavePoint() {
		point=new TestMapPoint();
		double longitude=Double.valueOf(this.getRequest().getParameter("longitude"));
		double latitude=Double.valueOf(this.getRequest().getParameter("latitude"));
		point.setLatitude(latitude);
		point.setLongitude(longitude);
		point.setTime(new Date());
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		testServer.save(point);
		Map<String, String> map = new HashMap<>();
		map.put("result", "true");
		writeJson(map);
		
	}
	public void testGetPoint(){
		point=null;
		HqlFilter hqlFilter2 = new HqlFilter();
		hqlFilter2.addOrder("desc");
		hqlFilter2.addSort("time");
		String hql = "from TestMapPoint t";
		DatabaseHolder.getInstance().setDataBaseSource(DatabaseHolder.getDbKeys(0).toString());
		List<TestMapPoint>  points = testServer.find(hql+hqlFilter2.getWhereAndOrderHql());
		if (points!=null&&!points.isEmpty()) {
			point=points.get(0);
		}else{
			point=new TestMapPoint(39.914714,116.403119);
		}
		writeJson(point);
	}
	
	
	
	public TestMapPoint getPoint() {
		return point;
	}

	public void setPoint(TestMapPoint point) {
		this.point = point;
	}
	
}
