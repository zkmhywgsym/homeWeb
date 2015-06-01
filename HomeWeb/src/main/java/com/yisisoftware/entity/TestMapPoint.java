package com.yisisoftware.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "map_point")
public class TestMapPoint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double longitude;
	private double latitude;
	private Date time;
	public TestMapPoint() {
		super();
	}
	
	public TestMapPoint(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	
	public TestMapPoint(double longitude, double latitude, Date time) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	@Override
	public String toString() {
		return "MapPoint [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
	

}
