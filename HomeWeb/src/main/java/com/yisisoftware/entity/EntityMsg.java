package com.yisisoftware.entity;

import java.io.File;

import javax.persistence.Id;

//提交后台数据
public class EntityMsg {
	@Id
	private int id;
	private String name;
	private String imei;
	private String cardJson;
	private double longitude;
	private double latitude;
	private File file;
	private String locationType;//wf,gps,cl
	private long optTime;
	
	public EntityMsg() {
		super();
	}

	public EntityMsg(int id, String name, String imei, String cardJson,
			double longitude, double latitude, File file, String locationType,
			long optTime) {
		super();
		this.id = id;
		this.name = name;
		this.imei = imei;
		this.cardJson = cardJson;
		this.longitude = longitude;
		this.latitude = latitude;
		this.file = file;
		this.locationType = locationType;
		this.optTime = optTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCardJson() {
		return cardJson;
	}

	public void setCardJson(String cardJson) {
		this.cardJson = cardJson;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public long getOptTime() {
		return optTime;
	}

	public void setOptTime(long optTime) {
		this.optTime = optTime;
	}
	
}
