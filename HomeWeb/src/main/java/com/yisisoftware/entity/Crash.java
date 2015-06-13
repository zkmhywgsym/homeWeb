package com.yisisoftware.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**客户端日志文件*/
@Entity
@SuppressWarnings("serial")
public class Crash implements Serializable{
	/**
	 * 主键id 自增长
	 */
	private Long id;
	private String appName;
	private String verName;
	private String verCode;
	private String device;
	private String model;
	private String type;
	private String crashTime;
	private String crashMsg;
	public Crash() {
		super();
	}
	public Crash(String appName, String verName, String verCode,
			String device, String model, String type, String crashTime,
			String crashMsg) {
		super();
		this.appName = appName;
		this.verName = verName;
		this.verCode = verCode;
		this.device = device;
		this.model = model;
		this.type = type;
		this.crashTime = crashTime;
		this.crashMsg = crashMsg;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getVerName() {
		return verName;
	}
	public void setVerName(String verName) {
		this.verName = verName;
	}
	public String getVerCode() {
		return verCode;
	}
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCrashTime() {
		return crashTime;
	}
	public void setCrashTime(String crashTime) {
		this.crashTime = crashTime;
	}
	public String getCrashMsg() {
		return crashMsg;
	}
	public void setCrashMsg(String crashMsg) {
		this.crashMsg = crashMsg;
	}
	@Override
	public String toString() {
		return "EntityCrash [appName=" + appName + ", verName=" + verName
				+ ", verCode=" + verCode + ", device=" + device + ", model="
				+ model + ", type=" + type + ", crashTime=" + crashTime
				+ ", crashMsg=" + crashMsg + "]";
	}
	
	
}
