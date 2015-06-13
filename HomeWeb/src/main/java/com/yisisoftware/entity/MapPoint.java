package com.yisisoftware.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "map_point")
public class MapPoint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double longitude;
	private double latitude;
	private Date time;
	private String name;//登陆人
	private String deviceName;//设备名称
	private String locationType;//定位类型（gps,wf,cl）
	public MapPoint() {
		super();
	}
	public MapPoint(int id, double longitude, double latitude, Date time,
			String name, String deviceName, String locationType) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.name = name;
		this.deviceName = deviceName;
		this.locationType = locationType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	

}
